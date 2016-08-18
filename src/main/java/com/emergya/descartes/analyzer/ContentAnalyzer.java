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
import com.emergya.descartes.utils.Constants;

public class ContentAnalyzer {

    private static Logger log = Logger.getLogger(ContentAnalyzer.class);

    /**
     * @param <T>
     * @param contentProxy
     */
    public <T> AnalyzedContent<T> analyzeContent(
            DescartesContentProxy contentProxy) {
        AnalyzedContent<T> analyzedContent = new AnalyzedContent<T>();
        analyzedContent.setContentProxy(contentProxy);
        List<AnalyzedHTMLFile> analyzedListFiles = new ArrayList<>();

        try (Stream<Path> filePathStream = Files.walk(Paths.get(contentProxy
                .getLocalCopy().getPath()))) {
            filePathStream
                    .forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            String ext = FilenameUtils.getExtension(filePath
                                    .toString());
                            if (ext.equals(Constants.FILE_HTML)
                                    || ext.equals(Constants.FILE_HTM)) {
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
        AnalyzedHTMLFile analyzedHTMLFile = new AnalyzedHTMLFile();
        try {
            @SuppressWarnings("unused")
            Document doc = Jsoup.parse(file.toFile(),
                    StandardCharsets.UTF_8.displayName());

            analyzedHTMLFile.setPathInContent(file.getFileName().toString());
            analyzedHTMLFile.setAnalisis(W3CValidator.validateHtml5(file
                    .toFile()));

        } catch (Exception e) {
            log.error((file != null ? file.getFileName().toString() : "Fichero")
                    + ": Estructura HTML incorrecta." + e);
        }

        return analyzedHTMLFile;
    }
}
