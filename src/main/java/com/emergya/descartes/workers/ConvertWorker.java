package es.juntadeandalucia.cmaot.geoinspire.exportador.workers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.utils.Constants;
import com.emergya.descartes.utils.DeleteFilesInFolder;

/**
 * 
 * Prepara el entorno de trabajo para realizar el proceso de transformación
 * 
 * @author root
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

        JobConverter currentJob = getJob();

        log.info("----Inicio de la transformación de metadatos----");

        // Control de la carpeta de trabajo
        File theDir = new File(currentJob.getJobConfig()
                .getTransformTempLocalPath());
        try {
            if (theDir.exists()) {
                DeleteFilesInFolder.delete(theDir);
            }
            theDir.mkdirs();
        } catch (Exception e) {
            String fatalError = "No se ha podido crear la carpeta temporal de transformación de metadatos";
            log.error(fatalError);
            throw new Error(fatalError);
        }

        while (!currentJob.isTransformQueueReadyFlag()
                || !currentJob.getMetadatosATransformar().isEmpty()) {
            try {
                MetadataProxy metadataToTransform = currentJob
                        .getMetadatosATransformar().take();
                if (metadataToTransform != Job.STOP_QUEUE) {
                    if (metadataToTransform != null) {
                        try {
                            doWork(metadataToTransform, currentJob);
                        } catch (ParserConfigurationException e) {
                            log.error("Ha ocurrido un error en la transformación de metadatos. "
                                    + e.getMessage());
                        } catch (SAXException e) {
                            log.error("Ha ocurrido un error en la transformación de metadatos. "
                                    + e.getMessage());
                        }
                    }
                } else {
                    currentJob.getMetadatosAImportar().put(Job.STOP_QUEUE);
                    currentJob.setTransformQueueReadyFlag(true);
                    log.info("-->>Total Transformados: "
                            + (currentJob.getMetadatosTransformados().size()));
                }

            } catch (InterruptedException e) {
                log.error("Interrupción de la cola de transformación. "
                        + e.getMessage());
                try {
                    currentJob.getMetadatosAImportar().put(Job.STOP_QUEUE);
                } catch (InterruptedException e1) {
                    log.error("error", e1);
                }
            }
        }
    }

    /**
     * Método que realiza el trabajo de transformacion
     * @param metadataToTransform
     * @param job
     * @throws InterruptedException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    private void doWork(MetadataProxy metadataToTransform, Job job)
            throws InterruptedException, ParserConfigurationException,
            SAXException {

        try {

            File parentFolder = doMefDecompress(metadataToTransform, job);

            // Aplicamos las transformaciones XSL
            if (job.getJobConfig().isToTransformMEF()) {
                aplicaXSLT(job, metadataToTransform,
                        parentFolder.getAbsolutePath() + "/", job
                                .getJobConfig().isToModifUUID());
            }
            // ¿Hay que cambiar el uuid? Parametrizable
            /*
             * if(job.getJobConfig().isToModifUUID()){
             * generarUUIDMetadataXml(metadataToTransform,
             * parentFolder.getAbsolutePath() + "/"); }
             */

            File theDir2 = new File(job.getJobConfig()
                    .getImportAfterTransfTempLocalPath());
            try {
                if (!theDir2.exists()) {
                    theDir2.mkdir();
                }
            } catch (Exception e) {
                log.error(
                        "No se ha podido crear la carpeta temporal de importación de metadatos",
                        e);
                job.getMetadatosAImportar().put(Job.STOP_QUEUE);
            }

            // Volvemos a comprimir el mef
            doMefCompress(metadataToTransform);

            // Asignamos el contenido del fichero al metadato a importar
            File metadataFileCopy = new File(job.getJobConfig()
                    .getImportAfterTransfTempLocalPath()
                    + metadataToTransform.getUuid() + Constants._MEF);
            metadataToTransform.setLocalCopy(metadataFileCopy);
            job.getMetadatosAImportar().put(metadataToTransform);

            // Estadistica
            job.getMetadatosTransformados().add(metadataToTransform);

        } catch (InterruptedException e) {
            log.error("Interrupción de la cola de transformación. "
                    + e.getMessage());
            job.getMetadatosAImportar().put(Job.STOP_QUEUE);
        } catch (FileNotFoundException e) {
            log.error(
                    "No se ha encontrado el fichero indicado" + e.getMessage(),
                    e);
            job.getMetadatosAImportar().put(Job.STOP_QUEUE);
        } catch (IOException e) {
            log.error("Tratamiento de archivos incorrecto" + e.getMessage(), e);
        }
    }
}