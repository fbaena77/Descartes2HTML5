package com.emergya.descartes.converter;

// TODO: Auto-generated Javadoc
/**
 * The Enum Html4AttributeCss.
 * Se parametríza la conversión de atributos html4, no válidos en html5, a propiedades css
 *  */
public enum Html4AttributeCss {

    /** The align. */
    ALIGN("align", null, "text-align", null, null),

    /** The alink. */
    ALINK("alink", "a:link", "color", null, null),

    /** The bgcolor. */
    BGCOLOR("bgcolor", null, "background-color", null, null),

    /** The background hr. */
    BACKGROUND_HR("color", null,
            "background-color: %s; border-color: %s; border-style: solid",
            null, null),

    /** The link. */
    LINK("link", "a:link", "color", null, null),

    /** The lin. */
    LIN("lin", "a:link", "color", null, null),

    /** The marginbottom. */
    MARGINBOTTOM("marginbottom", null, "margin-bottom", "px", null),

    /** The marginheight. */
    MARGINHEIGHT("marginheight", null, "margin-height", "px", null),

    /** The marginleft. */
    MARGINLEFT("marginleft", null, "margin-left", "px", null),

    /** The marginright. */
    MARGINRIGHT("marginright", null, "margin-right", "px", null),

    /** The margintop. */
    MARGINTOP("margintop", null, "margin-top", "px", null),

    /** The marginwidth. */
    MARGINWIDTH("marginwidth", null, "margin", "px", null),

    /** The leftmargin. */
    LEFTMARGIN("leftmargin", null, "margin-left", "px", null),

    /** The topmargin. */
    TOPMARGIN("topmargin", null, "margin-top", "px", null),

    /** The text. */
    TEXT("text", null, "color", null, null),

    /** The vlink. */
    VLINK("vlink", "a:visited", "color", null, null),

    /** The vlin. */
    VLIN("vlin", "a:visited", "color", null, null),

    /** The clear. */
    CLEAR("clear", null, "clear", null, null),

    /** The valign. */
    VALIGN("valign", null, "vertical-align", null, null),

    /** The width. */
    WIDTH("width", null, "width", "px", null),

    /** The height. */
    HEIGHT("height", null, "height", "px", null),

    /** The compact. */
    COMPACT("compact", null, "line-height", "80%", null),

    /** The hspace. */
    HSPACE("hspace", null, "margin-left: %spx; margin-right: %spx;", null, null),

    /** The vspace. */
    VSPACE("vspace", null, "margin-top: %spx; margin-bottom: %spx;", null, null),

    /** The color. */
    COLOR("color", null, "color", null, null),

    /** The noshade. */
    NOSHADE("noshade", null, null, null, "noshade"),

    /** The size. */
    SIZE("size", null, "height", "px", null),

    /** The allowtransparency. */
    ALLOWTRANSPARENCY("allowtransparency", "body", null, null,
            "allowtransparency"),

    /** The frameborder. */
    FRAMEBORDER("frameborder", null, "border", "px", null),

    /** The scrolling. */
    /* auto = overflow:auto; yes= overflow:scroll; no= overflow:hidden. */
    SCROLLING("scrolling", null, null, null, null),

    /**  The bordercolor. */
    BORDERCOLOR("bordercolor", null, "border-color", null, null),

    /** The bordercolorlight. */
    BORDERCOLORLIGHT("bordercolorlight", null, "border-color", null, null),

    /** The border. */
    BORDER("border", null, "border", "px", null),

    /** The type. */
    TYPE("type", null, "list-style-type", null, null),

    /** The cellpadding. */
    CELLPADDING("cellpadding", "> tr > td", "padding", null, null),

    /** The cellspacing. */
    CELLSPACING("cellspacing", "> tr > td", "margin", null, null),

    /** The rules. */
    RULES("rules", null, null, null, null),

    /** The nowrap. */
    NOWRAP("nowrap", null, "white-space", "nowrap", null),

    /** The face. */
    FACE("face", null, "font-family", null, null),

    /** The STYLE. */
    STYLE("style", null, "", null, null),

    /** The square. */
    SQUARE("square", null, null, null, "list_style_square"),

    /** The background. */
    BACKGROUND("background", null, "background", null, null);

    /** Atributo de una etiqueta html, que ha quedado obsoleto en html5. */
    private String attribute;

    /** Ruta css en la que se aplica las propiedades css establecidas. */
    private String cssPath;

    /** Propiedad/des css por las que se reemplazará el atributo */
    private String css;

    /** En caso de aplicarle unidades, se establece el tipo de unidad correspondiente para la propieedad css */
    private String cssUnit;

    /** En ocasiones el atributo html4 no puede ser sustituido por una simple propiedad css y es necesario reemplazarlo por una clase definida */
    private String cssClass;

    /** The Constant TABLE_CENTER_CLASS. */
    public static final String TABLE_CENTER_CLASS = ".table_center{margin: auto;}";

    /** The Constant NOSHADE_CLASS. */
    public static final String NOSHADE_CLASS = ".noshade{height:2px;border-width:0;color:gray;background-color:gray;}";

    /** The Constant ALLOWTRANSPARENCY_CLASS. */
    public static final String ALLOWTRANSPARENCY_CLASS = ".allowtransparency{background: none transparent !important;}";

    /** The Constant IFRAME_SCROLLING_AUTO_CLASS. */
    public static final String IFRAME_SCROLLING_AUTO_CLASS = ".iframe_scrolling_auto{overflow:auto;}";

    /** The Constant IFRAME_SCROLLING_YES_CLASS. */
    public static final String IFRAME_SCROLLING_YES_CLASS = ".iframe_scrolling_yes{overflow:scroll;}";

    /** The Constant IFRAME_SCROLLING_NO_CLASS. */
    public static final String IFRAME_SCROLLING_NO_CLASS = ".iframe_scrolling_no{overflow:hidden;}";

    /** The Constant LIST_STYLE_SQUARE. */
    public static final String LIST_STYLE_SQUARE = ".list_style_square {list-style-type: square;}";

    /**
     * Instantiates a new html4 attribute css.
     *
     * @param attribute the attribute
     * @param cssPath the css path
     * @param css the css
     * @param cssUnit the css unit
     * @param cssClass the css class
     */
    private Html4AttributeCss(String attribute, String cssPath, String css,
            String cssUnit, String cssClass) {
        this.attribute = attribute;
        this.setCssPath(cssPath);
        this.css = css;
        this.cssUnit = cssUnit;
        this.cssClass = cssClass;
    }

    /**
     * Gets the attribute.
     *
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Sets the attribute.
     *
     * @param attribute the new attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * Gets the css.
     *
     * @return the css
     */
    public String getCss() {
        return css;
    }

    /**
     * Sets the css.
     *
     * @param css the new css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * Gets the css unit.
     *
     * @return the css unit
     */
    public String getCssUnit() {
        return cssUnit;
    }

    /**
     * Sets the css unit.
     *
     * @param cssUnit the new css unit
     */
    public void setCssUnit(String cssUnit) {
        this.cssUnit = cssUnit;
    }

    /**
     * Gets the css class.
     *
     * @return the css class
     */
    public String getCssClass() {
        return cssClass;
    }

    /**
     * Sets the css class.
     *
     * @param cssClass the new css class
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return attribute;
    }

    /**
     * Gets the css path.
     *
     * @return the css path
     */
    public String getCssPath() {
        return cssPath;
    }

    /**
     * Sets the css path.
     *
     * @param cssPath the new css path
     */
    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }
}
