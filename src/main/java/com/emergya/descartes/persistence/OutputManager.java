package com.emergya.descartes.persistence;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.emergya.descartes.analyzer.model.AnalyzedContent;
import com.emergya.descartes.analyzer.model.AnalyzedHTMLFile;
import com.emergya.descartes.converter.model.ConvertedContent;
import com.emergya.descartes.converter.model.ConvertedHTMLFile;

public class OutputManager {
    private static Logger log = Logger.getLogger(OutputManager.class);

    /** The Constant CSV_SEPARATOR. */
    private static final String CSV_SEPARATOR = ";";

    public static <T> void createAnalizedContentCSV(
            final AnalyzedContent<T> analyzedContent) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(analyzedContent.getLocalCopy()),
                    "ISO-8859-1"));

            StringBuffer sb = new StringBuffer();
            sb.append("FILE_PATH");
            sb.append(CSV_SEPARATOR);
            sb.append("NAME");
            sb.append(CSV_SEPARATOR);
            sb.append("SOLUTION");
            sb.append(CSV_SEPARATOR);
            sb.append("TYPE");
            sb.append(CSV_SEPARATOR);
            sb.append("MESSAGE");
            sb.append(CSV_SEPARATOR);
            sb.append("FIRSTLINE");
            sb.append(CSV_SEPARATOR);
            sb.append("FIRTCOLUMN");
            sb.append(CSV_SEPARATOR);
            sb.append("LASTLINE");
            sb.append(CSV_SEPARATOR);
            sb.append("LASTCOLUMN");
            sb.append(CSV_SEPARATOR);
            sb.append("EXTRACT");
            bw.write(sb.toString());
            bw.newLine();

            List<AnalyzedHTMLFile> listHTMLFiles = analyzedContent
                    .getAnalyzedListFiles();

            listHTMLFiles.forEach(W3CResult -> {
                sb.append(W3CResult.getPathInContent());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getLocalCopy().getName());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getSolution());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getType());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getMessage());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getFirstLine());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getFirstColumn());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getLastLine());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getLastColumn());
                sb.append(CSV_SEPARATOR);
                sb.append(W3CResult.getAnalisis().getExtract()
                        .replaceAll("\n", ""));
                try {
                    bw.write(sb.toString());
                    bw.newLine();
                } catch (Exception e) {
                    log.error("Error al escribir en fichero "
                            + analyzedContent.getLocalCopy().getName());
                }
            });

            bw.flush();
            bw.close();
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
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(convertedContent.getLocalCopy()),
                    "ISO-8859-1"));

            StringBuffer sb = new StringBuffer();
            sb.append("FILE_PATH");
            sb.append(CSV_SEPARATOR);
            sb.append("NAME");
            sb.append(CSV_SEPARATOR);
            sb.append("ERRORS");
            sb.append(CSV_SEPARATOR);
            bw.write(sb.toString());
            bw.newLine();

            List<ConvertedHTMLFile> listHTMLFiles = convertedContent
                    .getConvertedListFiles();

            listHTMLFiles.forEach(ConvertedHTMLFile -> {
                sb.append(ConvertedHTMLFile.getPathInContent());
                sb.append(CSV_SEPARATOR);
                sb.append(ConvertedHTMLFile.getLocalCopy().getName());
                sb.append(CSV_SEPARATOR);
                sb.append(ConvertedHTMLFile.getErrors());
                sb.append(CSV_SEPARATOR);
                try {
                    bw.write(sb.toString());
                    bw.newLine();
                } catch (Exception e) {
                    log.error("Error al escribir en fichero "
                            + convertedContent.getLocalCopy().getName());
                }
            });

            bw.flush();
            bw.close();
        } catch (Exception e) {
            log.error("Error al escribir en fichero "
                    + convertedContent.getLocalCopy().getName());
        }
    }
}
