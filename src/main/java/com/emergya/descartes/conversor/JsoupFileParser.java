package com.emergya.descartes.conversor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class JsoupFileParser {
	/**
	 * Recibiendo el path de un fichero por parámetro, se realiza la validación de este fichero.
	 *
	 * @param path the path
	 * @return the string
	 * @throws UnirestException the unirest exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String validateHtml5W3C(String path) throws UnirestException, ParserConfigurationException, IOException{

		File file = new File(path);
		final DocumentBuilderFactory dbfac = DocumentBuilderFactory
				.newInstance();
		final DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		// final Document doc =
		// docBuilder.parse(this.getClass().getResourceAsStream(file));
		final Document doc = Jsoup.parse(file, "UTF-8");
		
		String response = null;
		String source = doc.html();//"your html here";
		HttpResponse<String> uniResponse = Unirest.post("http://validator.w3.org/nu/")
		    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
		    .header("Content-Type", "text/html; charset=UTF-8")
		    .queryString("out", "html")
		    .body(source)
		    .asString();
		response = uniResponse.getBody();
		
		return response;
	}

}
