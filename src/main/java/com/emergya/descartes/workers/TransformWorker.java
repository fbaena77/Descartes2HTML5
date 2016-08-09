package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.util.Constants;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.util.DeleteFilesInFolder;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.Job;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.MetadataProxy;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de transformación
 * 
 * @author root
 * 
 */
public class TransformWorker extends BaseWorker implements Runnable {

	private static Logger log = Logger.getLogger(TransformWorker.class);
	
	/**
	 * @param job
	 */
	public TransformWorker(Job job) {
		super(job);
	}

	/**
	 * Realiza el proceso de transformación de metadatos
	 */
	public void run() {

		Job currentJob = getJob();

		log.info("----Inicio de la transformación de metadatos----");
		
		// Control de la carpeta de trabajo
		File theDir = new File(currentJob.getJobConfig().getTransformTempLocalPath()); 
		try{   
		    if (theDir.exists()){
		    	DeleteFilesInFolder.delete(theDir);
		    }
		    theDir.mkdirs();
		}catch(Exception e){  
			String fatalError = "No se ha podido crear la carpeta temporal de transformación de metadatos";
			log.error(fatalError);
			throw new Error(fatalError);
		}  

		while (!currentJob.isTransformQueueReadyFlag() || !currentJob.getMetadatosATransformar().isEmpty()) {
			try{
				MetadataProxy metadataToTransform = currentJob.getMetadatosATransformar().take();
				if(metadataToTransform!=Job.STOP_QUEUE){
					if (metadataToTransform != null) {
						try {
							doWork(metadataToTransform, currentJob);
						} catch (ParserConfigurationException e) {
							log.error("Ha ocurrido un error en la transformación de metadatos. "+e.getMessage());
						} catch (SAXException e) {
							log.error("Ha ocurrido un error en la transformación de metadatos. "+e.getMessage());
						}
					}
				}
				else{
					currentJob.getMetadatosAImportar().put(Job.STOP_QUEUE);
					currentJob.setTransformQueueReadyFlag(true);
					log.info("-->>Total Transformados: "+(currentJob.getMetadatosTransformados().size()));	
				}
		
			}catch (InterruptedException e) {
				log.error("Interrupción de la cola de transformación. "+e.getMessage());
				try {
					currentJob.getMetadatosAImportar().put(Job.STOP_QUEUE);
				} catch (InterruptedException e1) {
					log.error("error", e1);
				}
			}
		}
	}

	/**
	 * Método que realiza el trabajo de transformacion
	 * @param metadataToTransform
	 * @param job
	 * @throws InterruptedException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	private void doWork(MetadataProxy metadataToTransform, Job job) throws InterruptedException, ParserConfigurationException, SAXException {

		try{
			
			File  parentFolder = doMefDecompress(metadataToTransform, job);
			
			//Aplicamos las transformaciones XSL
			if(job.getJobConfig().isToTransformMEF()){
				aplicaXSLT(job, metadataToTransform, parentFolder.getAbsolutePath() + "/", job.getJobConfig().isToModifUUID());
			}
			//¿Hay que cambiar el uuid? Parametrizable
			/*if(job.getJobConfig().isToModifUUID()){
				generarUUIDMetadataXml(metadataToTransform, parentFolder.getAbsolutePath() + "/");
			}*/
			
			File theDir2 = new File(job.getJobConfig().getImportAfterTransfTempLocalPath()); 
			try{   
			    if (!theDir2.exists()){
			    	theDir2.mkdir();
			    }
			}catch(Exception e){  
				log.error("No se ha podido crear la carpeta temporal de importación de metadatos", e);
				job.getMetadatosAImportar().put(Job.STOP_QUEUE);
			} 
			
			//Volvemos a comprimir el mef
			doMefCompress(metadataToTransform);
			
			//Asignamos el contenido del fichero al metadato a importar
			File metadataFileCopy = new File(job.getJobConfig().getImportAfterTransfTempLocalPath()+metadataToTransform.getUuid()+Constants._MEF);
			metadataToTransform.setLocalCopy(metadataFileCopy);
			job.getMetadatosAImportar().put(metadataToTransform);
			
			//Estadistica
			job.getMetadatosTransformados().add(metadataToTransform);

		} catch (InterruptedException e) {
			log.error("Interrupción de la cola de transformación. "+e.getMessage());
				job.getMetadatosAImportar().put(Job.STOP_QUEUE);
		} catch (FileNotFoundException e) {
			log.error("No se ha encontrado el fichero indicado"
					+ e.getMessage(), e);
			job.getMetadatosAImportar().put(Job.STOP_QUEUE);
		} catch (IOException e) {
			log.error("Tratamiento de archivos incorrecto"
					+ e.getMessage(), e);
		}
	}

	@SuppressWarnings("resource")
	private File doMefDecompress(MetadataProxy metadataFile, Job job){

		ZipInputStream zis;
//		boolean flag = false;
		boolean creado = false;

		String pathTransformDir = job.getJobConfig().getTransformTempLocalPath();

		File parentFolder = new File(pathTransformDir
				+ metadataFile.getUuid());
		
		if (!parentFolder.exists()) {
			parentFolder.mkdir();
		}
		
		try {
			zis = new ZipInputStream(new FileInputStream(
					metadataFile.getLocalCopy()));


			ZipEntry entrada;

			while (null != (entrada = zis.getNextEntry())) {

					File folder = new File(parentFolder, entrada.getName());

					boolean esDirectorio = false;

					if (!entrada.getName().contains("."))
						esDirectorio = true;

					if (!folder.exists() && esDirectorio) {
						creado = folder.mkdirs();
					}

					if (creado) {
						if (!esDirectorio) {
							FileOutputStream fos = new FileOutputStream(
									pathTransformDir
											+ metadataFile.getUuid()
											+ "/" + entrada.getName());
							int leido;
							byte[] buffer = new byte[1024];

							while (0 < (leido = zis.read(buffer))) {
								fos.write(buffer, 0, leido);
							}
							fos.close();
						}
					} else {
						throw new IOException();
					}
			}

			zis.closeEntry();
			
		} catch (FileNotFoundException e) {
			log.error("No se ha encontrado el fichero indicado"
					+ e.getMessage(), e);
		} catch (IOException e) {
			log.error("Tratamiento de archivos incorrecto"
					+ e.getMessage(), e);
		}
		
		return parentFolder;
	}

	/**
	 * Método que aplica un XSL al metadata.xml El anterior metadata.xml se
	 * elimina y es sustituido por el nuevo.
	 * 
	 * @param job
	 * @param metadataFile
	 * @param ruta
	 */
	private void aplicaXSLT(Job job, MetadataProxy metadataFile, String ruta, boolean uuid) {
		String toAplicarXSL = job.getJobConfig().getXSLAplicablesToMEF();
		String rutaXSL = job.getJobConfig().getRutaXSLAplicablesToMEF();

		String delimitadores = " ";// se delimita por espacios
		String[] listaXSLs = toAplicarXSL.split(delimitadores);

		for (String xsl : listaXSLs) {

			log.info(">>Aplicando : " + xsl + " a "
					+ metadataFile.getUuid());
			
			

			try {
				File fichero = new File(ruta + Constants.XML_METADATA);
				
				InputStream inputStream= new FileInputStream(fichero);
				Reader reader = new InputStreamReader(inputStream,"UTF-8");
				
				File xsltFile = new File(rutaXSL + xsl);
				
				Source xmlSource = new StreamSource(reader);
				Source xsltSource = new StreamSource(xsltFile);

				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer(xsltSource);

				File metadataNew = new File(ruta + Constants.XML_METADATA_NEW);
				
				if(job.getJobConfig().getGeonetworkDestino().getServer().getVersion().equals(Constants.GN_VERSION_2104)){
				//Establecemos las urls de las miniaturas en el xsl para que sea legible por GN
				transformer.setParameter("miniaturaUrl", 
						job.getJobConfig().getGeonetworkDestino().getServer().getProtocol()+"://"+
						job.getJobConfig().getGeonetworkDestino().getServer().getHost()+":"+
						job.getJobConfig().getGeonetworkDestino().getServer().getPort()+
						job.getJobConfig().getGeonetworkDestino().getServer().getContext()+
						job.getJobConfig().getGeonetworkDestino().getServer().getServicesPrefix()+"resources.get?uuid="+
						metadataFile.getUuid()+"&fname=");
				}
				//Establecemos el uuid en el xsl
				if(uuid) {
					transformer.setParameter("uuid", metadataFile.getUuid());
					log.info(">>UUID modificado : " + metadataFile.getUuid());
				}
				transformer.transform(xmlSource, new StreamResult(metadataNew));

				//se borra el metadato original para que el renombrado funcione
				fichero.delete();
				metadataNew.renameTo(fichero);

			} catch (TransformerException e) {
				log.error("No se puede aplicar " + rutaXSL + xsl
						+ " en el metadato " + metadataFile.getUuid(), e);
			} catch (FileNotFoundException e) {
				log.error("No se puede aplicar " + rutaXSL + xsl
						+ " en el metadato " + metadataFile.getUuid(), e);
			} catch (UnsupportedEncodingException e) {
				log.error("No se puede aplicar " + rutaXSL + xsl
						+ " en el metadato " + metadataFile.getUuid(), e);
			}

		}
	}
	
	
	/**
	 * Método que comprime los metadatos a *.mef
	 * @param metadataFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void doMefCompress(MetadataProxy metadataFile) throws IOException,FileNotFoundException {

        File mefFile = new File(getJob().getJobConfig().getImportAfterTransfTempLocalPath()+metadataFile.getUuid()+Constants._MEF);
        File dirToZip = new File(getJob().getJobConfig().getTransformTempLocalPath()+metadataFile.getUuid());
        zip(dirToZip, mefFile);
	}

	/**
	 * Método que comprime un directorio en un zip
	 * @param directory
	 * @param zipfile
	 * @throws IOException
	 */
	private void zip(File directory, File zipfile) throws IOException {
	    URI base = directory.toURI();
	    Deque<File> queue = new LinkedList<File>();
	    queue.push(directory);
	    OutputStream out = new FileOutputStream(zipfile);
	    Closeable res = out;
	    try {
	      ZipOutputStream zout = new ZipOutputStream(out);
	      res = zout;
	      while (!queue.isEmpty()) {
	        directory = queue.pop();
	        for (File kid : directory.listFiles()) {
	          String name = base.relativize(kid.toURI()).getPath();
	          if (kid.isDirectory()) {
	            queue.push(kid);
	            name = name.endsWith("/") ? name : name + "/";
	            zout.putNextEntry(new ZipEntry(name));
	          } else {
	            zout.putNextEntry(new ZipEntry(name));
	            copy(kid, zout);
	            zout.closeEntry();
	          }
	        }
	      }
	    } finally {
	      res.close();
	    }
	}
	
	
	private void copy(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    while (true) {
	      int readCount = in.read(buffer);
	      if (readCount < 0) {
	        break;
	      }
	      out.write(buffer, 0, readCount);
	    }
	}

	private void copy(File file, OutputStream out) throws IOException {
	    InputStream in = new FileInputStream(file);
	    try {
	      copy(in, out);
	    } finally {
	      in.close();
	    }
	}
}