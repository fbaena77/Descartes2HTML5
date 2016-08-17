package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import org.apache.log4j.Logger;

import com.emergya.descartes.job.JobConverter;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de exportacion.
 * Se basa en el uso del servicio xml.search de Geonetwork.
 *   
 * @author root
 *
 */
public class ValidateWorker extends BaseWorker implements Runnable {

    private static Logger log = Logger.getLogger(ValidateWorker.class);

    /**
     * @param job
     */
    public ValidateWorker(JobConverter job) {
        super(job);
    }

    /**
    * Realiza el proceso de procesamiento de metadatos
    * @author Federico Baena (Fujitsu)
    */
    @Override
    public void run() {
        try {

            JobConverter currentJob = getJob();
            connection = currentJob.getJobConfig().getPoolGNDestino()
                    .getConnectionThreadSafe();

            while (!currentJob.isProcessQueueReadyFlag()
                    || !currentJob.getMetadatosAProcesar().isEmpty()) {

                MetadataProxy metadataToProcess = currentJob
                        .getMetadatosAProcesar().take();

                if (metadataToProcess != Job.STOP_QUEUE) {
                    if (metadataToProcess != null) {
                        doWork(metadataToProcess, currentJob);
                    }
                } else {
                    log.info("-->>Total Privilegios Procesados en el GN en "
                            + currentJob.getServerDestino().getHost() + ": "
                            + currentJob.getMetadatosProcesados().size());
                    log.info("****Procesamiento de datos finalizado****");
                    currentJob.setProcessQueueReadyFlag(true);
                }
            }

        } catch (InterruptedException e) {
            log.error("Problema de Interrupción. " + e.getMessage(), e);
        } catch (GnNotAvailableConnectionException e) {
            log.error(
                    "Conexión destino no disponible en este instante. "
                            + e.getMessage(), e);
        }
    }

    /**
     * Método que realiza el trabajo de procesamiento
     * 
     */
    private void doWork(MetadataProxy metadataToProcess, Job job) {
        PrivilegesRequest privilReq = new PrivilegesRequest(
                metadataToProcess.getUuid(), job.getJobConfig()
                        .getPrivilegesToProcess());
        try {
            PrivilegesResponse privilResp = (PrivilegesResponse) connection
                    .exec(privilReq);

            if (privilResp != null) {
                // Info estadística
                job.getMetadatosProcesados().add(metadataToProcess);
                log.info(">>Modificados Privilegios al metadato: "
                        + metadataToProcess.getUuid());
            }

        } catch (GnNotAuthorizedConnectionException e) {
            log.error(
                    "No autorizado para la conexión destino en este instante. "
                            + e.getMessage(), e);
        } catch (GnRequestException e) {
            log.error("[Error]" + e.getMessage(), e);
        } catch (NullPointerException e) {
            // FIXME Revisar este nullpointer
            log.warn("El Dato con uuid :" + e + " ya existe", e);
        }
    }
}