package com.emergya.descartes.workers;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.ZipContent;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.job.SearchDescartesContents;

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
     * @param checkContents 
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

        if (getJob().getJobConfig().isToAnalyze()) {
            (new Thread(new AnalyzeWorker(getJob()))).start();
        }

        if (getJob().getJobConfig().isToConvert2HTML5()) {
            (new Thread(new ConvertWorker(getJob()))).start();
        }

        if (getJob().getJobConfig().isToAnalyze()) {
            (new Thread(new ValidateWorker(getJob()))).start();
        }

        SearchDescartesContents zipFilesNames = new SearchDescartesContents(
                getJob().getJobConfig().getOriginalContentPath());

        doWork(getJob(), zipFilesNames);

        log.info("---------------------------------PROCESO FINALIZADO---------------------------------");
    }

    /**
     * Método que inicializa la cola de conversión de contenidos
     * @param execSearch
     * @param job
     */
    private void doWork(JobConverter job, SearchDescartesContents ZipFilesNames) {
        ArrayList<ZipContent> listaContenidos = (ArrayList<ZipContent>) ZipFilesNames
                .getZipContentListNames();
        try {
            for (ZipContent zipFile : listaContenidos) {
                job.getContentsToAnalyze().put(zipFile);
            }
            log.info("-->>Número Total de Contenidos: "
                    + (job.getContentsToAnalyze().size() + 1));
            // Añadimos a la cola la poison pill para consumir el thread cuando
            // llegue a ella
            job.getContentsToAnalyze().put(JobConverter.STOP_QUEUE_ZIP);
        } catch (InterruptedException e) {
            log.error("Error de Interrupción. " + e.getMessage(), e);
            try {
                job.getContentsToAnalyze().put(JobConverter.STOP_QUEUE_ZIP);
            } catch (InterruptedException e1) {
                log.error("Error de Interrupción. " + e.getMessage(), e);
            }
        }
    }
}
