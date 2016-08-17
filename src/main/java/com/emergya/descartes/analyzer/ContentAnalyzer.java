package com.emergya.descartes.analyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.analyzer.model.AnalyzedHTMLFile;
import com.emergya.descartes.content.DescartesContentProxy;

public class ContentAnalyzer {

    private static Logger log = Logger.getLogger(ContentAnalyzer.class);

    /** The Constant FILE_HTML. */
    private static final String FILE_HTML = "html";

    /** The Constant FILE_HTM. */
    private static final String FILE_HTM = "htm";

    /**
     * @param contentProxy
     */
    public AnalyzedContent analyzeContent(DescartesContentProxy contentProxy) {

        AnalyzedContent analyzedContent = new AnalyzedContent();
        analyzedContent.setContentProxy(contentProxy);
        List<AnalyzedHTMLFile> analyzedListFiles = new ArrayList<>();

        try (Stream<Path> filePathStream = Files.walk(Paths.get(contentProxy
                .getLocalCopy().getPath()))) {
            filePathStream
                    .forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            String ext = FilenameUtils.getExtension(filePath
                                    .toString());
                            if (ext.equals(FILE_HTML) || ext.equals(FILE_HTM)) {
                                analyzedListFiles.add(analyzeHtml(filePath));
                            }
                        }
                    });

            analyzedContent.setAnalyzedListFiles(analyzedListFiles);

        } catch (IOException e) {
            log.error(
                    "Error al obtener el nombre de los ficheros .zip de Descartes en la ruta de origen",
                    e);
        }

        return analyzedContent;
    }

    /**
     * @param file the file
     */
    private AnalyzedHTMLFile analyzeHtml(Path file) {
        try {
            Document doc = Jsoup.parse(file.toFile(),
                    StandardCharsets.UTF_8.displayName());

            c.setValidateHtml5(checkW3C(file.getPath()));

        } catch (Exception e) {
            errorContent = e.getMessage();
            System.out.println((file != null ? file.getAbsolutePath()
                    : "Fichero")
                    + ": Estructura HTML incorrecta."
                    + e.getMessage());
            e.printStackTrace();
        }

        analizado.add(c);
    }
}
