package com.emergya.descartes.analyzer.model;

import java.io.File;
import java.util.List;

public class AnalyzedHTMLFile {

    private File localCopy;
    private String pathInContent;
    private List<W3CResponse> analisis;

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
     * @return the analisis
     */
    public List<W3CResponse> getAnalisis() {
        return analisis;
    }

    /**
     * @param analisis the analisis to set
     */
    public void setAnalisis(List<W3CResponse> analisis) {
        this.analisis = analisis;
    }
}
