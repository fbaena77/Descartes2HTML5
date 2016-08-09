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
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.model.GnMetadata;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.util.Constants;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.SearchRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.SearchResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.StatusGetRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.search.StatusGetResponse;
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
public class PublishWorker  extends BaseWorker  implements Runnable {

	private static Logger log = Logger.getLogger(PublishWorker.class);
	
	GnIConnection connectionDestino = null;
	GnIConnection connectionOrigen = null;
	Map<String, String> getProps= null;
	
	/**
	 * @param job
	 */
	public PublishWorker(Job job) {
		super(job);
	}
	   /**
	   * Realiza el proceso de actualización de publicación de metadatos
	   * @author Federico Baena (Fujitsu)
	   */
	public void run() {
		
		Job currentJob = getJob();

		try {
			
			connectionDestino = currentJob.getJobConfig().getPoolGNDestino().getConnectionThreadSafe();
			connectionOrigen = currentJob.getJobConfig().getPoolGNOrigen().getConnectionThreadSafe();
			
			while(!currentJob.isPublishQueueReadyFlag() || !currentJob.getMetadatosAPublicar().isEmpty()) {
				
				GnMetadata metadataToGet = currentJob.getMetadatosAPublicar().take();
				if(metadataToGet!=Job.STOP_QUEUE_GNMETADATA){
					if(metadataToGet!=null){
						//Si es 2.6.4 TODOS LOS METADATOS "Están aprobados" y ya está
						if(currentJob.getServerOrigen().getVersion().matches(Constants.GN_VERSION_264)){
							doWorkApproved(metadataToGet, currentJob);
						}//si es 2.10.4
						else{
							//Entonces pido, mediante "xml.metadata.status.get" el estado
							StatusGetRequest statusGetReq = new StatusGetRequest(metadataToGet.getInfo().getUuid());
							try {
								StatusGetResponse statusGetResp = (StatusGetResponse)connectionOrigen.exec(statusGetReq);
								//Si el estado es "APROBADO" lo añado en la lista de metadatos a publicar.
								
								if(statusGetResp.getRecord()!=null && !statusGetResp.getRecord().isEmpty()){
									if(statusGetResp.getRecord().get(0).getName().matches(Constants.METADATA_STATUS_APPROVED)){
										doWorkApproved(metadataToGet, currentJob);
									}
									//Si el estado es "Retired" lo añado en la lista de metadatos a BORRAR.
									else if(statusGetResp.getRecord().get(0).getName().matches(Constants.METADATA_STATUS_RETIRED)){
										doWorkRetired(metadataToGet, currentJob);
									}
									else{
										log.warn("El estado del metadato de origen " +metadataToGet.getInfo().getUuid()+" no permite su importación");
									}
								}
								else{
									log.warn("El estado del metadato de origen " +metadataToGet.getInfo().getUuid()+" es desconocido");
								}
									
							} catch (GnNotAuthorizedConnectionException e) {
								log.error("Conexión a Geonetwork no autorizada. "+e.getMessage());
							} catch (GnRequestException e) {
								log.error("Error en la solicitud del estado del metadato. "+e.getMessage());
							}	
						}
					}
				}
				else{
					currentJob.getMetadatosAExportar().put(Job.STOP_QUEUE);
					currentJob.getMetadatosAEliminar().put(Job.STOP_QUEUE);
					currentJob.setPublishQueueReadyFlag(true);
				}
			}
			
		} catch (InterruptedException e) {
			log.warn("\n****Proceso de Publicación Finalizado con errores****", e);
			try {
				currentJob.getMetadatosAExportar().put(Job.STOP_QUEUE);
				currentJob.getMetadatosAEliminar().put(Job.STOP_QUEUE);
			} catch (InterruptedException e1) {
				log.error("Interrupción de la cola de publicación. "+e1.getMessage());
			}
		} catch (GnNotAvailableConnectionException e) {
			log.error("Conexión destino no disponible en este instante. "+e.getMessage());
		}
	}
	
	/**
	 * Método que realiza el trabajo xml.search
	 * y decide si se actualiza el metadato en el GN de destino o no
	 * 
	 * @param metadataToGet
	 */
	private void doWorkApproved(GnMetadata metadataToGet, Job job) throws GnNotAvailableConnectionException {
		
		Map<String, Set<String>> searchMapSet = new HashMap<String, Set<String>>(2);
		
		//Obtenemos el uuid del GN de origen como único parámetro de búsqueda en el GN de destino
		Set<String> valueSet = new HashSet<String>();
		valueSet.add(metadataToGet.getInfo().getUuid());
		searchMapSet.put("uuid", valueSet);

		SearchRequest searchReq = new SearchRequest(searchMapSet);
		
		MetadataProxy metadataAExportar = new MetadataProxy();
		metadataAExportar.setUuid(metadataToGet.getInfo().getUuid());
		
		if(connectionDestino != null){
			try {
				SearchResponse execSearchGNDestino = (SearchResponse)connectionDestino.exec(searchReq);
				
				//En vez de utilizar xml.metadata.get lo hacemos con xml.search
				if(execSearchGNDestino!=null && execSearchGNDestino.getSummary().getCount()>0){
					//Si existe el metadato en el GN de destino
					//Comparamos su fecha de actualización con la del metadato de origen
					//Si el metadato de origen es más reciente o el metadato de destino no tiene fecha de actualización y el de origen sí
					if(metadataToGet.getInfo().getChangeDate().after(execSearchGNDestino.getMetadata().get(0).getInfo().getChangeDate()) 
						|| (metadataToGet.getInfo().getChangeDate() != null && execSearchGNDestino.getMetadata().get(0).getInfo().getChangeDate()==null)){

						//En este caso no utilizaremos la cola de borrado porque podrían darse casos de falta de sincronización con la importación
						DeleteRequest deleteReq = new DeleteRequest(execSearchGNDestino.getMetadata().get(0).getInfo().getUuid());
						DeleteResponse execDeleteGNDestino = (DeleteResponse)connectionDestino.exec(deleteReq);
						
						if(execDeleteGNDestino!=null){
							//Borrado en destino con éxito
							//Metemos el de origen en la cola a exportar
							job.getMetadatosAExportar().put(metadataAExportar);	
						}else{
							log.warn("No ha sido posible borrar el metadato "+execSearchGNDestino.getMetadata().get(0).getInfo().getUuid());
						}
					} 
					else {
						if(!metadataToGet.getInfo().getChangeDate().before(execSearchGNDestino.getMetadata().get(0).getInfo().getChangeDate())){
							log.warn("El metadato "+execSearchGNDestino.getMetadata().get(0).getInfo().getUuid()+" en el GN de destino no ha sido modificado porque su fecha de actualización es más reciente");
						}
						if(metadataToGet.getInfo().getChangeDate() == null){
							log.warn("El metadato "+execSearchGNDestino.getMetadata().get(0).getInfo().getUuid()+" en el GN de destino no ha sido modificado porque el de origen no tiene fecha de actualización");
						}
					}
				}else{
					//Si no encuentra el metadato
					//Metemos en la cola a exportar
					job.getMetadatosAExportar().put(metadataAExportar);
					//Si en el properties está configurado el procesamiento de privilegios se hará.
				}
					
			} catch (GnNotAuthorizedConnectionException e) {
				log.error("No autorizado para la conexión destino en este instante. "+e.getMessage());
			} catch (GnRequestException e) {
				log.error("Error en la solicitud de búsqueda de metadatos. "+e.getMessage());
			} catch (InterruptedException e) {
				log.error("Interrupción de la cola de exportación. "+e.getMessage());
			}
		}
	}	
	
	/**
	 * Método que realiza el trabajo xml.search
	 * y decide si se borra el metadato en el GN de destino o no
	 * 
	 * @param metadataToGet
	 */
	private void doWorkRetired(GnMetadata metadataToGet, Job job) throws GnNotAvailableConnectionException {
		
		Map<String, Set<String>> searchMapSet = new HashMap<String, Set<String>>();
		
		//Obtenemos el uuid del GN de origen como único parámetro de búsqueda en el GN de destino
		Set<String> valueSet = new HashSet<String>();
		valueSet.add(metadataToGet.getInfo().getUuid());
		searchMapSet.put("uuid", valueSet);

		SearchRequest searchReq = new SearchRequest(searchMapSet);
		
		if(connectionDestino != null){
			try {
				SearchResponse execSearchGNDestino = (SearchResponse)connectionDestino.exec(searchReq);
				if(execSearchGNDestino!=null && execSearchGNDestino.getSummary().getCount()>0){
					//Si existe el metadato en el GN de destino metemos en la cola de borrar
					MetadataProxy metadataABorrar = new MetadataProxy();
					metadataABorrar.setUuid(metadataToGet.getInfo().getUuid());
					job.getMetadatosAEliminar().put(metadataABorrar);
				}
					
			} catch (GnNotAuthorizedConnectionException e) {
				log.error("No autorizado para la conexión destino en este instante. "+e.getMessage());
			} catch (GnRequestException e) {
				log.error("Error en la solicitud de búsqueda de metadatos. "+e.getMessage());
			} catch (InterruptedException e) {
				log.error("Interrupción de la cola de exportación. "+e.getMessage());
			}
		}
	}
}
