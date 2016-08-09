package com.emergya.descartes;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.emergya.descartes.job.JobConfiguration;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.job.SearchDescartesContents;
import com.emergya.descartes.workers.InitWorker;

/**
 * @author fbaena
 *
 */
public class Main {
    /**
     * 
     */
    private static final Logger log = Logger.getLogger(Main.class);

    /**
    * Inicia la aplicacion Descartes2HTML5
    * @param args 
    */
    public static void main(String[] args) {

        try {
            PropertyConfigurator.configure(args[0]);
            if (args.length != 1) {
                String errorArgs = "Es obligatorio indicar un fichero de configuración";
                log.debug(errorArgs);
                throw new Exception(
                        "No se han pasado todos los argumentos, debe indicar el nombre de un fichero de configuración");
            } else {

                JobConverter job = initJob(args[0]);
                SearchDescartesContents checkContents = new SearchDescartesContents(
                        job.getJobConfig().getOriginalContentPath());

                if (checkContents.getNumContents() > 0) {
                    log.info("---------------------------------INICIO DEL PROCESO DE CONVERSIÓN---------------------------------");
                    initWorkers(job);
                } else {
                    log.info("---------------------------------NO SE HAN ENCONTRADO CONTENIDOS DESCARTES EN LA RUTA ESPECIFICADA---------------------------------");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static JobConverter initJob(String configFile) {
        JobConfiguration config = new JobConfiguration(configFile);
        JobConverter job = new JobConverter(config);
        return job;
    }

    private static void initWorkers(JobConverter job) {
        InitWorker initWorker = new InitWorker(job);
        Thread thread = new Thread(initWorker);
        thread.start();
    }
}
