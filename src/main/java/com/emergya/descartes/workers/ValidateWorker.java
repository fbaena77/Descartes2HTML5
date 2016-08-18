package com.emergya.descartes.workers;

import java.io.File;

import org.apache.log4j.Logger;

import com.emergya.descartes.analyzer.ContentAnalyzer;
import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.persistence.OutputManager;

/**
 * 
 * @author fbaena
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
    * Realiza el proceso de validación de los html de los contenidos por la W3C
    */
    @Override
    public void run() {
        try {
            JobConverter currentJob = getJob();
            log.info("----Inicio de la validación final de contenidos----");
            // Control de existencia de carpeta de trabajo
            File resultPath = new File(currentJob.getJobConfig()
                    .getValidationResultPath());
            try {
                if (resultPath.exists()) {
                    FileManager.deleteFilesInFolder(resultPath);
                }
                resultPath.mkdirs();
            } catch (Exception e) {
                log.error("No se ha podido crear la carpeta temporal de validación de contenidos");
            }

            while (!currentJob.isValidateQueueReadyFlag()
                    || !currentJob.getContentsToValidate().isEmpty()) {

                DescartesContentProxy contentToValidate = currentJob
                        .getContentsToValidate().take();

                if (contentToValidate != JobConverter.STOP_QUEUE) {
                    if (contentToValidate != null) {
                        doWork(contentToValidate, currentJob);
                    }
                } else {
                    log.info("-->>Total Validados: "
                            + (currentJob.getContentsValidate().size()));
                    log.info("****Proceso de Validación Finalizado****");
                    currentJob.setValidateQueueReadyFlag(true);
                }
            }

        } catch (InterruptedException e) {
            log.error("Problema de Interrupción. " + e.getMessage(), e);
        }
    }

    /**
     * Método que realiza el trabajo de validación
     */
    private void doWork(DescartesContentProxy contentToValidate,
            JobConverter job) {

        ContentAnalyzer contentAnalyzer = new ContentAnalyzer();
        AnalyzedContent<?> analyzedContent = contentAnalyzer
                .analyzeContent(contentToValidate);
        log.info(">>Validando Contenido: " + contentToValidate.getTitle());

        File validatedContentFile = new File(job.getJobConfig()
                .getValidationResultPath()
                + File.separator
                + contentToValidate.getTitle() + "_W3CResult.csv");
        analyzedContent.setLocalCopy(validatedContentFile);
        OutputManager.createAnalizedContentCSV(analyzedContent);
        log.info(">>Validación guardada para el contenido: "
                + contentToValidate.getTitle());

        // Info estadística
        job.getContentsValidate().add(contentToValidate);
    }
}