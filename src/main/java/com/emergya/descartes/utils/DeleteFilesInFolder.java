package com.emergya.descartes.utils;

import java.io.File;

/**
 * @author fbaena
 *
 */
public class DeleteFilesInFolder {
    /**
     * @param path
     * @return boolean
     */
    public static boolean delete(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    delete(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }

        return (path.delete());
    }
}
