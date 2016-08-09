package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import org.apache.log4j.Logger;

import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAuthorizedConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAvailableConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnRequestException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.io.GnIConnection;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.PrivilegesRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.PrivilegesResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.Job;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.MetadataProxy;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de exportacion.
 * Se basa en el uso del servicio xml.search de Geonetwork.
 *   
 * @author root
 *
 */
public class ProcessingWorker extends BaseWorker  implements Runnable {
	
	private static Logger log = Logger.getLogger(ProcessingWorker.class);
	
	GnIConnection connection = null;
	/**
	 * @param job
	 */
	public ProcessingWorker(Job job) {
		super(job);
	}
   /**
   * Realiza el proceso de procesamiento de metadatos
   * @author Federico Baena (Fujitsu)
   */
	public void run() {
		try {
			
			Job currentJob = getJob();
			connection = currentJob.getJobConfig().getPoolGNDestino().getConnectionThreadSafe();
			
			while(!currentJob.isProcessQueueReadyFlag()|| !currentJob.getMetadatosAProcesar().isEmpty()) {
				
				MetadataProxy metadataToProcess = currentJob.getMetadatosAProcesar().take();

				if(metadataToProcess!=Job.STOP_QUEUE){
					if(metadataToProcess!=null){
						doWork(metadataToProcess, currentJob);
					}
				}else{
					log.info("-->>Total Privilegios Procesados en el GN en "+currentJob.getServerDestino().getHost()+": "+ currentJob.getMetadatosProcesados().size());
					log.info("****Procesamiento de datos finalizado****");
					currentJob.setProcessQueueReadyFlag(true);
				}
			}
			
		} catch (InterruptedException e) {
			log.error("Problema de Interrupción. "+e.getMessage() , e);
		} catch (GnNotAvailableConnectionException e) {
			log.error("Conexión destino no disponible en este instante. "+e.getMessage(), e);
		}
	}
		
	/**
	 * Método que realiza el trabajo de procesamiento
	 * 
	 */
	private void doWork(MetadataProxy metadataToProcess, Job job) {
		PrivilegesRequest privilReq = new PrivilegesRequest (metadataToProcess.getUuid(),job.getJobConfig().getPrivilegesToProcess());
		try {
			PrivilegesResponse privilResp = (PrivilegesResponse)connection.exec(privilReq);
			
			if(privilResp!=null){
				//Info estadística
				job.getMetadatosProcesados().add(metadataToProcess);
				log.info(">>Modificados Privilegios al metadato: "+metadataToProcess.getUuid());
			}
			
		} catch (GnNotAuthorizedConnectionException e) {
			log.error("No autorizado para la conexión destino en este instante. "+e.getMessage(), e);
		} catch (GnRequestException e) {
			log.error("[Error]"+e.getMessage(), e);
		} catch (NullPointerException e){
			// FIXME Revisar este nullpointer
			log.warn("El Dato con uuid :"+ e +" ya existe" , e);
		}
	}
}