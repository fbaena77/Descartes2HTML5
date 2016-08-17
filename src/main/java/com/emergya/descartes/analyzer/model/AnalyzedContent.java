package com.emergya.descartes.analyzer.model;

import java.io.File;
import java.util.List;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesContentType;

public class AnalyzedContent {

    private DescartesContentProxy contentProxy;
    private File fileCopy;
    private Double totalSize;
    private DescartesContentType typeContent = DescartesContentType.mef10;
    private List<AnalyzedHTMLFile> analyzedListFiles;
    private String errors;

    /**
     * @return the contentProxy
     */
    public DescartesContentProxy getContentProxy() {
        return contentProxy;
    }

    /**
     * @param contentProxy the contentProxy to set
     */
    public void setContentProxy(DescartesContentProxy contentProxy) {
        this.contentProxy = contentProxy;
    }

    /**
     * @return the fileCopy
     */
    public File getFileCopy() {
        return fileCopy;
    }

    /**
     * @param fileCopy the fileCopy to set
     */
    public void setFileCopy(File fileCopy) {
        this.fileCopy = fileCopy;
    }

    /**
     * @return the totalSize
     */
    public Double getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(Double totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the typeContent
     */
    public DescartesContentType getTypeContent() {
        return typeContent;
    }

    /**
     * @param typeContent the typeContent to set
     */
    public void setTypeContent(DescartesContentType typeContent) {
        this.typeContent = typeContent;
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

    /**
     * @return the analyzedListFiles
     */
    public List<AnalyzedHTMLFile> getAnalyzedListFiles() {
        return analyzedListFiles;
    }

    /**
     * @param analyzedListFiles the analyzedListFiles to set
     */
    public void setAnalyzedListFiles(List<AnalyzedHTMLFile> analyzedListFiles) {
        this.analyzedListFiles = analyzedListFiles;
    }
}
