package com.emergya.descartes.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.analyzer.model.AnalyzedHTMLFile;
import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.job.JobConverter;
import com.emergya.descartes.utils.Constants;

public class ContentAnalyzer {

    private static Logger log = Logger.getLogger(ContentAnalyzer.class);

    List<AnalyzedHTMLFile> analyzedListFiles = new ArrayList<>();
    JobConverter job;

    /**
     * @param job
     */
    public ContentAnalyzer(JobConverter job) {
        this.job = job;
    }

    /**
     * @param <T>
     * @param contentProxy
     */
    public <T> AnalyzedContent<T> analyzeContent(
            DescartesContentProxy contentProxy) {
        AnalyzedContent<T> analyzedContent = new AnalyzedContent<T>();
        try {
            Files.walk(Paths.get(contentProxy.getLocalCopy().getPath()))
                    .filter(p -> p.toString().endsWith(Constants.FILE_HTML)
                            || p.toString().endsWith(Constants.FILE_HTM))
                    .forEach(this::setAnalyzedListFiles);
            analyzedContent.setContentProxy(contentProxy);
            analyzedContent.setAnalyzedListFiles(analyzedListFiles);
        } catch (IOException e) {
            log.error(
                    "Error al obtener el nombre de los ficheros .zip de Descartes en la ruta de origen",
                    e);
        }

        return analyzedContent;
    }

    private void setAnalyzedListFiles(Path path) {
        analyzedListFiles.add(analyzeHtml(path));
    }

    /**
     * @param file the file
     */
    private AnalyzedHTMLFile analyzeHtml(Path file) {
        AnalyzedHTMLFile analyzedHTMLFile = new AnalyzedHTMLFile();
        try {
            @SuppressWarnings("unused")
            Document doc = Jsoup.parse(file.toFile(), job.getJobConfig()
                    .getValidationCharset());
            analyzedHTMLFile.setPathInContent(file.getFileName().toString());
            analyzedHTMLFile.setLocalCopy(file.toFile());
            analyzedHTMLFile.setAnalisis(W3CValidator.validateHtml5(
                    file.toFile(), job));

        } catch (Exception e) {
            log.error((file != null ? file.getFileName().toString() : "Fichero")
                    + ": estructura HTML incorrecta: " + e);
        }

        return analyzedHTMLFile;
    }
}
