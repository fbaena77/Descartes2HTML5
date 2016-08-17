package com.emergya.descartes.converter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTML.Tag;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.emergya.descartes.persistence.FileManager;
import com.emergya.descartes.validator.impl.JsoupHtml5Service;

// TODO: Auto-generated Javadoc
/**
 * The Class Html4CustomDeprecated.
 * Para algunas conversiones no estandares, qu no se corresponden con etiquetas o atributos obsoletos, 
 * sino con html mal formado es necesario definir métdos de conversión que se encargan de manipular 
 * el código html para que cumpla las validaciones w3c
 */
public class Html4CustomDeprecated {

    /** The Constant METHOD_DOCTYPE. */
    public static final String METHOD_DOCTYPE = "docType";

    /** The Constant METHOD_SCRIPT_TYPE. */
    public static final String METHOD_SCRIPT_TYPE = "scriptType";

    /** The Constant METHOD_META_LANGUAGE. */
    public static final String METHOD_META_LANGUAGE = "metaLanguage";

    /** The Constant METHOD_META_CONTENT. */
    public static final String METHOD_META_CONTENT = "metaContent";

    /** The Constant METHOD_META_DUPLICATE. */
    public static final String METHOD_META_DUPLICATE = "metaDuplicate";

    /** The Constant METHOD_UL_ANIDADO. */
    public static final String METHOD_UL_ANIDADO = "ulAnidado";

    /** The Constant METHOD_TITLE_GROUP. */
    public static final String METHOD_TITLE_GROUP = "titleGroup";

    /** The Constant METHOD_IMG_ALT. */
    public static final String METHOD_IMG_ALT = "imgAlt";

    /** The Constant METHOD_APPLET_AJS. */
    public static final String METHOD_APPLET_AJS = "addAppletPlugin";

    /** The Constant METHOD_P_SPAN. */
    public static final String METHOD_P_SPAN = "pParentSpan";

    /** The Constant METHOD_AREA_ATTRIBUTES. */
    public static final String METHOD_AREA_ATTRIBUTES = "areaAttributes";

    /** The Constant METHOD_LI_PARENT. */
    public static final String METHOD_LI_PARENT = "liParent";

    /** The Constant METHOD_UL_SPAN. */
    public static final String METHOD_UL_SPAN = "ulParentSpan";

    /** The Constant METHOD_FONT_CHILD. */
    public static final String METHOD_FONT_CHILD = "fontChildStyle";

    /** The Constant METHOD_ID_DUPLICATE. */
    public static final String METHOD_ID_DUPLICATE = "idDuplicate";

    /** The Constant METHOD_B_CHILD. */
    public static final String METHOD_B_CHILD = "bChildStyle";

    /** The Constant METHOD_IMG_SRC_EMPTY. */
    public static final String METHOD_IMG_SRC_EMPTY = "imgSrcEmpty";

    /** The Constant AJS_TAG. */
    private static final String AJS_TAG = "ajs";

    /** The Constant AJS_WIDTH. */
    private static final String AJS_WIDTH = "width";

    /** The Constant AJS_HEIGHT. */
    private static final String AJS_HEIGHT = "height";

    /** Valor por defecto de altura para los discursos	*/
    // TODO valor por defecto para la altura del contenedor de los
    // discursos
    private static final String AJS_HEIGHT_VALUE = "3000";

    /** The Constant AJS_CODE. */
    private static final String AJS_CODE = "code";

    /** The Constant AJS_NAME. */
    private static final String AJS_NAME = "name";

    /** The Constant AJS_NAME_VALUE. */
    // TODO el valor del Code de la etiqueta AJS se cambia.
    // private static final String AJS_NAME_VALUE = "Descartes";
    private static final String AJS_NAME_VALUE = "descinst.DescartesWeb2_0.class";

    /** The Constant AJS_CLASS. */
    private static final String AJS_CLASS = "DescartesJS";

    /** The Constant SCRIPT_TYPE. */
    private static final String SCRIPT_TYPE = "text/javascript";

    /**
     * Doc type.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean docType(Document doc, Element node) {
        Boolean corregido = false;
        DocumentType docType = null;
        if (doc != null & node != null) {
            for (Node child : doc.childNodes()) {
                if (child instanceof DocumentType) {
                    docType = (DocumentType) child;
                    break;
                }
            }

            if (docType == null) {
                docType = new DocumentType("html", "", "", "");
                final Element html = doc.getElementsByTag("html").get(0);
                html.before(docType);
                corregido = true;
            } else {
                docType.setBaseUri("");
                docType.removeAttr("publicId");
                docType.removeAttr("systemId");
            }
        }
        return corregido;
    }

    /**
     * Script type.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean scriptType(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.SCRIPT.getNode().equals(
                        node.nodeName())) {

            node.attr("type", "text/javascript");
            node.removeAttr("language");
            corregido = true;

        }
        return corregido;
    }

    /**
     * Meta language.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean metaLanguage(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.META.getNode().equals(
                        node.nodeName())) {
            String langValue = null;

            if ("Content-Language".equalsIgnoreCase(node.attr("http-equiv"))) {
                langValue = node.attr("content");
                node.remove();
            }

            if (langValue != null) {
                final Element html = doc.getElementsByTag("html").get(0);
                html.attr("lang", langValue);
                corregido = true;
            }
        }
        return corregido;
    }

    /**
     * Meta content.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean metaContent(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.META.getNode().equals(
                        node.nodeName())) {

            if ("".equalsIgnoreCase(node.attr("http-equiv"))) {
                node.remove();
                corregido = true;
            }
        }
        return corregido;
    }

    /**
     * Img alt.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean imgAlt(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.IMG.getNode().equals(
                        node.nodeName())) {
            final String alt = node.attr("alt");
            if (StringUtil.isBlank(alt)) {
                node.attr("alt", node.attr("src"));
                corregido = true;
            }
        }
        return corregido;
    }

    /**
     * Ul anidado.
     *
     * @param doc the doc
     * @param element the element
     * @return the boolean
     */
    public static Boolean ulAnidado(Document doc, Element element) {
        Node node = element;
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.UL.getNode().equals(
                        node.nodeName())) {

            List<Node> children = node.childNodes();
            List<Node> appendChild = new ArrayList<Node>();
            List<Node> ulChildren = new ArrayList<Node>();
            for (Node child : children) {
                /** Si tiene como hijo un nodo UL se analiza éste*/
                if (child.nodeName().equals(Tag.UL.toString())
                        || child.nodeName().equals(Tag.OL.toString())) {
                    ulChildren.add(child);
                    corregido = true;
                }
                /** En caso contrario se guarda para su posterior formateo*/
                else {
                    appendChild.add(child);
                }
            }

            /** Eliminamos todo los hijos del UL para posteriormente inseretarlos con el formato y orden correctos*/
            int index = 0;
            while (index < children.size()) {
                if (!children.get(index).nodeName().equals(Tag.UL.toString())
                        && !children.get(index).nodeName()
                                .equals(Tag.OL.toString())) {
                    children.get(index).remove();
                } else {
                    index++;
                }
            }

            /** Insertamoes en el UL los nodos hijos en orden con formato LI*/
            for (Node child : appendChild) {
                if (child instanceof TextNode) {
                    final String text = child.toString();
                    if (!StringUtil.isBlank(text.trim())) {
                        Element liChild = new Element(
                                org.jsoup.parser.Tag.valueOf(Tag.LI.toString()),
                                "");
                        liChild.appendText(text);
                        ((Element) node).appendChild(liChild);
                        corregido = true;
                    }
                } else if (!child.nodeName().equals(Tag.LI.toString())) {
                    Element liChild = new Element(
                            org.jsoup.parser.Tag.valueOf(Tag.LI.toString()), "");
                    liChild.appendChild(child);
                    ((Element) node).appendChild(liChild);
                    corregido = true;
                } else {
                    ((Element) node).appendChild(child);
                }
            }

            /** ya que el nodo actual es un UL los UL contenidos se pasan al nodo padre*/
            for (Node ulChild : ulChildren) {
                node.before(ulChild);
            }

        }

        return corregido;
    }

    /**
     * Title group.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean titleGroup(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.HEAD.getNode().equals(
                        node.nodeName())) {

            StringBuffer titleContent = new StringBuffer();
            Elements titles = doc.getElementsByTag(Tag.TITLE.toString());
            for (Element title : titles) {
                titleContent.append(title.text());
            }

            titles.remove();

            if (titleContent.length() == 0) {
                titleContent.append("SIN TITULO");
            }

            doc.title(titleContent.toString());
        }

        return corregido;
    }

    /**
     * Añade applet plugin.
     * Sustituye la antigua etiqueta APPLET por la de descartes AJS
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean addAppletPlugin(Document doc, Element node) {
        node.parent().html("<" + AJS_TAG + " />");
        final Element ajsNode = node.parent().select(AJS_TAG).get(0);

        ajsNode.attr(AJS_WIDTH, node.attr(AJS_WIDTH));
        // TODO Se establece una altura por defecto para los discursos
        ajsNode.attr(AJS_HEIGHT, AJS_HEIGHT_VALUE/* node.attr(AJS_HEIGHT) */);
        // TODO el valor del Code de la etiqueta AJS se cambia.
        ajsNode.attr(AJS_CODE, AJS_NAME_VALUE);
        ajsNode.attr(AJS_NAME, AJS_NAME_VALUE);
        ajsNode.addClass(AJS_CLASS);

        for (Element child : node.children()) {
            ajsNode.appendChild(child);
        }

        /**Comprobar si existe el script de descartes*/
        boolean existDescartes = false;
        final Elements scripts = doc.head().getElementsByTag(
                Tag.SCRIPT.toString());
        for (Element script : scripts) {
            if (script.attr("src").contains(JsoupHtml5Service.SCRIPT_DESCARTES)) {
                existDescartes = true;
                break;
            }
        }

        /**Añadir el scipt de descartes*/
        if (!existDescartes) {
            final Element descartesJs = new Element(
                    org.jsoup.parser.Tag.valueOf(Tag.SCRIPT.toString()), "");
            descartesJs.attr("type", SCRIPT_TYPE);
            descartesJs.attr("src", "./" + JsoupHtml5Service.SCRIPT_DESCARTES);
            doc.head().appendChild(descartesJs);

            final File actual = new File(doc.baseUri());
            final boolean existFile = FileManager.exist(actual.getParent(),
                    JsoupHtml5Service.SCRIPT_DESCARTES);

            /** Añadir el fichero de descartes si no existe*/
            if (!existFile) {
                try {
                    FileManager.copy(
                            Html4CustomDeprecated.class.getResource(
                                    "/" + JsoupHtml5Service.SCRIPT_DESCARTES)
                                    .getPath(), actual.getParent(),
                            JsoupHtml5Service.SCRIPT_DESCARTES);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    /**
     * P parent span.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean pParentSpan(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.P.toString())
                && node.parent().tagName().equals(Tag.SPAN.toString())) {
            Element span = node.parent();
            span.parent().appendChild(node);
        }

        return corregido;
    }

    /**
     * Area attributes.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean areaAttributes(Document doc, Element node) {
        Boolean corregido = false;
        if (doc != null
                && node != null
                && Html4NodeAttributeDeprecated.AREA.getNode().equals(
                        node.nodeName())) {
            final String alt = node.attr("alt");
            if (StringUtil.isBlank(alt)) {
                node.attr("alt", node.attr("href"));
                corregido = true;
            }
            final String coords = node.attr("coords");
            if (!StringUtil.isBlank(coords)) {
                node.attr("coords", coords.replaceAll(" ", ""));
                corregido = true;
            }

        }
        return corregido;
    }

    /**
     * Li parent.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean liParent(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.LI.toString())
                && !node.parent().tagName().equals(Tag.UL.toString())
                && !node.parent().tagName().equals(Tag.OL.toString())
                && !node.parent().tagName().equals(Tag.MENU.toString())) {

            Element ul = new Element(org.jsoup.parser.Tag.valueOf(Tag.UL
                    .toString()), "");
            node.before(ul);
            ul.appendChild(node);
        }

        return corregido;
    }

    /**
     * Ul parent span.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean ulParentSpan(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.UL.toString())
                && node.parent().tagName().equals(Tag.SPAN.toString())) {
            Element span = node.parent();
            span.parent().appendChild(node);
        }

        return corregido;
    }

    /**
     * Font child style.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean fontChildStyle(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.FONT.toString())) {
            final String color = node.attr("color");
            final String font = node.attr("face");

            if (node.children().size() == 0) {
                Element actual = new Element(
                        org.jsoup.parser.Tag.valueOf(Tag.SPAN.toString()), "");
                actual.attr("style", "color:" + color + "; font-family:" + font);

                List<Node> childNodes = new ArrayList<Node>();
                for (Node child : node.childNodes()) {
                    childNodes.add(child);
                }
                for (Node child : childNodes) {
                    actual.appendChild(child);
                }
                node.before(actual);
                node.remove();
            } else {
                Elements children = node.children();
                for (Element child : children) {
                    child.attr("style", child.attr("style") + ";color:" + color
                            + "; font-family:" + font);
                    node.before(child);
                }
                node.remove();
            }
        }

        return corregido;
    }

    /**
     * B child style.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean bChildStyle(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.B.toString())) {
            if (node.children().size() != 0) {
                Elements children = node.children();
                for (Element child : children) {
                    child.attr("style", child.attr("style")
                            + ";font-weight:bold");
                    node.before(child);
                }
                node.remove();
            }
        }

        return corregido;
    }

    /**
     * Id duplicate.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean idDuplicate(Document doc, Element node) {
        boolean corregido = false;

        Elements children = node.children();
        for (Element child : children) {
            if (!StringUtil.isBlank(child.id())) {
                if (child.id().equals("AutoNumber2")) {
                    System.out.println("kk");
                }
                Elements duplicates = doc.getElementsByAttributeValue("id",
                        child.id());
                if (duplicates.size() > 1) {
                    corregido = true;
                    /** Reemplaza el estilo de identificador por una clase*/
                    Elements styles = doc.head().getElementsByTag("style");
                    for (Element style : styles) {
                        style.html(style.html().replaceAll("#" + child.id(),
                                "." + child.id()));
                    }

                    for (Element dupli : duplicates) {
                        /** Añade el estilo de clase y cambia el identificador*/
                        dupli.addClass(dupli.id());
                        final Random rd = new Random();
                        final int maxRandom = 2000000000;
                        final int minRandom = 1000000000;
                        final String idValue = "html5_"
                                + dupli.id()
                                + "_"
                                + (rd.nextInt(maxRandom - minRandom + 1) + minRandom);
                        dupli.attr("id", idValue);
                    }
                }
            }
            corregido = corregido || idDuplicate(doc, child);
        }

        return corregido;
    }

    /**
     * Img src empty.
     *
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean imgSrcEmpty(Document doc, Element node) {
        boolean corregido = false;

        if (node.tagName().equals(Tag.IMG.toString())
                && StringUtil.isBlank(node.attr("src"))) {
            node.remove();
        }

        return corregido;
    }

}
