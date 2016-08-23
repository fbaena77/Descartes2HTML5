package com.emergya.descartes.workers;

import java.io.File;

import org.apache.log4j.Logger;

import com.emergya.descartes.analyzer.ContentAnalyzer;
import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesZipContentProxy;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.persistence.OutputManager;

/**
 * 
 * @author fbaena
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
            log.info("****INICIO DEL ANÁLISIS DE CONTENIDOS****");
            // Control de existencia de carpeta de trabajo
            File resultPath = new File(currentJob.getJobConfig()
                    .getAnalysisResultPath());

            if (resultPath.exists()) {
                FileManager.deleteFilesInFolder(resultPath);
            }

            resultPath.mkdirs();

            while (!currentJob.isAnalyzeQueueReadyFlag()
                    || !currentJob.getContentsToAnalyze().isEmpty()) {
                DescartesZipContentProxy contentToProcess = currentJob
                        .getContentsToAnalyze().take();
                if (contentToProcess != JobConverter.STOP_QUEUE) {
                    if (contentToProcess != null) {
                        doWork(contentToProcess, currentJob);
                    }
                } else {
                    log.info("-->> Total Analizados: "
                            + (currentJob.getContentsAnalyzed().size()));
                    log.info("****ANÁLISIS FINALIZADO****");
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
        } catch (Exception e) {
            log.error("No se ha podido crear la carpeta temporal de análisis de contenidos");
        }
    }

    /**
     * Método que realiza el trabajo de Análisis
     * 
     * @param zipContent
     * @param job
     */
    private void doWork(DescartesZipContentProxy zipContent, JobConverter job) {
        File contentFolder = FileManager.extractZipContents(zipContent, job);
        DescartesContentProxy contentProxy = new DescartesContentProxy();
        contentProxy.setLocalCopy(contentFolder);
        contentProxy.setTitle(zipContent.getTitle());
        ContentAnalyzer contentAnalyzer = new ContentAnalyzer(job);
        AnalyzedContent<?> analyzedContent = contentAnalyzer
                .analyzeContent(contentProxy);
        log.info(">> Analizando Contenido: " + zipContent.getFileName());

        File analyzedContentFile = new File(job.getJobConfig()
                .getAnalysisResultPath()
                + File.separator
                + contentProxy.getTitle());
        analyzedContent.setLocalCopy(analyzedContentFile);
        String workingPath = job.getJobConfig().getWorkingContentPath();
        OutputManager.createAnalizedContentCSV(analyzedContent, workingPath);
        log.info(">> Análisis guardado para el contenido: "
                + zipContent.getFileName());

        // Info estadística
        job.getContentsAnalyzed().add(zipContent);
    }
}
