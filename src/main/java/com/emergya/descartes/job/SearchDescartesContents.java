package com.emergya.descartes.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class SearchDescartesContents {

    private static final Logger log = Logger
            .getLogger(SearchDescartesContents.class);

    private long numContents;
    private String path;

    public SearchDescartesContents(String path) {
        super();
        this.setNumContents(getNumContents(path));
        this.setPath(path);
    }

    private long getNumContents(String _path) {

        long count = 0;
        try {
            count = Files.find(Paths.get(_path), 1,
                    (path, attributes) -> attributes.isDirectory()).count() - 1;
        } catch (IOException e) {
            log.error(
                    "Error al obtener el ńumero de contenidos Descartes en la ruta de origen",
                    e);
        }
        return count;
    }

    /**
     * @return the numContents
     */
    public long getNumContents() {
        return numContents;
    }

    /**
     * @param numContents the numContents to set
     */
    private void setNumContents(long numContents) {
        this.numContents = numContents;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    private void setPath(String path) {
        this.path = path;
    }

}
