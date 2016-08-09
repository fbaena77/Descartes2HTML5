package com.emergya.descartes.persistence.model;

import com.emergya.descartes.utils.Message;

/**
 *
 * @author pankaj
 */

public class FileValidation{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The file. */
	private File file;
	
	/** The message. */
	private Message message;
	
	/** The first line. */
	private String firstLine;
	
	/** The first column. */
	private String firstColumn;
	
	/** The last line. */
	private String lastLine;
	
	/** The last column. */
	private String lastColumn;
	
	/** The exract. */
	private String exract;

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Gets the first line.
	 *
	 * @return the first line
	 */
	public String getFirstLine() {
		return firstLine;
	}

	/**
	 * Sets the first line.
	 *
	 * @param firstLine the new first line
	 */
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	/**
	 * Gets the first column.
	 *
	 * @return the first column
	 */
	public String getFirstColumn() {
		return firstColumn;
	}

	/**
	 * Sets the first column.
	 *
	 * @param firstColumn the new first column
	 */
	public void setFirstColumn(String firstColumn) {
		this.firstColumn = firstColumn;
	}

	/**
	 * Gets the last line.
	 *
	 * @return the last line
	 */
	public String getLastLine() {
		return lastLine;
	}

	/**
	 * Sets the last line.
	 *
	 * @param lastLine the new last line
	 */
	public void setLastLine(String lastLine) {
		this.lastLine = lastLine;
	}

	/**
	 * Gets the last column.
	 *
	 * @return the last column
	 */
	public String getLastColumn() {
		return lastColumn;
	}

	/**
	 * Sets the last column.
	 *
	 * @param lastColumn the new last column
	 */
	public void setLastColumn(String lastColumn) {
		this.lastColumn = lastColumn;
	}

	/**
	 * Gets the exract.
	 *
	 * @return the exract
	 */
	public String getExract() {
		return exract;
	}

	/**
	 * Sets the exract.
	 *
	 * @param exract the new exract
	 */
	public void setExract(String exract) {
		this.exract = exract;
	}

	
}
