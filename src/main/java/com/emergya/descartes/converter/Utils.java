package com.emergya.descartes.converter;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Utils {

    private static Logger log = Logger.getLogger(Utils.class);

    /**
     * Encode html.
     * Formatea los carácteres html para prevenir problemas de encoding
     * @param doc the doc
     * @return the string
     */
    public static String encodeHtml(Document doc) {
        doc.normalise();

        return doc.outerHtml();
    }

    /**
     * Content charset.
     * Obtiene el enconding especificado en el documento HTML
     *
     * @param doc the doc
     * @return the charset
     */
    public static Charset getContentCharset(Document doc) {
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
     * Elimina charset.
     * Borra la el encoding especificado en el código HTML,
     * para no causar conflicto con el propio encoding del fichero físico
     *
     * @param doc the doc
     */
    public static boolean removeCharset(Document doc) {
        boolean result = true;
        try {
            Elements metas = doc.getElementsByTag("meta");
            for (Element meta : metas) {
                if ("Content-Type".equalsIgnoreCase(meta.attr("http-equiv"))) {
                    meta.remove();
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Update charset.
     * Actualiza el encoding especificado en el documento html
     * @param doc the doc
     * @param charset the charset
     */
    public static void updateCharset(Document doc, Charset charset) {
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
     * Custom deprecated.
     * Invoca las funcional particular encargada de hacer un cambio en el documento HTML
     *
     * @param methodName the method name
     * @param doc the doc
     * @param node the node
     * @return the boolean
     */
    public static Boolean customDeprecated(String methodName, Document doc,
            Element node) {
        Boolean corregido = false;
        try {
            Method method = Html4CustomDeprecated.class.getMethod(methodName,
                    Document.class, Element.class);
            corregido = (Boolean) method.invoke(null, doc, node);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return corregido;
    }
}
