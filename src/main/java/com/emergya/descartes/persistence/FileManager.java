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
	 * @param sourceFilePath            the source file path
	 * @param destinationPath            the destination path
	 * @param fileName            the file name
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public static void copy(String sourceFilePath,
			String destinationPath, String fileName) throws IOException {
		File destinationPathObject = new File(destinationPath);
		File sourceFilePathObject = new File(sourceFilePath);
		if (destinationPathObject.isDirectory())
		// both source and destination paths are available
		{
			// creating object for File class
			File statusFileNameObject = new File(destinationPath + "/"
					+ fileName);
			if (statusFileNameObject.isFile())
			// Already file is exists in Destination path
			{
				// deleted File
				statusFileNameObject.delete();
				// paste file from source to Destination path with fileName as
				// value of fileName argument
				FileUtils.copyFile(sourceFilePathObject, statusFileNameObject);
			}
			// File is not exists in Destination path.
			{
				// paste file from source to Destination path with fileName as
				// value of fileName argument
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
	public static boolean exist(String path){
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
	public static boolean exist(String folderPath, final String fileName){
		boolean exist = false;
		final File folder = new File(folderPath);
		
		String[] list = folder.list(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.contains(fileName);
		    }
		});
		
		exist = list!=null && list.length>0;
		
		return exist;
	}
}
