package com.emergya.descartes.workers;

import java.io.File;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.ZipContent;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.utils.DeleteFilesInFolder;
import com.emergya.descartes.utils.ZipUtils;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de exportacion. Se
 * basa en el uso del servicio mef.export de Geonetwork.
 * 
 * @author root
 * 
 */
public class AnalyzeWorker extends BaseWorker implements Runnable {

    private Logger log = Logger.getLogger(AnalyzeWorker.class);

    /**
     * @param job
     */
    public AnalyzeWorker(JobConverter job) {
        super(job);
    }

    /**
     * Realiza el proceso de análisis de contenidos
     * 
     * @author fbaena
     */
    @Override
    public void run() {
        try {
            JobConverter currentJob = getJob();

            // Control de existencia de carpeta de trabajo
            File resultPath = new File(currentJob.getJobConfig()
                    .getValidationResultPath());
            try {
                if (resultPath.exists()) {
                    DeleteFilesInFolder.delete(resultPath);
                }
                resultPath.mkdirs();
            } catch (Exception e) {
                log.error("No se ha podido crear la carpeta temporal de análisis de contenidos");
            }

            boolean toConvert2HTML5 = currentJob.getJobConfig()
                    .isToConvert2HTML5();

            while (!currentJob.isAnalyzeQueueReadyFlag()
                    || !currentJob.getContentsToAnalyze().isEmpty()) {
                ZipContent contentToProcess = currentJob.getContentsToAnalyze()
                        .take();
                if (contentToProcess != JobConverter.STOP_QUEUE_ZIP) {
                    if (contentToProcess != null) {
                        DescartesContentProxy contentToConvert = new DescartesContentProxy();
                        contentToConvert = doWork(contentToProcess, currentJob);
                        if (toConvert2HTML5) {
                            currentJob.getContentsToConvert().put(
                                    contentToConvert);
                        }
                    }
                } else {
                    if (toConvert2HTML5) {
                        currentJob.getContentsToConvert().put(
                                JobConverter.STOP_QUEUE);
                    }

                    log.info("-->>Total Analizados: "
                            + (currentJob.getContentsAnalyzed().size()));
                    log.info("****Proceso de Análisis Finalizado****");
                    currentJob.setAnalyzeQueueReadyFlag(true);
                }
            }

            log.info("Contenidos que han provocado errores: "
                    + currentJob.getContentsAnalyzedError().size());

        } catch (InterruptedException e1) {
            log.error("Interrupción de la cola de exportación. "
                    + e1.getMessage());
            try {
                getJob().getContentsToConvert().put(JobConverter.STOP_QUEUE);
            } catch (InterruptedException e) {
                log.error("Interrupción de la cola de análisis. "
                        + e1.getMessage());
            }
        }
    }

    /**
     * Método que realiza el trabajo de Análisis
     * 
     * @param zipContent
     * @param job
     */
    private DescartesContentProxy doWork(ZipContent zipContent, JobConverter job) {
        DescartesContentProxy contentProxy = new DescartesContentProxy();
        File contentFolder = ZipUtils.doZipDecompress(zipContent, job);
        contentProxy.setLocalCopy(contentFolder);
        contentProxy.setTitle(zipContent.getFileName());

        // TODO analizar

        log.info(">>Analizando Contenido: " + zipContent.getFileName());
        // Info estadística
        job.getContentsAnalyzed().add(zipContent);
        return contentProxy;
    }
}
