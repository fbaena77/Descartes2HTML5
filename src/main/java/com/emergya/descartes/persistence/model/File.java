package com.emergya.descartes.persistence.model;

import java.util.List;
/**
 *
 * @author pankaj
 */
public class File extends java.io.File {

	public File(String pathname) {
		super(pathname);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The group. */
	private String group;
	
	/** The validation. */
	private List<FileValidation> validation;

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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gets the validation.
	 *
	 * @return the validation
	 */
	public List<FileValidation> getValidation() {
		return validation;
	}

	/**
	 * Sets the validation.
	 *
	 * @param validation the new validation
	 */
	public void setValidation(List<FileValidation> validation) {
		this.validation = validation;
	}

	
}
