package com.emergya.descartes.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.analyzer.model.AnalyzedHTMLFile;
import com.emergya.descartes.converter.model.ConvertedContent;
import com.emergya.descartes.converter.model.ConvertedHTMLFile;

public class OutputManager {
    private static Logger log = Logger.getLogger(OutputManager.class);

    public static <T> void createAnalizedContentCSV(
            final AnalyzedContent<T> analyzedContent, String workingPath) {
        try {
            List<AnalyzedHTMLFile> listHTMLFiles = analyzedContent
                    .getAnalyzedListFiles();

            for (int i = 0; i < listHTMLFiles.size(); i++) {
                File outFolder = analyzedContent.getLocalCopy();
                outFolder.mkdir();
                String tmpAnalyzeFile = listHTMLFiles
                        .get(i)
                        .getLocalCopy()
                        .toString()
                        .replace(workingPath, "")
                        .replace(analyzedContent.getContentProxy().getTitle(),
                                "");
                File analyzeFile = new File(
                        outFolder
                                + File.separator
                                + (tmpAnalyzeFile.replace(File.separator, "+") + "_W3CResult.csv")
                                        .replace("++", ""));

                FileWriter writer = new FileWriter(analyzeFile);

                CSVUtils.writeLine(writer, Arrays.asList("TYPE", "MESSAGE",
                        "FIRSTLINE", "FIRTCOLUMN", "LASTLINE", "LASTCOLUMN",
                        "EXTRACT"));

                for (int j = 0; j < listHTMLFiles.get(i).getAnalisis().size(); j++) {
                    CSVUtils.writeLine(
                            writer,
                            Arrays.asList(
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getType() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getType() : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getMessage() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getMessage()
                                            : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getFirstLine() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getFirstLine()
                                            : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getFirstColumn() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getFirstColumn()
                                            : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getLastLine() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getLastLine()
                                            : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getLastColumn() != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getLastColumn()
                                            : ""),
                                    (listHTMLFiles.get(i).getAnalisis().get(j)
                                            .getExtract().replaceAll("\n", "") != null ? listHTMLFiles
                                            .get(i).getAnalisis().get(j)
                                            .getExtract().replaceAll("\n", "")
                                            : "")));
                }

                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            log.error("Error al escribir en fichero "
                    + analyzedContent.getLocalCopy().getName());
        }
    }

    /**
    * Crea log de los contenidos convertidos.
    * @param <T>
    * @param file the file
    * @param doc the doc
    * @return the file
    */
    public static <T> void createLogConvertedContentCSV(
            final ConvertedContent<T> convertedContent) {
        try {
            List<ConvertedHTMLFile> listHTMLFiles = convertedContent
                    .getConvertedListFiles();
            FileWriter writer = new FileWriter(convertedContent.getLogFile());
            CSVUtils.writeLine(writer, Arrays.asList("FILE_PATH", "ERRORS"));

            for (int i = 0; i < listHTMLFiles.size(); i++) {
                CSVUtils.writeLine(writer, Arrays.asList((listHTMLFiles.get(i)
                        .getPathInContent() != null ? listHTMLFiles.get(i)
                        .getPathInContent() : ""), (listHTMLFiles.get(i)
                        .getErrors() != null ? listHTMLFiles.get(i).getErrors()
                        : "")));
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            log.error("Error al escribir en fichero "
                    + convertedContent.getLogFile().getName());
        }
    }

    /**
     * Crea file html5.
     * Genera el contenido HTML5 del documento con el nombre del fichero original en el mismo directorio.
     * @param file the file
     * @param doc the doc
     * @return the file
     */
    public static File createHtml5File(File file, Document doc) {
        File html5File = null;
        try {
            html5File = new File(file.getAbsolutePath());
            OutputStreamWriter outWriter = new OutputStreamWriter(
                    new FileOutputStream(file.getAbsolutePath()),
                    StandardCharsets.ISO_8859_1);
            outWriter.write(doc.normalise().outerHtml());
            outWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html5File;
    }
}
