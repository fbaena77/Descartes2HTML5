package com.emergya.descartes.converter.model;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesContentType;

public class ConvertedContent<T> implements Iterable<T> {

    private DescartesContentProxy contentProxy;
    private File localCopy;
    private Double totalSize;
    private DescartesContentType typeContent = DescartesContentType.mef10;
    private List<ConvertedHTMLFile> convertedListFiles;

    @Override
    public Iterator<T> iterator() {
        return null;
    }

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
     * @return the convertedListFiles
     */
    public List<ConvertedHTMLFile> getConvertedListFiles() {
        return convertedListFiles;
    }

    /**
     * @param convertedListFiles the convertedListFiles to set
     */
    public void setConvertedListFiles(List<ConvertedHTMLFile> convertedListFiles) {
        this.convertedListFiles = convertedListFiles;
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
}
