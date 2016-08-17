package com.emergya.descartes.content;

public class ZipContent {

    /**
     * @param fileName
     */
    public ZipContent(String fileName) {
        this.setFileName(fileName);
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

}
