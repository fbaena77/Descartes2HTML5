package com.emergya.descartes.converter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.converter.model.ConvertedContent;
import com.emergya.descartes.converter.model.ConvertedHTMLFile;
import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.utils.Constants;

public class Html5FileConverter {

    private static Logger log = Logger.getLogger(Html5FileConverter.class);

    /** The Constant ID_ATTRIBUTE. */
    private final String ID_ATTRIBUTE = "id";

    /** The Constant STYLE_NODE. */
    private final String STYLE_NODE = "style";

    /** The node css. */
    private Map<String, String> nodeCss;

    /** The error content. */
    private String errorContent;

    /**
     *
     * @param <T>
     * @param file the file
     */
    public <T> ConvertedContent<T> convertContentToHtml5(
            final DescartesContentProxy contentToConvert, File targetPath) {
        errorContent = "";
        ConvertedContent<T> convertedContent = new ConvertedContent<T>();
        List<ConvertedHTMLFile> convertedListFiles = new ArrayList<>();
        try {
            FileUtils
                    .copyDirectory(contentToConvert.getLocalCopy(), targetPath);
        } catch (IOException e1) {
            log.error("Error al crear el contenido "
                    + contentToConvert.getTitle() + " para su conversión");
        }
        convertedContent.setContentProxy(contentToConvert);
        convertedContent.setLocalCopy(targetPath);

        try (Stream<Path> filePathStream = Files.walk(Paths
                .get(convertedContent.getLocalCopy().getPath()))) {
            filePathStream
                    .forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            String ext = FilenameUtils.getExtension(filePath
                                    .toString());
                            if (ext.equals(Constants.FILE_HTML)
                                    || ext.equals(Constants.FILE_HTM)) {
                                try {
                                    Document doc = Jsoup.parse(filePath
                                            .toFile(),
                                            StandardCharsets.ISO_8859_1
                                                    .displayName());

                                    final Charset charset = Utils
                                            .getContentCharset(doc);
                                    if (!charset
                                            .equals(StandardCharsets.ISO_8859_1)) {
                                        doc = Jsoup.parse(filePath.toFile(),
                                                charset.displayName());
                                    }
                                    Document _doc = Utils.updateCharset(doc,
                                            StandardCharsets.UTF_8);
                                    nodeCss = new HashMap<String, String>();
                                    analizeDOM(_doc);
                                    addCssStyleDOM(_doc);

                                    ConvertedHTMLFile convertedHTMLFile = new ConvertedHTMLFile();
                                    convertedHTMLFile.setLocalCopy(filePath
                                            .toFile());
                                    convertedListFiles.add(convertedHTMLFile);

                                } catch (Exception e) {
                                    errorContent = e.getMessage();
                                    ConvertedHTMLFile convertedHTMLFile = new ConvertedHTMLFile();
                                    convertedHTMLFile.setLocalCopy(filePath
                                            .toFile());
                                    convertedHTMLFile.setErrors(errorContent);
                                    convertedListFiles.add(convertedHTMLFile);

                                    log.error((filePath.toFile() != null ? filePath
                                            .toFile().getAbsolutePath()
                                            : "Fichero")
                                            + ": Estructura HTML incorrecta."
                                            + e);
                                }
                            }
                        }
                    });

            convertedContent.setConvertedListFiles(convertedListFiles);

        } catch (IOException e) {
            log.error("Error al obtener los ficheros del contenido '"
                    + contentToConvert.getTitle() + "' en la ruta de origen", e);
        }
        return convertedContent;
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
                    final Boolean error = Utils.customDeprecated(
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
     * Descartes js.
     * Reemplaza la antigua libreria descartes-min.js por la nueva
     *
     * @param file the file
     */
    private void replaceDescartesJS(File file) {
        try {
            if (file.getName().equals("descartes-min.js")) {
                FileManager.copy(
                        this.getClass()
                                .getResource("/" + Constants.SCRIPT_DESCARTES)
                                .getPath(), file.getParent(), file.getName());
            }
        } catch (Exception e) {
            errorContent = e.getMessage();
            e.printStackTrace();
        }
    }
}
