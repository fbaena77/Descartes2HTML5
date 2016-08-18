package com.emergya.descartes.converter.model;

import java.io.File;

public class ConvertedHTMLFile {

    private File localCopy;
    private String pathInContent;
    private String errors;

    /**
     * @return the pathInContent
     */
    public String getPathInContent() {
        return pathInContent;
    }

    /**
     * @param pathInContent the pathInContent to set
     */
    public void setPathInContent(String pathInContent) {
        this.pathInContent = pathInContent;
    }

    /**
     * @return the localCopy
     */
    public File getLocalCopy() {
        return localCopy;
    }

    /**
     * @param localCopy the localCopy to set
     */
    public void setLocalCopy(File localCopy) {
        this.localCopy = localCopy;
    }

    /**
     * @return the errors
     */
    public String getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(String errors) {
        this.errors = errors;
    }

}
