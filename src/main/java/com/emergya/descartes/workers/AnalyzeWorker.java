package com.emergya.descartes.workers;

import java.io.File;

import org.apache.log4j.Logger;

import com.emergya.descartes.analyzer.ContentAnalyzer;
import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.ZipContent;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.persistence.OutputManager;
import com.emergya.descartes.utils.ZipUtils;

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
            log.info("----Inicio del análisis de contenidos----");
            // Control de existencia de carpeta de trabajo
            File resultPath = new File(currentJob.getJobConfig()
                    .getAnalysisResultPath());

            if (resultPath.exists()) {
                FileManager.deleteFilesInFolder(resultPath);
            }
            resultPath.mkdirs();

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
    private DescartesContentProxy doWork(ZipContent zipContent, JobConverter job) {
        DescartesContentProxy contentProxy = new DescartesContentProxy();
        File contentFolder = ZipUtils.doZipDecompress(zipContent, job);
        contentProxy.setLocalCopy(contentFolder);
        contentProxy.setTitle(zipContent.getFileName());

        ContentAnalyzer contentAnalyzer = new ContentAnalyzer();
        AnalyzedContent<?> analyzedContent = contentAnalyzer
                .analyzeContent(contentProxy);
        log.info(">>Analizando Contenido: " + zipContent.getFileName());

        File analyzedContentFile = new File(job.getJobConfig()
                .getAnalysisResultPath()
                + File.separator
                + contentProxy.getTitle() + "_W3CResult.csv");
        analyzedContent.setLocalCopy(analyzedContentFile);
        OutputManager.createAnalizedContentCSV(analyzedContent);
        log.info(">>Análisis guardado para el contenido: "
                + zipContent.getFileName());

        // Info estadística
        job.getContentsAnalyzed().add(zipContent);
        return contentProxy;
    }
}
