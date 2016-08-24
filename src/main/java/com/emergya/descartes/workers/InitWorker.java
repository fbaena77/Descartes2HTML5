package com.emergya.descartes.workers;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesZipContentProxy;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.job.SearchDescartesContents;
import com.emergya.descartes.persistence.FileManager;

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

        boolean isToConvert = false;
        if (getJob().getJobConfig().isToAnalyzeOrConvert()
                .equalsIgnoreCase("analyze")) {
            (new Thread(new AnalyzeWorker(getJob()))).start();
        } else if (getJob().getJobConfig().isToAnalyzeOrConvert()
                .equalsIgnoreCase("convert")) {
            (new Thread(new ConvertWorker(getJob()))).start();
            isToConvert = true;
            if (getJob().getJobConfig().isToW3CValidate()) {
                (new Thread(new ValidateWorker(getJob()))).start();
            }
        } else {
            log.error("*La configuración no es válida: el parámetro 'general.analyzeORconvert' sólo acepta los valores 'analyze' o 'convert'*");
        }

        SearchDescartesContents zipDescartesContents = new SearchDescartesContents(
                getJob().getJobConfig().getOriginalContentPath());

        // Control de existencia de carpeta de trabajo
        File resultPath = new File(getJob().getJobConfig()
                .getWorkingContentPath());

        if (resultPath.exists()) {
            FileManager.deleteFilesInFolder(resultPath);
        }

        resultPath.mkdirs();

        doWork(getJob(), zipDescartesContents, isToConvert);
    }

    /**
     * Método que inicializa la cola de análisis de contenidos
     * @param ZipFilesNames
     * @param job
     */
    private void doWork(JobConverter job,
            SearchDescartesContents zipDescartesContents, boolean isToConvert) {
        ArrayList<DescartesZipContentProxy> listaContenidos = (ArrayList<DescartesZipContentProxy>) zipDescartesContents
                .getDescartesZipContentProxyListNames();
        try {
            if (isToConvert) {
                for (DescartesZipContentProxy zipFile : listaContenidos) {
                    job.getContentsToConvert().put(zipFile);
                }
            } else {
                for (DescartesZipContentProxy zipFile : listaContenidos) {
                    job.getContentsToAnalyze().put(zipFile);
                }
            }
            log.info("-->> Número Total de Contenidos: "
                    + (listaContenidos.size()));
            // Añadimos a la cola la poison pill para consumir el thread cuando
            // llegue a ella
            job.getContentsToAnalyze().put(JobConverter.STOP_QUEUE);
            job.getContentsToConvert().put(JobConverter.STOP_QUEUE);
        } catch (InterruptedException e) {
            log.error("Error de Interrupción. " + e.getMessage(), e);
            try {
                job.getContentsToAnalyze().put(JobConverter.STOP_QUEUE);
                job.getContentsToConvert().put(JobConverter.STOP_QUEUE);
            } catch (InterruptedException e1) {
                log.error("Error de Interrupción. " + e.getMessage(), e);
            }
        }
    }
}
