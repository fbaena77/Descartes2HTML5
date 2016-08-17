package com.emergya.descartes.analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.emergya.descartes.content.DescartesContentProxy;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ContentAnalyzer {

    private List<DescartesContentProxy> analizar(String directorio) {
        List<DescartesContentProxy> result = analizeRecursive(directorio);

        validationSave(directorio, result);
        return result;
    }

    public List<DescartesContentProxy> analyzeContent(String directorio) {
        File temp = new File(directorio);
        if (temp.isDirectory()) {
            String[] subDirectorio = temp.list();

            if (subDirectorio != null) {
                for (int i = 0; i < subDirectorio.length; i++) {
                    analizar(temp.getAbsolutePath() + File.separator
                            + subDirectorio[i]);
                }
            } else {
                // System.out.println("directorio vacío…");
            }
        } else if (temp.isFile()) {
            System.out.println("......" + temp.getAbsolutePath());
            final String ext = FilenameUtils.getExtension(temp
                    .getAbsolutePath());
            if (ext.equals(FILE_HTML)) {
                validarHtml(temp);
            } else if (ext.equals(FILE_CSS)) {

            } else if (ext.equals(FILE_JS)) {

            }

            final long tarda = (System.currentTimeMillis() - time) / 1000;
            System.out.println(tarda + "s" + " " + temp.getAbsolutePath());
        }

        return analizado;
    }

    /**
     * Validar html.
     *
     * @param file the file
     */
    private void validarHtml(File file) {
        DescartesContentProxy c = new DescartesContentProxy();
        c.setPath(file.getPath());
        try {

            Document doc = Jsoup.parse(file,
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

    /**
     * Recibiendo el path de un fichero por parámetro, se realiza la validación de este fichero.
     *
     * @param path the path
     * @return the string
     * @throws UnirestException the unirest exception
     * @throws ParserConfigurationException the parser configuration exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String checkW3C(String path) throws UnirestException,
            ParserConfigurationException, IOException {
        File file = new File(path);
        final Document doc = Jsoup.parse(file, "UTF-8");
        String source = doc.html();// "your html here";
        HttpResponse<String> uniResponse = Unirest
                .post("http://validator.w3.org/nu/")
                .header("User-Agent",
                        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
                .header("Content-Type", "text/html; charset=UTF-8")
                .queryString("out", "html").body(source).asString();
        String response = uniResponse.getBody();

        return response;
    }
}
