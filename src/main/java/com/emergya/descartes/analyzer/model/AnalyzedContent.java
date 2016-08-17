package com.emergya.descartes.analyzer.model;

import com.emergya.descartes.content.DescartesContentType;

public class AnalyzedContent {

    private String treeStructureInfo;
    private Double totalSize;
    private DescartesContentType typeContent = DescartesContentType.mef10;
    private String errores;
    private String validateHtml5;
    private Boolean hasScenes;
    private Integer AppletsNumber;
    private String[] listFilesApplets;
}
