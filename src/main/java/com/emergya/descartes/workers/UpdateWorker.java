package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import java.util.Map;

import org.apache.log4j.Logger;

import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAuthorizedConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnNotAvailableConnectionException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.ex.GnRequestException;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.io.GnIConnection;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.UpdateRequest;
import es.juntadeandalucia.cmaot.geoinspire.exportador.geonet.xmlhttp.ops.transform.UpdateResponse;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.Job;
import es.juntadeandalucia.cmaot.geoinspire.exportador.job.MetadataProxy;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de actualización.
 * Se basa en el uso del servicio xml.metadata.update de Geonetwork.
 *   
 * @author root
 *
 */
public class UpdateWorker extends BaseWorker  implements Runnable {

	private static Logger log = Logger.getLogger(UpdateWorker.class);
	
	GnIConnection connection = null;
	Map<String, String> processProps= null;
	
	/**
	 * @param job
	 */
	public UpdateWorker(Job job) {
		super(job);
	}
   /**
   * Realiza el proceso de actualización de metadatos
   * @author Federico Baena (Fujitsu)
   */
	public void run() {
		try {
			
			Job currentJob = getJob();
			
			connection = currentJob.getJobConfig().getPoolGNDestino().getConnectionThreadSafe();
			while(!currentJob.isUpdateQueueReadyFlag()|| !currentJob.getMetadatosAActualizar().isEmpty()) {
				
				MetadataProxy metadataToUpdate = currentJob.getMetadatosAActualizar().take();
				if(metadataToUpdate!=null){
					doWork(metadataToUpdate, currentJob);			
				}
			}
		
			currentJob.setUpdateQueueReadyFlag(true);
			
		} catch (InterruptedException e) {
			log.error("error", e);
		} catch (GnNotAvailableConnectionException e) {
			log.error("Conexión destino no disponible en este instante. "+e.getMessage());
		}
	}
		
	/**
	 * Método que realiza el trabajo de actualizacion
	 * De momento no se utiliza
	 */
	private void doWork(MetadataProxy metadataToUpdate, Job job) {

		UpdateRequest updateReq = new UpdateRequest (metadataToUpdate.getUuid(),
				Integer.toString(metadataToUpdate.getVersion()),
				"false",
				metadataToUpdate.getTitulo(),
				metadataToUpdate.getData()); //CDATA del GNMetadata como string
		try {
			UpdateResponse updateResp = (UpdateResponse)connection.exec(updateReq);
			
			if(updateResp!=null){
				//Info estadística
				job.getMetadatosActualizados().add(metadataToUpdate);
			}
			
		} catch (GnNotAuthorizedConnectionException e) {
			log.error("No autorizado para la conexión destino en este instante. "+e.getMessage());
		} catch (GnRequestException e) {
			log.error("Error en la solicitud de actualización de metadatos. "+e.getMessage());
		}

	
	}
}