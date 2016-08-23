package com.emergya.descartes.workers;

import java.io.File;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesZipContentProxy;
import com.emergya.descartes.converter.Html5FileConverter;
import com.emergya.descartes.converter.model.ConvertedContent;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.persistence.OutputManager;

/**
 * @author fbaena
 * 
 */
public class ConvertWorker extends BaseWorker implements Runnable {

    private static Logger log = Logger.getLogger(ConvertWorker.class);

    /**
     * @param job
     */
    public ConvertWorker(JobConverter job) {
        super(job);
    }

    /**
     * Realiza el proceso de transformación de metadatos
     */
    @Override
    public void run() {
        try {
            JobConverter currentJob = getJob();
            log.info("****INICIO DEL PROCESO DE CONVERSIÓN DE CONTENIDOS****");
            // Control de existencia de carpeta de trabajo
            File resultPath = new File(currentJob.getJobConfig()
                    .getConvertedContentPath());

            if (resultPath.exists()) {
                FileManager.deleteFilesInFolder(resultPath);
            }
            resultPath.mkdirs();

            while (!currentJob.isConvertQueueReadyFlag()
                    || !currentJob.getContentsToConvert().isEmpty()) {

                DescartesZipContentProxy contentToConvert = currentJob
                        .getContentsToConvert().take();

                DescartesContentProxy contentConverted = new DescartesContentProxy();

                if (contentToConvert != JobConverter.STOP_QUEUE) {
                    if (contentToConvert != null) {
                        contentConverted = doWork(contentToConvert, currentJob);
                    }

                    if (currentJob.getJobConfig().isToW3CValidate()) {
                        currentJob.getContentsToValidate()
                                .put(contentConverted);
                    }

                } else {
                    currentJob.getContentsToValidate().put(
                            JobConverter.STOP_QUEUE_V);
                    currentJob.setConvertQueueReadyFlag(true);
                    log.info("-->> Total contenidos convertidos: "
                            + (currentJob.getContentsConverted().size()));

                    log.info("****CONVERSIÓN FINALIZADA****");
                }
            }
            log.info("Contenidos que han provocado errores: "
                    + currentJob.getContentsConvertedError().size());
        } catch (InterruptedException e) {
            log.error("Interrupción de la cola de transformación. "
                    + e.getMessage());
            try {
                getJob().getContentsToValidate().put(JobConverter.STOP_QUEUE_V);
            } catch (InterruptedException e1) {
                log.error("error", e1);
            }
        } catch (Exception e) {
            log.error("No se ha podido crear la carpeta de conversión de contenidos");
        }
    }

    /**
     * Método que realiza el trabajo de conversión de un contenido
     * @param <T>
     * @param contentToConvert
     * @param job
     */
    private DescartesContentProxy doWork(
            DescartesZipContentProxy contentToConvert, JobConverter job)
            throws InterruptedException {
        File contentFolder = FileManager.extractZipContents(contentToConvert,
                job);
        DescartesContentProxy contentProxy = new DescartesContentProxy();
        contentProxy.setLocalCopy(contentFolder);
        contentProxy.setTitle(contentToConvert.getTitle());
        File filesConvertedPath = new File(job.getJobConfig()
                .getConvertedContentPath()
                + File.separator
                + contentProxy.getTitle());

        Html5FileConverter html5FileConverter = new Html5FileConverter(job);
        ConvertedContent<?> convertedContent = html5FileConverter
                .convertContentToHtml5(contentProxy, filesConvertedPath);
        log.info(">> Convirtiendo Contenido: " + contentToConvert.getTitle());

        File convertedContentLogFile = new File(filesConvertedPath.toString()
                + File.separator + "descartes2Html5_summary.csv");

        convertedContent.setLogFile(convertedContentLogFile);
        OutputManager.createLogConvertedContentCSV(convertedContent);
        log.info(">> Sumario de conversión guardado para el contenido: "
                + contentToConvert.getTitle());
        // Estadistica
        job.getContentsConverted().add(contentToConvert);

        return convertedContent.getContentProxy();
    }
}