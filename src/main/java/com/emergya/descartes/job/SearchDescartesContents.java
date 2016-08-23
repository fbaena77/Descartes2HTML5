package com.emergya.descartes.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.emergya.descartes.content.DescartesZipContentProxy;

public class SearchDescartesContents {

    private static final Logger log = Logger
            .getLogger(SearchDescartesContents.class);

    private long numContents;
    private String path;
    private List<DescartesZipContentProxy> contentList = new ArrayList<DescartesZipContentProxy>();

    public SearchDescartesContents(String path) {
        super();
        this.setNumContents(path);
        this.setPath(path);
    }

    public List<DescartesZipContentProxy> getDescartesZipContentProxyListNames() {
        try {
            Files.walk(Paths.get(getPath()))
                    .filter(p -> p.toString().endsWith(".zip"))
                    .forEach(this::setContentList);

        } catch (Exception e) {
            log.error(
                    "Error al obtener el nombre de los ficheros .zip de Descartes en la ruta de origen",
                    e);
        }

        return contentList;
    }

    private void setContentList(Path path) {
        DescartesZipContentProxy DescartesZipContentProxy = new DescartesZipContentProxy(
                path);
        contentList.add(DescartesZipContentProxy);
    }

    private void setNumContents(final String _path) {
        try {
            numContents = Files.find(Paths.get(_path), 1,
                    (path, attributes) -> !attributes.isDirectory()).count();
        } catch (IOException e) {
            log.error(
                    "Error al obtener el ńumero de contenidos Descartes en la ruta de origen",
                    e);
        }
    }

    /**
     * @return the numContents
     */
    public long getNumContents() {
        return numContents;
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
