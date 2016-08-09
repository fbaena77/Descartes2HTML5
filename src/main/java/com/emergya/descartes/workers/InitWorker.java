package com.emergya.descartes.workers;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.emergya.descartes.job.JobConverter;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de conversión.
 *   
 * @author fbaena
 *
 */
public class InitWorker extends BaseWorker implements Runnable {

    private static Logger log = Logger.getLogger(InitWorker.class);

    /**
     * @param job
     */
    public InitWorker(JobConverter job) {
        super(job);
    }

    /**
    * Realiza el proceso de carga de contenidos e inicia los hilos de análisis, conversión y validación
    *  
    * @author fbaena
    */
    @Override
    public void run() {
        try {

            SearchRequest searchReq = new SearchRequest(getJob().getJobConfig()
                    .getSearchMapSet());
            GnIConnection connection = getJob().getJobConfig()
                    .getPoolGNOrigen().getConnectionThreadSafe();

            SearchResponse execSearch = (SearchResponse) connection
                    .exec(searchReq);

            if (execSearch != null && execSearch.getSummary().getCount() > 0) {
                // El trabajo de exportación se lanza siempre porque en este
                // punto siempre hay resultados que exportar
                (new Thread(new ExportWorker(getJob()))).start();

                // El trabajo de transformación se lanza bajo dos condiciones:
                // 1. Que haya que aplicar una XSL
                // 2. Que haya que modificar el uuid del metadato usando el
                // generado por GN
                if (getJob().getJobConfig().isToTransformMEF()) {
                    (new Thread(new TransformWorker(getJob()))).start();
                }

                // El trabajo de importación se lanza siempre porque en este
                // punto siempre hay resultados que importar
                Thread importThread = new Thread(new ImportWorker(getJob()));
                importThread.start();

                // Si la importación es al GN de acceso público
                if (getJob().getJobConfig().isToPublicNode()) {
                    // Se inicia el algoritmo de publicación de metadatos en el
                    // GN de destino
                    doWorkToPublico(execSearch, getJob());
                    // Estos threads se lanzarán sólo si se va a realizar una
                    // publicación en el GN PUBLICO
                    (new Thread(new EraserWorker(getJob()))).start();
                    // Este thread continúa el algoritmo de publicación
                    // evaluando los metadatos en el GN de destino
                    (new Thread(new PublishWorker(getJob()))).start();

                } else if (!getJob().getJobConfig().isToPublicNode()) {
                    doWorkToInterno(execSearch, getJob());
                } else {
                    connection.close();
                    return;
                }

                synchronized (importThread) {
                    try {
                        importThread.wait();
                    } catch (InterruptedException e) {
                        log.error("error", e);
                    }
                }

                if (getJob().getJobConfig().isToProcessPrivileges()) {
                    Thread processThread = new Thread(new ProcessingWorker(
                            getJob()));
                    processThread.start();

                    synchronized (processThread) {
                        try {
                            processThread.wait();
                        } catch (InterruptedException e) {
                            log.error("error", e);
                        }
                    }
                }

                // Borrado de carpetas temporales
                if (getJob().getJobConfig().isToDeleteExportFolder()) {
                    File theDir = new File(getJob().getJobConfig()
                            .getExportLocalPath());
                    DeleteFilesInFolder.delete(theDir);
                }
                if (getJob().getJobConfig().isToDeleteTransformFolder()) {
                    File theDir = new File(getJob().getJobConfig()
                            .getTransformTempLocalPath());
                    DeleteFilesInFolder.delete(theDir);
                }
                if (getJob().getJobConfig().isToDeleteImportFolder()) {
                    File theDir = new File(getJob().getJobConfig()
                            .getImportAfterTransfTempLocalPath());
                    DeleteFilesInFolder.delete(theDir);
                }

                log.info("---------------------------------PROCESO DE EXPORTACIÓN FINALIZADO---------------------------------");

            } else {
                log.warn("No se ha podido obtener ningún resultado en la búsqueda en el GN Origen.");
                connection.close();
                return;
            }
        } catch (GnRequestException e) {
            log.error(
                    "No se ha podido conectar con el GN Origen. "
                            + e.getMessage(), e);
        } catch (GnNotAvailableConnectionException e) {
            log.error(
                    "Conexión destino no disponible en este instante. "
                            + e.getMessage(), e);
        } catch (GnNotAuthorizedConnectionException e) {
            log.error(
                    "No autorizado para la conexión destino en este instante. "
                            + e.getMessage(), e);
        }
    }

    /**
     * Método que inicializa la cola de exportación para el GN de destino INTERNO
     * @param execSearch
     * @param job
     */
    private void doWorkToInterno(SearchResponse execSearch, Job job) {

        ArrayList<GnMetadata> listaMetadatos = (ArrayList<GnMetadata>) execSearch
                .getMetadata();
        try {
            for (GnMetadata metadato : listaMetadatos) {
                MetadataProxy metadataAExportar = new MetadataProxy();
                metadataAExportar.setUuid(metadato.getInfo().getUuid());
                metadataAExportar.setChangeDate(metadato.getInfo()
                        .getChangeDate());

                job.getMetadatosAExportar().put(metadataAExportar);
            }

            log.info("-->>Número Total de Metadatos: "
                    + (job.getMetadatosAExportar().size() + 1));
            // Añadimos a la cola la poison pill para consumir el thread cuando
            // llegue a ella
            job.getMetadatosAExportar().put(Job.STOP_QUEUE);

        } catch (InterruptedException e) {
            log.error("Problema de Interrupción. " + e.getMessage(), e);
            try {
                job.getMetadatosAExportar().put(Job.STOP_QUEUE);
            } catch (InterruptedException e1) {
                log.error("Problema de Interrupción. " + e.getMessage(), e);
            }
        }
    }

    /**
     * Método que inicializa el algoritmo de publicación en el GeonetWork PUBLICO
     * @param execSearch
     * @param job
     */
    private void doWorkToPublico(SearchResponse execSearch, Job job) {

        ArrayList<GnMetadata> listaMetadatos = (ArrayList<GnMetadata>) execSearch
                .getMetadata();
        try {
            for (GnMetadata metadato : listaMetadatos) {
                job.getMetadatosAPublicar().put(metadato);
            }
            log.info("-->>Número Total de Metadatos a publicar: "
                    + (job.getMetadatosAPublicar().size()));
            // Añadimos a la cola la poison pill para consumir el thread cuando
            // llegue a ella
            job.getMetadatosAPublicar().put(Job.STOP_QUEUE_GNMETADATA);

        } catch (InterruptedException e) {
            log.error("Problema de Interrupción. " + e.getMessage(), e);
            try {
                job.getMetadatosAPublicar().put(Job.STOP_QUEUE_GNMETADATA);
            } catch (InterruptedException e1) {
                log.error("Problema de Interrupción. " + e1.getMessage(), e);
            }
        }
    }
}
