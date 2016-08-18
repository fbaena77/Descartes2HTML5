package com.emergya.descartes.analyzer.model;

/**
 *
 * @author pankaj
 */
public class W3CResponse {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The type. */
    private String type;

    /** The message. */
    private String message;

    /** The solution. */
    private String solution;

    /** The extract. */
    private String extract;

    /** The first line. */
    private String firstLine;

    /** The first column. */
    private String firstColumn;

    /** The last line. */
    private String lastLine;

    /** The last column. */
    private String lastColumn;

    /**
     * @return the extract
     */
    public String getExtract() {
        return extract;
    }

    /**
     * @param extract the extract to set
     */
    public void setExtract(String extract) {
        this.extract = extract;
    }

    /**
     * @return the firstLine
     */
    public String getFirstLine() {
        return firstLine;
    }

    /**
     * @param firstLine the firstLine to set
     */
    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    /**
     * @return the firstColumn
     */
    public String getFirstColumn() {
        return firstColumn;
    }

    /**
     * @param firstColumn the firstColumn to set
     */
    public void setFirstColumn(String firstColumn) {
        this.firstColumn = firstColumn;
    }

    /**
     * @return the lastLine
     */
    public String getLastLine() {
        return lastLine;
    }

    /**
     * @param lastLine the lastLine to set
     */
    public void setLastLine(String lastLine) {
        this.lastLine = lastLine;
    }

    /**
     * @return the lastColumn
     */
    public String getLastColumn() {
        return lastColumn;
    }

    /**
     * @param lastColumn the lastColumn to set
     */
    public void setLastColumn(String lastColumn) {
        this.lastColumn = lastColumn;
    }

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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the solution.
     *
     * @return the solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Sets the solution.
     *
     * @param solution the new solution
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }
}
