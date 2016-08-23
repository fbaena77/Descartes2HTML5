package com.emergya.descartes;

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
    * Inicia la aplicacion Descartes2HTML5
    * @param args 
    */
    public static void main(String[] args) {

        try {
            if (args.length != 1) {
                throw new Exception();
            } else {
                PropertyConfigurator.configure(args[0]);
                JobConverter job = initJob(args[0]);
                SearchDescartesContents checkContents = new SearchDescartesContents(
                        job.getJobConfig().getOriginalContentPath());

                if (checkContents.getNumContents() > 0) {
                    initWorkers(job);
                } else {
                    System.console()
                            .writer()
                            .println(
                                    "-----No se han encontrado contenidos Descartes en la ruta especificada en la configuración------");
                }
            }
        } catch (Exception e) {
            String errorArgs = "**No se han pasado todos los argumentos, debe indicar el nombre de un fichero de configuración**\n";
            System.err.print(errorArgs);
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
