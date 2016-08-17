package com.emergya.descartes.converter;

// TODO: Auto-generated Javadoc
/**
 * The Enum Html4NodeAttributeDeprecated.
 * Se parametriza para cada etiqueta html5 la conversión de sus atributos
 */
public enum Html4NodeAttributeDeprecated {

    /** The a. */
    A("a", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.BORDER, Html4AttributeCss.VSPACE,
            Html4AttributeCss.WIDTH, Html4AttributeCss.HEIGHT }, new String[] {
            "charset", "coords", "methods", "name", "shape", "urn", "datafld",
            "datasrc" }, new String[] {}),

    /** The applet. */
    APPLET("applet", new Html4AttributeCss[] { Html4AttributeCss.STYLE },
            new String[] { "datafld", "datasrc" },
            new String[] { Html4CustomDeprecated.METHOD_APPLET_AJS }),

    /** The area. */
    AREA("area", new Html4AttributeCss[] { Html4AttributeCss.STYLE },
            new String[] { "nohref" },
            new String[] { Html4CustomDeprecated.METHOD_AREA_ATTRIBUTES }),

    /** The body. */
    BODY("body", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BGCOLOR,
            Html4AttributeCss.BACKGROUND, Html4AttributeCss.LINK,
            Html4AttributeCss.LIN, Html4AttributeCss.ALINK,
            Html4AttributeCss.MARGINBOTTOM, Html4AttributeCss.MARGINHEIGHT,
            Html4AttributeCss.MARGINLEFT, Html4AttributeCss.MARGINRIGHT,
            Html4AttributeCss.MARGINTOP, Html4AttributeCss.MARGINWIDTH,
            Html4AttributeCss.TEXT, Html4AttributeCss.VLINK,
            Html4AttributeCss.VLIN, Html4AttributeCss.LEFTMARGIN,
            Html4AttributeCss.TOPMARGIN }, new String[] { "bgproperties",
            "language" },
            new String[] { Html4CustomDeprecated.METHOD_ID_DUPLICATE }),

    /** The b. */
    B("b", new Html4AttributeCss[] { Html4AttributeCss.STYLE },
            new String[] {},
            new String[] { Html4CustomDeprecated.METHOD_B_CHILD }),

    /** The br. */
    BR("br", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.CLEAR }, new String[] {}, new String[] {}),

    /** The button. */
    BUTTON("button", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datafld", "dataformatas", "datasrc" },
            new String[] {}),

    /** The caption. */
    CAPTION("caption", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] { "" }, new String[] {}),

    /** The col. */
    COL("col", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.VALIGN,
            Html4AttributeCss.WIDTH }, new String[] { "char", "charoff" },
            new String[] {}),

    /** The div. */
    DIV("div", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.FACE,
            Html4AttributeCss.COLOR }, new String[] { "datafld",
            "dataformatas", "datasrc", "size", "font" }, new String[] {}),

    /** The dl. */
    DL("dl", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.COMPACT }, new String[] {}, new String[] {}),

    /** The embed. */
    EMBED("embed", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.HSPACE,
            Html4AttributeCss.VSPACE }, new String[] { "name", "applet" },
            new String[] {}),

    /** The fieldset. */
    FIELDSET("fieldset", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datafld" }, new String[] {}),

    /** The font. */
    FONT("font", new Html4AttributeCss[] {}, new String[] {},
            new String[] { Html4CustomDeprecated.METHOD_FONT_CHILD }),

    /** The form. */
    FORM("form", new Html4AttributeCss[] { Html4AttributeCss.STYLE },
            new String[] { "accept" }, new String[] {}),

    /** The frame. */
    FRAME("frame", new Html4AttributeCss[] { Html4AttributeCss.STYLE },
            new String[] { "datafld", "datasrc" }, new String[] {}),

    /** The H1. */
    H1("h1", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The H2. */
    H2("h2", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The H3. */
    H3("h3", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The H4. */
    H4("h4", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The H5. */
    H5("h5", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The H6. */
    H6("h6", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] {}, new String[] {}),

    /** The head. */
    HEAD("head", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "profile" },
            new String[] { Html4CustomDeprecated.METHOD_TITLE_GROUP }),

    /** The hr. */
    HR("hr", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BACKGROUND_HR,
            Html4AttributeCss.NOSHADE, Html4AttributeCss.SIZE,
            Html4AttributeCss.WIDTH }, new String[] { "noshhade" },
            new String[] {}),

    /** The html. */
    HTML("html", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "version", "xmlns", "xmlns:v", "xmlns:o" },
            new String[] { Html4CustomDeprecated.METHOD_DOCTYPE,
                    Html4CustomDeprecated.METHOD_ID_DUPLICATE }),

    /** The iframe. */
    IFRAME("iframe", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.ALLOWTRANSPARENCY,
            Html4AttributeCss.FRAMEBORDER, Html4AttributeCss.HSPACE,
            Html4AttributeCss.MARGINHEIGHT, Html4AttributeCss.MARGINWIDTH,
            Html4AttributeCss.SCROLLING, Html4AttributeCss.VSPACE,
            Html4AttributeCss.WIDTH, Html4AttributeCss.HEIGHT }, new String[] {
            "datafld", "datasrc" }, new String[] {}),

    /** The image. */
    IMAGE("image", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BORDER,
            Html4AttributeCss.HSPACE, Html4AttributeCss.VSPACE }, new String[] {
            "xthumbnail-orig-image", "dynamicanimation" }, new String[] {}),

    /** The img. */
    IMG("img", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BORDER,
            Html4AttributeCss.HSPACE, Html4AttributeCss.VSPACE,
            Html4AttributeCss.WIDTH, Html4AttributeCss.HEIGHT }, new String[] {
            "lowsrc", "name", "datafld", "datasrc", "nosave", "v:shapes",
            "longdesc", "dynamicanimation" }, new String[] {
            Html4CustomDeprecated.METHOD_IMG_ALT,
            Html4CustomDeprecated.METHOD_IMG_SRC_EMPTY }),

    /** The input. */
    INPUT("input", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.HSPACE,
            Html4AttributeCss.VSPACE }, new String[] { "usemap", "ismap",
            "datafld", "dataformatas", "datasrc" }, new String[] {}),

    /** The label. */
    LABEL("label", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datafld", "dataformatas", "datasrc" },
            new String[] {}),

    /** The legend. */
    LEGEND("legend", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] { "datafld",
            "dataformatas", "datasrc" }, new String[] {}),

    /** The li. */
    LI("li", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.TYPE, Html4AttributeCss.SQUARE },
            new String[] { "value" },
            new String[] { Html4CustomDeprecated.METHOD_LI_PARENT }),

    /** The link. */
    LINK("link", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "charset", "methods", "target", "urn" },
            new String[] {}),

    /** The marquee. */
    MARQUEE("marquee", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datafld", "dataformatas", "datasrc" },
            new String[] {}),

    /** The meta. */
    META("meta", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "scheme" }, new String[] {
                    Html4CustomDeprecated.METHOD_META_LANGUAGE,
                    Html4CustomDeprecated.METHOD_META_CONTENT }),

    /** The object. */
    OBJECT("object", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BORDER,
            Html4AttributeCss.HSPACE, Html4AttributeCss.VSPACE }, new String[] {
            "applet", "standby", "declare", "archive", "classid", "code",
            "codebase", "codetype", "datafld", "dataformatas", "datasrc" },
            new String[] {}),

    /** The ol. */
    OL("ol", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.COMPACT }, new String[] {}, new String[] {}),

    /** The option. */
    OPTION("option", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "name", "dataformatas", "datasrc" }, new String[] {}),

    /** The p. */
    P("p", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN }, new String[] { "algin" },
            new String[] { Html4CustomDeprecated.METHOD_P_SPAN }),

    /** The param. */
    PARAM("param", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "type", "valuetype", "datafld" }, new String[] {}),

    /** The pre. */
    PRE("pre", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.WIDTH, Html4AttributeCss.ALIGN },
            new String[] {}, new String[] {}),

    /** The script. */
    SCRIPT("script", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "event", "for", "fptype" },
            new String[] { Html4CustomDeprecated.METHOD_SCRIPT_TYPE }),

    /** The select. */
    SELECT("select", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datafld", "dataformatas", "datasrc" },
            new String[] {}),

    /** The span. */
    SPAN("span", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.FACE, Html4AttributeCss.COLOR,
            Html4AttributeCss.SIZE, Html4AttributeCss.ALIGN,
            Html4AttributeCss.HEIGHT, Html4AttributeCss.WIDTH }, new String[] {
            "dataformatas", "datasrc", "font", "lang" }, new String[] {}),

    /** The style. */
    STYLE("style", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "fprolloverstyle" }, new String[] {}),

    /** The table. */
    TABLE("table", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BGCOLOR,
            Html4AttributeCss.BACKGROUND, Html4AttributeCss.HEIGHT,
            Html4AttributeCss.BORDERCOLOR, Html4AttributeCss.BORDERCOLORLIGHT,
            Html4AttributeCss.BORDER, Html4AttributeCss.CELLPADDING,
            Html4AttributeCss.CELLSPACING, Html4AttributeCss.RULES,
            Html4AttributeCss.WIDTH }, new String[] { "datapagesize", "frame",
            "summary", "dataformatas", "datasrc", "bordercolordark", "cols" },
            new String[] {}),

    /** The tbody. */
    TBODY("tbody", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.VALIGN }, new String[] {
            "char", "charoff" }, new String[] {}),

    /** The td. */
    TD("td", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BGCOLOR,
            Html4AttributeCss.HEIGHT, Html4AttributeCss.NOWRAP,
            Html4AttributeCss.VALIGN, Html4AttributeCss.WIDTH,
            Html4AttributeCss.BORDERCOLOR, Html4AttributeCss.BORDERCOLORLIGHT,
            Html4AttributeCss.BACKGROUND }, new String[] { "axis", "char",
            "charoff", "bordercolordark", "x:num", "x:fmla" }, new String[] {}),

    /** The textarea. */
    TEXTAREA("textarea", new Html4AttributeCss[] { Html4AttributeCss.STYLE, },
            new String[] { "datasrc" }, new String[] {}),

    /** The tfood. */
    TFOOD("tfood", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.VALIGN }, new String[] {
            "char", "charoff" }, new String[] {}),

    /** The th. */
    TH("th", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BGCOLOR,
            Html4AttributeCss.HEIGHT, Html4AttributeCss.NOWRAP,
            Html4AttributeCss.VALIGN, Html4AttributeCss.WIDTH }, new String[] {
            "axis", "char", "charoff" }, new String[] {}),

    /** The thead. */
    THEAD("thead", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.VALIGN }, new String[] {
            "char", "charoff" }, new String[] {}),

    /** The tr. */
    TR("tr", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.ALIGN, Html4AttributeCss.BGCOLOR,
            Html4AttributeCss.VALIGN, Html4AttributeCss.BORDERCOLOR },
            new String[] { "char", "charoff" }, new String[] {}),

    /** The ul. */
    UL("ul", new Html4AttributeCss[] { Html4AttributeCss.STYLE,
            Html4AttributeCss.COMPACT, Html4AttributeCss.TYPE,
            Html4AttributeCss.SQUARE }, new String[] { "msimagelist",
            "dynamicoutline" }, new String[] {
            Html4CustomDeprecated.METHOD_UL_ANIDADO,
            Html4CustomDeprecated.METHOD_UL_SPAN });

    /** Etiqueta html5 */
    private String node;

    /** Adaptación de atributos obsoletos por estilos css*/
    private Html4AttributeCss[] attributesStyle;

    /** Atributos obsoletos que se pueden eliminar directamente de la etiqueta sin perder funcionalidad */
    private String[] attributeRemove;

    /** Métodos personalizados que es necesario invocar para la conversión de ésta etiqueta */
    private String[] custom;

    /**
     * Instantiates a new html4 node attribute deprecated.
     *
     * @param node the node
     * @param attributesStyle the attributes style
     * @param attributeRemove the attribute remove
     * @param custom the custom
     */
    private Html4NodeAttributeDeprecated(String node,
            Html4AttributeCss[] attributesStyle, String[] attributeRemove,
            String[] custom) {
        this.node = node;
        this.attributesStyle = attributesStyle;
        this.attributeRemove = attributeRemove;
        this.setCustom(custom);
    }

    /**
     * Gets the node.
     *
     * @return the node
     */
    public String getNode() {
        return node;
    }

    /**
     * Sets the node.
     *
     * @param node the new node
     */
    public void setNode(String node) {
        this.node = node;
    }

    /**
     * Gets the attributes style.
     *
     * @return the attributes style
     */
    public Html4AttributeCss[] getAttributesStyle() {
        return attributesStyle;
    }

    /**
     * Sets the attributes style.
     *
     * @param attributesStyle the new attributes style
     */
    public void setAttributesStyle(Html4AttributeCss[] attributesStyle) {
        this.attributesStyle = attributesStyle;
    }

    /**
     * Gets the attribute remove.
     *
     * @return the attribute remove
     */
    public String[] getAttributeRemove() {
        return attributeRemove;
    }

    /**
     * Sets the attribute remove.
     *
     * @param attributeRemove the new attribute remove
     */
    public void setAttributeRemove(String[] attributeRemove) {
        this.attributeRemove = attributeRemove;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return node;
    }

    /**
     * Gets the custom.
     *
     * @return the custom
     */
    public String[] getCustom() {
        return custom;
    }

    /**
     * Sets the custom.
     *
     * @param custom the new custom
     */
    public void setCustom(String[] custom) {
        this.custom = custom;
    }
}
