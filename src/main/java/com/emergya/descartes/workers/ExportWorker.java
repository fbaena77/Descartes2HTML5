package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAuthorizedConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAvailableConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnRequestException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.io.GnIConnection;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.util.Constants;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.util.DeleteFilesInFolder;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.mef.MefExportRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.mef.MefExportResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.Job;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.MetadataProxy;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de exportacion. Se
 * basa en el uso del servicio mef.export de Geonetwork.
 * 
 * @author root
 * 
 */
public class ExportWorker extends BaseWorker implements Runnable {

	private Logger log = Logger.getLogger(ExportWorker.class);
	
	private GnIConnection connection = null;

	/**
	 * @param job
	 */
	public ExportWorker(Job job) {
		super(job);
	}

	/**
	 * Realiza el proceso de exportación de metadatos
	 * 
	 * @author Federico Baena (Fujitsu)
	 */
	public void run() {
		try {
			Job currentJob = getJob();
			connection = currentJob.getJobConfig().getPoolGNOrigen()
					.getConnectionThreadSafe();

			boolean toTransformMEF = currentJob.getJobConfig().isToTransformMEF();
			
			// Control de existencia de carpeta de trabajo
			File theDir = new File(currentJob.getJobConfig().getExportLocalPath());
			try {
				if (theDir.exists()) { 
					DeleteFilesInFolder.delete(theDir);
				}
				theDir.mkdirs();
			} catch (Exception e) {
				log.error("No se ha podido crear la carpeta temporal de exportación de metadatos");
			}

			while (!currentJob.isExportQueueReadyFlag()
					|| !currentJob.getMetadatosAExportar().isEmpty()) {
				try {
					MetadataProxy metadataToExport = currentJob
							.getMetadatosAExportar().take();
					if (metadataToExport != Job.STOP_QUEUE) {
						if (metadataToExport != null) {
							MetadataProxy metadataFile = doWork(
									metadataToExport, currentJob);
							if (metadataFile != null) {
								// Transformación directa del MEF - modificación
								// del UUID
								if (currentJob.getJobConfig()
										.isToTransformMEF()) {
									currentJob.getMetadatosATransformar().put(
											metadataFile);
								} else {
									currentJob.getMetadatosAImportar().put(
											metadataFile);
								}
							}
						}
					} else {
						if (toTransformMEF) {
							currentJob.getMetadatosATransformar().put(
									Job.STOP_QUEUE);
						} else {
							currentJob.getMetadatosAImportar().put(
									Job.STOP_QUEUE);
						}
						log
								.info("-->>Total Exportados desde el GN en "
										+ currentJob.getServerOrigen()
												.getHost()
										+ ": "
										+ (currentJob.getMetadatosExportados()
												.size()));
						log
								.info("****Proceso de Exportación Finalizado****");
						currentJob.setExportQueueReadyFlag(true);
					}


				} catch (IOException e) {
					log.error("Error en el tratamiento de archivos"
							+ e.getMessage());
				} catch (GnRequestException e) {
					log.error("Problema detectado. " + e.getMessage());
				}
				
			}
			
			log.info("Datos que han provocado errores: "
					+ currentJob.getMetadatosErroneos().size());

		} catch (GnNotAuthorizedConnectionException e) {
			log.error("[Error] No autorizado para la conexión destino en este instante. "
					+ e.getMessage());
		} catch (GnNotAvailableConnectionException e2) {
			log.error("Conexión destino no disponible en este instante. "
					+ e2.getMessage());
		} catch (InterruptedException e1) {
			log.error("Interrupción de la cola de exportación. "
					+ e1.getMessage());
			try {
				getJob().getMetadatosATransformar().put(Job.STOP_QUEUE);
				getJob().getMetadatosAImportar().put(Job.STOP_QUEUE);
			} catch (InterruptedException e) {
				log.error("Interrupción de la cola de exportación. "
						+ e1.getMessage());
			}
		}
	}

	/**
	 * Método que realiza el trabajo de Exportado
	 * 
	 * @param metadataToExport
	 * @throws GnRequestException
	 * @throws GnNotAuthorizedConnectionException
	 * @throws IOException
	 */
	private MetadataProxy doWork(MetadataProxy metadataToExport, Job job)
			throws GnNotAuthorizedConnectionException, GnRequestException,
			IOException {

		MetadataProxy metadataFile = new MetadataProxy();

		MefExportRequest mefReq = new MefExportRequest(
				metadataToExport.getUuid());
		MefExportResponse mefExport = (MefExportResponse) connection
				.exec(mefReq);

		byte[] buffer = new byte[8 * 1024];

		InputStream input = mefExport.getResourceAsStream();
		String metadataFilePath;
		try {

			OutputStream output;
			metadataFilePath = job.getJobConfig().getExportLocalPath()
					+ metadataToExport.getUuid() + Constants._MEF;

			output = new FileOutputStream(metadataFilePath);

			try {
				int bytesRead;
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}

				File metadataFileCopy = new File(metadataFilePath);
				metadataFile.setLocalCopy(metadataFileCopy);
				metadataFile.setUuid(metadataToExport.getUuid());
				metadataFile.setChangeDate(metadataToExport.getChangeDate());

				log.info(">>Exportando Metadato: "
						+ metadataFile.getUuid());
				// Info estadística
				job.getMetadatosExportados().add(metadataFile);

			} catch (IOException e) {
				String _error = "Error en el tratamiento de archivos"
						+ e.getMessage();
				log.error(_error, e);
				return null;
			} finally {
				output.close();
			}
		} catch (FileNotFoundException e) {
			String _error = "No se ha encontrado el fichero indicado"
					+ e.getMessage();
			log.error(_error, e);
		} finally {
			input.close();
		}
		return metadataFile;
	}
}
