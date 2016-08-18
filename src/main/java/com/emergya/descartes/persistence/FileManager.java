package com.emergya.descartes.persistence;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * The Class FileManager.
 */
public class FileManager {
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
