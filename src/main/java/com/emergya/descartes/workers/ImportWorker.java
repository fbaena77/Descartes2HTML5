package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAuthorizedConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAvailableConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnRequestException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.io.GnIConnection;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.mef.MefImportRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.mef.MefImportResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.SearchRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.SearchResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.DeleteRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.DeleteResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.Job;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.MetadataProxy;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de importación.
 * Se basa en el uso del servicio mef.import de Geonetwork.
 *   
 * @author root
 *
 */
public class ImportWorker  extends BaseWorker  implements Runnable {
	
	private static Logger log = Logger.getLogger(ImportWorker.class);
	
	GnIConnection connectionDestino = null;
	/**
	 * @param job
	 */
	public ImportWorker(Job job) {
		super(job);
	}
   /**
   * Realiza el proceso de importación de metadatos
   * @author Federico Baena (Fujitsu)
   */
	public void run() {
		
		Job currentJob = getJob();
		boolean toProcessPrivileges = currentJob.getJobConfig().isToProcessPrivileges();
		
		try {
	
			connectionDestino = currentJob.getJobConfig().getPoolGNDestino().getConnectionThreadSafe();
			
			while(!currentJob.isImportQueueReadyFlag() || !currentJob.getMetadatosAImportar().isEmpty()) {
				
				MetadataProxy metadataToImport = currentJob.getMetadatosAImportar().take();
				boolean imported = false;
				
				if(metadataToImport!=Job.STOP_QUEUE){
					if(metadataToImport!=null){
						imported = doWork(metadataToImport, currentJob);
					}
				}else{
					if(toProcessPrivileges){
						currentJob.getMetadatosAProcesar().put(Job.STOP_QUEUE);
					}
					log.info("-->>Total Importados al GN en "+currentJob.getServerDestino().getHost()+": "+ (currentJob.getMetadatosImportados().size()));
					log.info("****Proceso de Importación Finalizado****");
					currentJob.setImportQueueReadyFlag(true);
				}

				if(imported && (toProcessPrivileges)){
					MetadataProxy metadataAProcesar = new MetadataProxy();
					metadataAProcesar.setUuid(metadataToImport.getUuid());
					currentJob.getMetadatosAProcesar().put(metadataAProcesar);
				}
			}	
		} catch (InterruptedException e) {
			log.error("****Proceso de Importación Finalizado con errores****", e);
			try {
				if(toProcessPrivileges){
					getJob().getMetadatosAProcesar().put(Job.STOP_QUEUE);
				}
			} catch (InterruptedException e1) {
				log.error("Interrupción de la cola de importación. "+e1.getMessage(), e);
			}
		} catch (GnNotAvailableConnectionException e) {
			log.error("No se ha podido conectar con el GN Destino. "+e.getMessage(), e);
			try {
				getJob().getMetadatosAProcesar().put(Job.STOP_QUEUE);
			} catch (InterruptedException e1) {
				log.error("Interrupción de la cola de importación. "+e1.getMessage());
			}
		} catch (GnNotAuthorizedConnectionException e) {
			log.error("Conexión a Geonetwork no autorizada. "+e.getMessage());
			e.printStackTrace();
		} catch (GnRequestException e) {
			log.error("Error en la solicitud del estado del metadato. "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Método que realiza el trabajo de Importado
	 * 
	 * @param metadataToImport
	 * @param job
	 * @throws GnRequestException 
	 * @throws GnNotAuthorizedConnectionException 
	 * @throws GnRequestException
	 */
	private boolean doWork(MetadataProxy metadataToImport, Job job) throws GnNotAvailableConnectionException, GnNotAuthorizedConnectionException, GnRequestException {
		
		boolean result = false;
		MefImportRequest importReq = new MefImportRequest(metadataToImport.getLocalCopy());

		if(connectionDestino != null){
			try {
				//Comprobamos si existe el metadato en el GN de destino
				Map<String, Set<String>> searchMapSet = new HashMap<String, Set<String>>(2);
				//En vez de utilizar xml.metadata.get lo hacemos con xml.search
				//El uuid del GN de origen es el único parámetro de búsqueda en el GN de destino
				Set<String> valueSet = new HashSet<String>();
				valueSet.add(metadataToImport.getUuid());
				searchMapSet.put("uuid", valueSet);

				SearchRequest searchReq = new SearchRequest(searchMapSet);
				
				MetadataProxy metadataAActualizar = new MetadataProxy();
				metadataAActualizar.setUuid(metadataToImport.getUuid());
				
				SearchResponse execSearchGNDestino = (SearchResponse)connectionDestino.exec(searchReq);
				//Si existe el metadato en el GN de destino
				if(execSearchGNDestino!=null && execSearchGNDestino.getSummary().getCount()>0){
					if(job.getJobConfig().isToOverrideMetadata())
					{
						log.info("El metadato "+metadataToImport.getUuid()+" ya existe en el GN de destino");
						//Si el metadato de origen tiene fecha de actualización
						if(metadataToImport.getChangeDate()!=null){
							//Si el metadato de destino no tiene fecha de actualización o si su fecha es más antigua, actualizamos
							if((execSearchGNDestino.getMetadata().get(0).getInfo().getChangeDate()==null) || 
							   (metadataToImport.getChangeDate().after(execSearchGNDestino.getMetadata().get(0).getInfo().getChangeDate()))){
								
								doUpdateMetadata(metadataToImport, job);
								result = true;
							}
							else{
								log.warn("El metadato "+execSearchGNDestino.getMetadata().get(0).getInfo().getUuid()+" en el GN de destino no ha sido modificado porque su fecha de actualización es más reciente");
							}
						//El metadato de origen no tiene fecha de actualizacion	no actualizamos
						}
						else{
							log.warn("El metadato "+metadataToImport.getUuid()+" no se ha actualizado en el GN de destino porque no tiene fecha de actualización en el GN de origen");
						}
					}
					else{
						log.info("El metadato "+metadataToImport.getUuid()+" ya existe en el GN de destino. Modifique la configuración para actualizarlo");
					}
				}
				else{
					
					MefImportResponse execImport = (MefImportResponse)connectionDestino.exec(importReq);
					if(execImport!=null){
						//Info estadística
						job.getMetadatosImportados().add(metadataToImport);
						result = true;
						log.info(">>Importando "+metadataToImport.getUuid());
					}
				} 
				
			}catch (GnNotAuthorizedConnectionException e) {
				log.error("[Error] No autorizado para la conexión destino en este instante. "+e.getMessage());
				result = false;
			} catch (GnRequestException e) {
				log.warn(e.getMessage());
				result = false;
			}
					
		}
		
		return result;		
	}
	/**
	 * Método que realiza el trabajo de actualización de metadatos
	 * 
	 * @param metadataToImport
	 * @param job
	 * @throws GnNotAvailableConnectionException 
	 * @throws GnNotAuthorizedConnectionException 
	 * @throws GnRequestException
	 */
	private void doUpdateMetadata(MetadataProxy metadataToImport, Job job) throws GnNotAvailableConnectionException, GnNotAuthorizedConnectionException, GnRequestException{
		//En este caso no utilizaremos la cola de borrado porque podrían darse casos de falta de sincronización con la importación
		DeleteRequest deleteReq = new DeleteRequest(metadataToImport.getUuid());
		DeleteResponse execDeleteGNDestino = (DeleteResponse)connectionDestino.exec(deleteReq);
		if(execDeleteGNDestino!=null){
			//Borrado en destino con éxito
			//Volvemos a procesar la importación
			log.info("El metadato "+metadataToImport.getUuid()+" se actualizará en el GN de destino");
			doWork(metadataToImport, job);

		}else{
			log.error("[Error] No ha sido posible borrar el metadato "+metadataToImport.getUuid());
		}
	}
}
