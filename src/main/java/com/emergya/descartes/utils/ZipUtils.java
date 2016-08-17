package com.emergya.descartes.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.ZipContent;
import com.emergya.descartes.job.JobConverter;

/**
 * @author fbaena
 *
 */
public class ZipUtils {

    private static Logger log = Logger.getLogger(ZipUtils.class);

    @SuppressWarnings("resource")
    public static File doZipDecompress(ZipContent zipContent, JobConverter job) {
        ZipInputStream zis;
        // boolean flag = false;
        boolean creado = false;
        String pathTransformDir = job.getJobConfig().getWorkingContentPath();
        File parentFolder = new File(pathTransformDir
                + zipContent.getFileName());
        if (!parentFolder.exists()) {
            parentFolder.mkdir();
        }
        try {
            zis = new ZipInputStream(new FileInputStream(
                    zipContent.getFileName()));
            ZipEntry entrada;
            while (null != (entrada = zis.getNextEntry())) {
                File folder = new File(parentFolder, entrada.getName());
                boolean esDirectorio = entrada.isDirectory();
                if (!folder.exists() && esDirectorio) {
                    creado = folder.mkdirs();
                }
                if (creado) {
                    if (!esDirectorio) {
                        FileOutputStream fos = new FileOutputStream(
                                pathTransformDir + zipContent.getFileName()
                                        + "/" + entrada.getName());
                        int leido;
                        byte[] buffer = new byte[1024];
                        while (0 < (leido = zis.read(buffer))) {
                            fos.write(buffer, 0, leido);
                        }
                        fos.close();
                    }
                } else {
                    throw new IOException();
                }
            }
            zis.closeEntry();
        } catch (FileNotFoundException e) {
            log.error(
                    "No se ha encontrado el fichero indicado" + e.getMessage(),
                    e);
        } catch (IOException e) {
            log.error("Tratamiento de archivos incorrecto" + e.getMessage(), e);
        }

        return parentFolder;
    }

    /**
     * Método que comprime los metadatos a *.zip
     * @param contentPoxy
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void doZipCompress(DescartesContentProxy contentPoxy,
            JobConverter job) throws IOException, FileNotFoundException {

        File zipFile = new File(job.getJobConfig().getConvertedContentPath()
                + contentPoxy.getTitulo() + ".zip");
        File dirToZip = new File(job.getJobConfig().getConvertedContentPath()
                + contentPoxy.getTitulo());
        zip(dirToZip, zipFile);
    }

    /**
     * Método que comprime un directorio en un zip
     * @param directory
     * @param zipfile
     * @throws IOException
     */
    @SuppressWarnings("resource")
    private static void zip(File directory, File zipfile) throws IOException {
        URI base = directory.toURI();
        Deque<File> queue = new LinkedList<File>();
        queue.push(directory);
        OutputStream out = new FileOutputStream(zipfile);
        Closeable res = out;
        try {
            ZipOutputStream zout = new ZipOutputStream(out);
            res = zout;
            while (!queue.isEmpty()) {
                directory = queue.pop();
                for (File kid : directory.listFiles()) {
                    String name = base.relativize(kid.toURI()).getPath();
                    if (kid.isDirectory()) {
                        queue.push(kid);
                        name = name.endsWith("/") ? name : name + "/";
                        zout.putNextEntry(new ZipEntry(name));
                    } else {
                        zout.putNextEntry(new ZipEntry(name));
                        copy(kid, zout);
                        zout.closeEntry();
                    }
                }
            }
        } finally {
            res.close();
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

    private static void copy(File file, OutputStream out) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            copy(in, out);
        } finally {
            in.close();
        }
    }
}
