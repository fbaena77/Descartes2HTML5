package com.emergya.descartes.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.emergya.descartes.analyzer.model.W3CResponse;
import com.emergya.descartes.job.JobConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class W3CValidator {

    private static Logger log = Logger.getLogger(W3CValidator.class);

    /**
     * Recibiendo el path de un fichero por parámetro, se realiza la validación de este fichero.
     *
     * @param path the path
     * @return the string
     * @throws UnirestException the unirest exception
     * @throws ParserConfigurationException the parser configuration exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static List<W3CResponse> validateHtml5(File file, JobConverter job)
            throws UnirestException, ParserConfigurationException, IOException {
        final Document doc = Jsoup.parse(file, job.getJobConfig()
                .getValidationCharset());
        String response = null;
        String source = doc.html();
        HttpResponse<String> uniResponse = Unirest
                .post(job.getJobConfig().getW3CServiceUrl())
                .header("User-Agent",
                        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
                .header("Content-Type", "text/html; charset=UTF-8")
                .queryString("out", "json").body(source).asString();
        response = uniResponse.getBody();

        return deserializeResponse(response, file);
    }

    /**
     * Validation to object.
     *
     * @param response
     * @return W3CResponse
     */
    private static List<W3CResponse> deserializeResponse(String response,
            File file) {
        List<W3CResponse> w3CResponseList = new ArrayList<W3CResponse>();
        JSONObject json = null;
        try {
            json = (JSONObject) new JSONParser().parse(response);
        } catch (ParseException e) {
            log.error("Error al deserializar la validación de W3C para el fichero "
                    + file.toString() + " : " + e);
        }
        JSONArray messages = (JSONArray) json.get("messages");
        if (messages != null) {
            for (int i = 0; i < messages.size(); i++) {
                JSONObject jsonMessage = (JSONObject) messages.get(i);
                W3CResponse w3CResponse = new W3CResponse();
                w3CResponse.setType(jsonMessage.get("type").toString());
                w3CResponse.setMessage(jsonMessage.get("message").toString());
                w3CResponse
                        .setExtract((jsonMessage.get("extract") != null ? jsonMessage
                                .get("extract") : "").toString());
                w3CResponse
                        .setFirstLine((jsonMessage.get("firstLine") != null ? jsonMessage
                                .get("firstLine") : "").toString());
                w3CResponse
                        .setFirstColumn((jsonMessage.get("firstColumn") != null ? jsonMessage
                                .get("firstColumn") : "").toString());
                w3CResponse
                        .setLastLine((jsonMessage.get("lastLine") != null ? jsonMessage
                                .get("lastLine") : "").toString());
                w3CResponse
                        .setLastColumn((jsonMessage.get("lastColumn") != null ? jsonMessage
                                .get("lastColumn") : "").toString());

                w3CResponseList.add(w3CResponse);
            }
        }

        return w3CResponseList;
    }
}
