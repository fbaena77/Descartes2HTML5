package com.emergya.descartes.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesZipContentProxy;
import com.emergya.descartes.job.JobConverter;

/**
 * The Class FileManager.
 */
public class FileManager {

    private static Logger log = Logger.getLogger(FileManager.class);

    public static File extractZipContents(DescartesZipContentProxy zipContent,
            JobConverter job) {
        String pathTransformDir = job.getJobConfig().getWorkingContentPath();
        File outputFolder = new File(pathTransformDir + File.separator
                + zipContent.getTitle());
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipContent.getFileName().toFile());
            // get an enumeration of the ZIP file entries
            Enumeration<? extends ZipEntry> e = zipFile.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();
                File destinationPath = new File(outputFolder, entry.getName());
                // create parent directories
                destinationPath.getParentFile().mkdirs();
                // if the entry is a file extract it
                if (entry.isDirectory()) {
                    continue;
                } else {
                    BufferedInputStream bis = new BufferedInputStream(
                            zipFile.getInputStream(entry));
                    int b;
                    byte buffer[] = new byte[1024];
                    FileOutputStream fos = new FileOutputStream(destinationPath);
                    BufferedOutputStream bos = new BufferedOutputStream(fos,
                            1024);
                    while ((b = bis.read(buffer, 0, 1024)) != -1) {
                        bos.write(buffer, 0, b);
                    }
                    bos.close();
                    bis.close();
                }
            }

        } catch (IOException e) {
            log.error(
                    "Error al descomprimir el contenido "
                            + zipContent.getTitle() + ": " + e, e);
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException ioe) {
                log.error(
                        "Error al descomprimir el contenido "
                                + zipContent.getTitle() + ": " + ioe, ioe);
            }
        }

        return outputFolder;
    }

    /**
     * Método que comprime los metadatos a *.zip
     * @param contentPoxy
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void compressZipContent(DescartesContentProxy contentPoxy,
            JobConverter job) throws IOException, FileNotFoundException {

        File zipFile = new File(job.getJobConfig().getConvertedContentPath()
                + contentPoxy.getTitle() + ".zip");
        File dirToZip = new File(job.getJobConfig().getConvertedContentPath()
                + contentPoxy.getTitle());
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

    /**
     * Crea una copia de un fichero.
     *
     * @param sourceFilePath the source file path
     * @param destinationPath the destination path
     * @param fileName the file name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void copy(String sourceFilePath, String destinationPath,
            String fileName) throws IOException {
        File destinationPathObject = new File(destinationPath);
        File sourceFilePathObject = new File(sourceFilePath);
        if (destinationPathObject.isDirectory()) {
            File statusFileNameObject = new File(destinationPath + "/"
                    + fileName);
            if (statusFileNameObject.isFile()) {
                statusFileNameObject.delete();
                FileUtils.copyFile(sourceFilePathObject, statusFileNameObject);
            }
        }
    }

    /**
     * Exist.
     *
     * @param path the path
     * @return true, if successful
     */
    public static boolean exist(String path) {
        final File file = new File(path);
        return file.exists();
    }

    /**
     * Exist.
     *
     * @param folderPath the folder path
     * @param fileName the file name
     * @return true, if successful
     */
    public static boolean exist(String folderPath, final String fileName) {
        boolean exist = false;
        final File folder = new File(folderPath);

        String[] list = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(fileName);
            }
        });

        exist = list != null && list.length > 0;

        return exist;
    }

    /**
     * @param path
     * @return boolean
     */
    public static boolean deleteFilesInFolder(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteFilesInFolder(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }

        return (path.delete());
    }
}
