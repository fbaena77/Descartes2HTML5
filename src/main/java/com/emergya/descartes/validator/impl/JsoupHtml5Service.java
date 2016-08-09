package com.emergya.descartes.validator.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.conversor.Html4AttributeCss;
import com.emergya.descartes.conversor.Html4CustomDeprecated;
import com.emergya.descartes.conversor.Html4NodeAttributeDeprecated;
import com.emergya.descartes.conversor.Html4NodeDeprecated;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.persistence.model.FileValidation;
import com.emergya.descartes.utils.Message;
import com.emergya.descartes.validator.IJsoupHtml5Service;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class JsoupHtml5Service.
 * Servicios Jsoup para la validación de html por la w3c y corección de contendio.
 */
public class JsoupHtml5Service implements IJsoupHtml5Service {

    /** The Constant FILE_HTML. */
    private static final String FILE_HTML = "html";

    /** The Constant FILE_HTM. */
    private static final String FILE_HTM = "htm";

    /** The Constant FILE_CSS. */
    private static final String FILE_CSS = "css";

    /** The Constant FILE_JS. */
    private static final String FILE_JS = "js";

    /** The Constant STYLE_NODE. */
    private static final String STYLE_NODE = "style";

    /** The Constant CSV_SEPARATOR. */
    private static final String CSV_SEPARATOR = ";";

    /** The Constant SCRIPT_DESCARTES. */
    public static final String SCRIPT_DESCARTES = "descartes-min.js";

    /** The Constant ID_ATTRIBUTE. */
    private static final String ID_ATTRIBUTE = "id";

    /** The node css. */
    private Map<String, String> nodeCss;

    /** The time. */
    private long time;

    /** The error content. */
    private String errorContent;

    /** The analizado. */
    private List<DescartesContentProxy> analizado = new ArrayList<DescartesContentProxy>();

    @Override
    public List<DescartesContentProxy> analizar(String directorio) {
        List<DescartesContentProxy> result = analizeRecursive(directorio);

        validationSave(directorio, result);
        return result;
    }

    /**
     * Validation save.
     *
     * @param directorio the directorio
     * @param result the result
     */
    private void validationSave(String directorio,
            List<DescartesContentProxy> result) {
        // TODO Auto-generated method stub
        List<com.emergya.descartes.persistence.model.File> validationObjects = validationToObject(result);
        validationObjectToCSV(directorio, validationObjects);
    }

    /**
     * Validation object to csv.
     *
     * @param directorio the directorio
     * @param validationObjects the validation objects
     */
    private void validationObjectToCSV(String directorio,
            List<com.emergya.descartes.persistence.model.File> validationObjects) {
        // TODO Auto-generated method stub
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(directorio + File.separator
                            + System.currentTimeMillis()
                            + "_validationHtml5.csv"), "ISO-8859-1"));

            StringBuffer oneLine = new StringBuffer();
            oneLine.append("PATH");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("NAME");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("SOLUTION");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("TYPE");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("MESSAGE");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("FIRSTLINE");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("FIRTCOLUMN");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("LASTLINE");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("LASTCOLUMN");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("EXTRACT");
            bw.write(oneLine.toString());
            bw.newLine();

            for (com.emergya.descartes.persistence.model.File contenido : validationObjects) {
                for (FileValidation validation : contenido.getValidation()) {

                    oneLine = new StringBuffer();
                    oneLine.append(contenido.getPath());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(contenido.getName());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getMessage().getSolution());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getMessage().getType());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getMessage().getMessage());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getFirstLine());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getFirstColumn());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getLastLine());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getLastColumn());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(validation.getExract().replaceAll("\n", ""));
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Validation to object.
     *
     * @param result the result
     * @return the list
     */
    private List<com.emergya.descartes.persistence.model.File> validationToObject(
            List<DescartesContentProxy> result) {
        List<com.emergya.descartes.persistence.model.File> validationObjects = new ArrayList<com.emergya.descartes.persistence.model.File>();
        for (DescartesContentProxy c : result) {
            com.emergya.descartes.persistence.model.File object = new com.emergya.descartes.persistence.model.File(
                    c.getPath().substring(0,
                            c.getPath().lastIndexOf(File.separator)));
            object.setName(c.getPath().substring(
                    c.getPath().lastIndexOf(File.separator) + 1));

            object.setValidation(new ArrayList<FileValidation>());

            try {
                if (c.getValidateHtml5() != null) {
                    JSONObject json = (JSONObject) new JSONParser().parse(c
                            .getValidateHtml5());
                    JSONArray messages = (JSONArray) json.get("messages");
                    if (messages != null) {
                        for (int i = 0; i < messages.size(); i++) {
                            FileValidation validation = new FileValidation();
                            JSONObject jsonMessage = (JSONObject) messages
                                    .get(i);
                            Message message = new Message();
                            message.setType(jsonMessage.get("type").toString());
                            message.setMessage(jsonMessage.get("message")
                                    .toString());
                            validation.setMessage(message);
                            validation
                                    .setExract((jsonMessage.get("extract") != null ? jsonMessage
                                            .get("extract") : "").toString());
                            validation.setFirstLine((jsonMessage
                                    .get("firstLine") != null ? jsonMessage
                                    .get("firstLine") : "").toString());
                            validation.setFirstColumn((jsonMessage
                                    .get("firstColumn") != null ? jsonMessage
                                    .get("firstColumn") : "").toString());
                            validation
                                    .setLastLine((jsonMessage.get("lastLine") != null ? jsonMessage
                                            .get("lastLine") : "").toString());
                            validation.setLastColumn((jsonMessage
                                    .get("lastColumn") != null ? jsonMessage
                                    .get("lastColumn") : "").toString());

                            object.getValidation().add(validation);
                        }
                    }
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            validationObjects.add(object);
        }
        return validationObjects;
    }

    /**
     * Analize recursive.
     *
     * @param directorio the directorio
     * @return the list
     */
    public List<DescartesContentProxy> analizeRecursive(String directorio) {
        time = System.currentTimeMillis();

        File temp = new File(directorio);

        if (temp.isDirectory()) {
            String[] subDirectorio = temp.list();

            if (subDirectorio != null) {
                for (int i = 0; i < subDirectorio.length; i++) {
                    analizeRecursive(temp.getAbsolutePath() + temp.separator
                            + subDirectorio[i]);
                }
            } else {
                // System.out.println("directorio vacío…");
            }
        } else if (temp.isFile()) {
            System.out.println("......" + temp.getAbsolutePath());
            final String ext = FilenameUtils.getExtension(temp
                    .getAbsolutePath());
            if ((ext.equals(FILE_HTML) || ext.equals(FILE_HTM))
                    && !isBackup(temp.getName())) {
                htmlFile(temp);
            } else if (ext.equals(FILE_CSS)) {

            } else if (ext.equals(FILE_JS)) {
                descartesJS(temp);
            }

            final long tarda = (System.currentTimeMillis() - time) / 1000;
            System.out.println(tarda + "s" + " " + temp.getAbsolutePath());
        }

        return analizado;
    }

    /**
     * Checks if is backup.
     *
     * @param name the name
     * @return true, if is backup
     */
    private boolean isBackup(String name) {
        return name.contains("_old_");
    }

    /**
     * Descartes js.
     * Reemplaza la antigua libreria descartes-min.js por la nueva
     *
     * @param file the file
     */
    private void descartesJS(File file) {
        try {
            if (file.getName().equals("descartes-min.js")) {
                FileManager.copy(
                        this.getClass().getResource("/" + SCRIPT_DESCARTES)
                                .getPath(), file.getParent(), file.getName());
            }
        } catch (Exception e) {
            errorContent = e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Html file.
     * Analiza el código html de un fichero para convertirlo a HTML5
     *
     * @param file the file
     */
    public void htmlFile(final File file) {
        // final String relative = file.getPath().replace(
        // (env.getProperty("contenido.origen")), "");
        DescartesContentProxy c = new DescartesContentProxy();
        c.setPath(file.getPath());
        errorContent = "";
        try {

            final DocumentBuilderFactory dbfac = DocumentBuilderFactory
                    .newInstance();
            final DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            // final Document doc =
            // docBuilder.parse(this.getClass().getResourceAsStream(file));
            Document doc = Jsoup.parse(file,
                    StandardCharsets.ISO_8859_1.displayName());
            final Charset charset = getContentCharset(doc);
            if (!charset.equals(StandardCharsets.ISO_8859_1)) {
                doc = Jsoup.parse(file, charset.displayName());
            }
            // updateCharset(doc, charset);
            updateCharset(doc, StandardCharsets.UTF_8);
            // removeCharset(doc);

            nodeCss = new HashMap<String, String>();
            analizeDOM(doc);
            addCssStyleDOM(doc);

            c.setValidateHtml5(checkW3C(doc));

            createFileHtml5(file, doc);

        } catch (Exception e) {
            errorContent = e.getMessage();
            System.out.println((file != null ? file.getAbsolutePath()
                    : "Fichero")
                    + ": Estructura HTML incorrecta."
                    + e.getMessage());
            e.printStackTrace();
        }

        c.setErrores(errorContent);
        analizado.add(c);
    }

    /**
     * Content charset.
     * Obtiene el enconding especificado en el documento HTML
     *
     * @param doc the doc
     * @return the charset
     */
    private static Charset getContentCharset(Document doc) {
        Charset charset = doc.charset();

        Elements metas = doc.getElementsByTag("meta");
        for (Element meta : metas) {
            if ("Content-Type".equalsIgnoreCase(meta.attr("http-equiv"))) {
                final String contentValue = meta.attr("content");
                final int indexOf = contentValue.indexOf("charset=");
                if (indexOf != -1) {
                    final Charset contentCharset = Charset.forName(contentValue
                            .substring(indexOf + 8));
                    if (!contentCharset.equals(StandardCharsets.UTF_8)
                            && !contentCharset
                                    .equals(StandardCharsets.ISO_8859_1)
                            && !contentCharset.displayName().toLowerCase()
                                    .equals("iso-8859-2")
                            && !contentCharset.displayName().toLowerCase()
                                    .equals("windows-1252")) {
                        charset = StandardCharsets.UTF_8;

                    } else if (contentCharset
                            .equals(StandardCharsets.ISO_8859_1)) {
                        charset = Charset.forName("windows-1252");
                    } else {
                        charset = contentCharset;
                    }
                }
            }
        }

        return charset;
    }

    /**
     * Update charset.
     * Actualiza el encoding especificado en el documento html
     * @param doc the doc
     * @param charset the charset
     */
    private static void updateCharset(Document doc, Charset charset) {
        boolean exist = false;
        Elements metas = doc.getElementsByTag("meta");
        for (Element meta : metas) {
            if ("Content-Type".equalsIgnoreCase(meta.attr("http-equiv"))) {
                final String contentValue = meta.attr("content");
                final int indexOf = contentValue.indexOf("charset=");
                if (indexOf != -1) {
                    final Charset contentCharset = Charset.forName(contentValue
                            .substring(indexOf + 8));
                    meta.attr(
                            "content",
                            contentValue
                                    .replace(
                                            contentCharset.displayName()
                                                    .toLowerCase(),
                                            charset.displayName().toLowerCase())
                                    .replace(contentCharset.displayName(),
                                            charset.displayName().toLowerCase()));

                }
                exist = true;
            }
        }

        if (!exist) {
            Element meta = new Element(Tag.valueOf("meta"), "");
            meta.attr("http-equiv", "Content-Type");
            meta.attr("content", "text/html; charset="
                    + charset.displayName().toLowerCase());
            doc.head().appendChild(meta);
        }
    }

    /**
     * Elimina charset.
     * Borra la el encoding especificado en el código HTML,
     * para no causar conflicto con el propio encoding del fichero físico
     *
     * @param doc the doc
     */
    private static void removeCharset(Document doc) {
        Elements metas = doc.getElementsByTag("meta");
        for (Element meta : metas) {
            if ("Content-Type".equalsIgnoreCase(meta.attr("http-equiv"))) {
                meta.remove();
            }
        }
    }

    /**
     * Check w3c.
     * Valida el contenido de un documento HTML contra el servicio de W3C
     *
     * @param doc the doc
     * @return the string
     */
    private String checkW3C(Document doc) {
        String response = null;
        final String source = doc.html();
        HttpResponse<String> uniResponse = null;
        try {
            uniResponse = Unirest
                    .post("http://localhost:8080/vnu")
                    .header("User-Agent",
                            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
                    .header("Content-Type", "text/html; charset=UTF-8")
                    .queryString("out", "json").body(source).asString();
            response = uniResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Crea file html5.
     * Reemplaza el contenido HTML5 del documento en el fichero original, creando una copia
     * del fichero original en el mismo directorio.
     * @param file the file
     * @param doc the doc
     * @return the file
     */
    private File createFileHtml5(File file, Document doc) {
        File newFile = null;
        try {
            final String newName = file.getAbsolutePath();
            final String oldName = file.getAbsolutePath() + "_old_"
                    + System.currentTimeMillis() + ".htm";
            File oldFile = new File(oldName);
            file.renameTo(oldFile);

            newFile = new File(newName);
            OutputStreamWriter outWriter = new OutputStreamWriter(
                    new FileOutputStream(newName), StandardCharsets.UTF_8);
            outWriter.write(doc.normalise().outerHtml());
            outWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFile;
    }

    /**
     * Encode html.
     * Formatea los carácteres html para prevenir problemas de encoding
     * @param doc the doc
     * @return the string
     */
    private String encodeHtml(Document doc) {
        doc.normalise();

        return doc.outerHtml();
    }

    /**
     * Analize dom.
     * Analiza el documneto HTML pra ir corrigiendo la sintaxis obsoleta
     *
     * @param doc the doc
     */
    private void analizeDOM(Document doc) {
        /** Sustitye los nodos del HTML que han sido deprecados en HTML5 */
        for (Html4NodeDeprecated deprecated : Html4NodeDeprecated.values()) {
            Elements nodes = doc.getElementsByTag(deprecated.getNode());
            if (deprecated.getReplace() != null) {
                for (Element node : nodes) {
                    Element actual = new Element(Tag.valueOf(deprecated
                            .getReplace()), "");
                    actual.attributes().addAll(node.attributes());
                    if (deprecated.getCssClass() != null) {
                        actual.addClass(deprecated.getCssClass());
                    }
                    List<Node> childNodes = new ArrayList<Node>();
                    for (Node child : node.childNodes()) {
                        childNodes.add(child);
                    }
                    for (Node child : childNodes) {
                        actual.appendChild(child);
                    }
                    node.before(actual);
                }
                nodes.remove();
            }
        }

        /** Analizamos los atributos deprecados en HTML5 */
        for (Html4NodeAttributeDeprecated deprecated : Html4NodeAttributeDeprecated
                .values()) {
            Elements nodes = doc.getElementsByTag(deprecated.getNode());
            for (Element node : nodes) {
                /** Atributos que se reemplazarán por estilos css */
                for (int i = 0; i < deprecated.getAttributesStyle().length; i++) {
                    if (node.hasAttr(deprecated.getAttributesStyle()[i]
                            .getAttribute())) {
                        final String attrValue = node.attr(deprecated
                                .getAttributesStyle()[i].getAttribute());
                        attrToStyle(node, attrValue,
                                deprecated.getAttributesStyle()[i], deprecated);
                        node.removeAttr(deprecated.getAttributesStyle()[i]
                                .getAttribute());

                        errorContent += "CSS: " + deprecated.getNode() + "."
                                + deprecated.getAttributesStyle()[i] + "<br/>";
                    }
                }
                /** Atributos que se borran del HTML directamente */
                for (int i = 0; i < deprecated.getAttributeRemove().length; i++) {
                    if (node.hasAttr(deprecated.getAttributeRemove()[i])) {

                        node.removeAttr(deprecated.getAttributeRemove()[i]);

                        errorContent += "Borrado: " + deprecated.getNode()
                                + "." + deprecated.getAttributeRemove()[i]
                                + "<br/>";
                    }
                }

                for (int i = 0; i < deprecated.getCustom().length; i++) {
                    final Boolean error = customDeprecated(
                            deprecated.getCustom()[i], doc, node);

                    if (error) {
                        errorContent += "Custom: " + deprecated.getNode() + "."
                                + deprecated.getCustom()[i] + "<br/>";
                    }
                }
            }
        }
    }

    /**
     * Custom deprecated.
     * Invoca las funcional particular encargada de hacer un cambio en el documento HTML
     *
     * @param methodName the method name
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    private Boolean customDeprecated(String methodName, Document doc,
            Element node) {
        Boolean corregido = false;
        try {
            Method method = Html4CustomDeprecated.class.getMethod(methodName,
                    Document.class, Element.class);
            corregido = (Boolean) method.invoke(null, doc, node);
        } catch (Exception e) {
            errorContent = e.getMessage();
            e.printStackTrace();
        }
        return corregido;
    }

    /**
     * Attr to style.
     * Convierte los atributos obsoletos en estilos css
     *
     * @param node the node
     * @param attrValue the attr value
     * @param html4AttributeCss the html4 attribute css
     * @param deprecated the deprecated
     */
    private void attrToStyle(Element node, String attrValue,
            Html4AttributeCss html4AttributeCss,
            Html4NodeAttributeDeprecated deprecated) {
        if (html4AttributeCss.getCssClass() != null) {
            /**
             * Si el antiguo atributo se ha sustituido por una clase css se
             * indica en el elemento
             */
            node.addClass(html4AttributeCss.getCssClass());

        } else {
            final String cssClass = addCssClassEspecial(deprecated,
                    html4AttributeCss, attrValue);

            if (cssClass != null) {
                node.addClass(String.format(cssClass, attrValue));
            } else {
                /**
                 * Si el antiguo atributo se ha sustituido por un elemento css, se
                 * añade a la lista de estilos correspondientes al elemento
                 */
                String idValue = node.attr(ID_ATTRIBUTE);
                if (StringUtil.isBlank(idValue)) {
                    final Random rd = new Random();
                    final int maxRandom = 2000000000;
                    final int minRandom = 1000000000;
                    idValue = "html5"
                            + node.nodeName()
                            + "_"
                            + (rd.nextInt(maxRandom - minRandom + 1) + minRandom);
                    node.attr(ID_ATTRIBUTE, idValue);
                }

                if (html4AttributeCss.getCssPath() != null) {
                    idValue += " " + html4AttributeCss.getCssPath();
                }

                String cssValue = nodeCss.get(idValue);
                cssValue = cssValue != null ? cssValue : "";

                /** Si el estilo contempla varios estilos al mismo tiempo y solo hay que indicarle el valor que toma*/
                cssValue += html4AttributeCss.getCss() != null ? (html4AttributeCss
                        .getCss().contains(":") ? html4AttributeCss.getCss()
                        .replaceAll("%s", attrValue) : html4AttributeCss
                        .getCss()) : "no_css_definido_parar_el_atributo_"
                        + html4AttributeCss.getAttribute();

                if (!html4AttributeCss.getCss().contains(":")) {

                    if (Html4AttributeCss.STYLE.equals(html4AttributeCss)) {
                        cssValue += attrValue;
                    } else if (Html4AttributeCss.BACKGROUND
                            .equals(html4AttributeCss)) {
                        cssValue += ":url('" + attrValue + "')";
                    } else {
                        cssValue += ":" + attrValue;
                    }

                    if (Html4AttributeCss.WIDTH.equals(html4AttributeCss)
                            || Html4AttributeCss.HEIGHT
                                    .equals(html4AttributeCss)) {

                        boolean porcentaje = attrValue.contains("%");
                        boolean px = attrValue.contains("px");

                        /** Si el atributo ya tenía indicada la unidad no se vuelve a especificar*/
                        if (!porcentaje && !px) {
                            cssValue += html4AttributeCss.getCssUnit() != null ? html4AttributeCss
                                    .getCssUnit() : "";
                        }

                    } else {
                        cssValue += html4AttributeCss.getCssUnit() != null ? html4AttributeCss
                                .getCssUnit() : "";
                    }

                }
                cssValue += cssValue.isEmpty() ? "" : ";";
                nodeCss.put(idValue, cssValue);
            }
        }
    }

    /**
     * Añade css class especial.
     * Añade a los estilos modificaciones no parametrizables
     * 
     * @param deprecated the deprecated
     * @param html4AttributeCss the html4 attribute css
     * @param attrValue the attr value
     * @return the string
     */
    private String addCssClassEspecial(Html4NodeAttributeDeprecated deprecated,
            Html4AttributeCss html4AttributeCss, String attrValue) {
        String cssClass = null;

        if (Html4NodeAttributeDeprecated.TABLE.equals(deprecated)
                && Html4AttributeCss.ALIGN.equals(html4AttributeCss)
                && "center".equals(attrValue)) {

            cssClass = "table_center";
        } else if (Html4NodeAttributeDeprecated.IFRAME.equals(deprecated)
                && Html4AttributeCss.SCROLLING.equals(html4AttributeCss)
                && "auto".equals(attrValue)) {

            cssClass = "iframe_scrolling_auto";
        } else if (Html4NodeAttributeDeprecated.IFRAME.equals(deprecated)
                && Html4AttributeCss.SCROLLING.equals(html4AttributeCss)
                && "yes".equals(attrValue)) {

            cssClass = "iframe_scrolling_yes";
        } else if (Html4NodeAttributeDeprecated.IFRAME.equals(deprecated)
                && Html4AttributeCss.SCROLLING.equals(html4AttributeCss)
                && "no".equals(attrValue)) {

            cssClass = "iframe_scrolling_no";
        }

        return cssClass;
    }

    /**
     * Añade css style dom.
     * Añade los estilos partculares, de elemento HTML que se ha ido generadndo, a la cabecera
     *
     * @param document the document
     */
    private void addCssStyleDOM(Document document) {
        // head.append("<link rel=\"stylesheet\" href=\"http://example.com/your.css\">");
        final Element head = document.head();

        final Elements stylesNode = head.getElementsByTag(STYLE_NODE);
        Element styleNode;
        if (stylesNode.size() > 0) {
            styleNode = stylesNode.get(0);
        } else {
            Tag tagStyle = Tag.valueOf(STYLE_NODE);
            styleNode = new Element(tagStyle, "");
            head.appendChild(styleNode);
        }

        StringBuffer styleContent = new StringBuffer();
        for (Map.Entry<String, String> itemCss : nodeCss.entrySet()) {
            styleContent.append("\n#").append(itemCss.getKey()).append("{")
                    .append(itemCss.getValue()).append("}");
        }

        styleContent.append(Html4AttributeCss.TABLE_CENTER_CLASS);
        styleContent.append(Html4AttributeCss.NOSHADE_CLASS);
        styleContent.append(Html4AttributeCss.ALLOWTRANSPARENCY_CLASS);
        styleContent.append(Html4AttributeCss.IFRAME_SCROLLING_AUTO_CLASS);
        styleContent.append(Html4AttributeCss.IFRAME_SCROLLING_YES_CLASS);
        styleContent.append(Html4AttributeCss.IFRAME_SCROLLING_NO_CLASS);

        styleContent.append(Html4NodeDeprecated.DIV_CENTER_CLASS);
        styleContent.append(Html4NodeDeprecated.SPAN_DL);
        styleContent.append(Html4NodeDeprecated.SPAN_U);
        styleContent.append(Html4NodeDeprecated.SPAN_EM);
        styleContent.append(Html4NodeDeprecated.SPAN_TT);
        styleContent.append(Html4NodeDeprecated.SPAN_OP);
        styleContent.append(Html4NodeDeprecated.SPAN_NOBR);

        styleNode.appendText(styleContent.toString());

    }

    @Override
    public List<DescartesContentProxy> validar(String directorio) {
        File temp = new File(directorio);
        if (temp.isDirectory()) {
            String[] subDirectorio = temp.list();

            if (subDirectorio != null) {
                for (int i = 0; i < subDirectorio.length; i++) {
                    analizar(temp.getAbsolutePath() + temp.separator
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

            c.setValidateHtml5(checkW3C(doc));
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
