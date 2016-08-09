package com.emergya.descartes.conversor;

// TODO: Auto-generated Javadoc
/**
 * The Enum Html4NodeDeprecated.
 * Para las etiquetas que se han quedado obsoletos en html5 se establece porqué etiquetas sustituirlas
 * que cumplan la validación de w3c
 */
public enum Html4NodeDeprecated {

	/** The acronym. */
	ACRONYM("acronym","abbr",null),
	
	/** The bgsound. */
	BGSOUND("bgsound","audio",null),
	
	/** The dir. */
	DIR("dir","ul",null),
	
	/** The frame. */
	FRAME("frame","iframe",null),
	
	/** The frameset. */
	FRAMESET("frameset","iframe",null),
	
	/** The noframes. */
	NOFRAMES("noframes","iframe",null),
	
	/** The hgroup. */
	HGROUP("hgroup","div",null),
	
	/** The isindex. */
	ISINDEX("isindex","form",null),
	
	/** The listing. */
	LISTING("listing","pre",null),
	
	/** The nextid. */
	NEXTID("nextid",null,null),
	
	/** The noembed. */
	NOEMBED("noembed","object",null),
	
	/** The plaintext. */
	PLAINTEXT("plaintext",null,null),
	
	/** The strike. */
	STRIKE("strike",null,null),
	
	/** The xmp. */
	XMP("xmp","pre",null),
	
	/** The basefont. */
	BASEFONT("basefont",null,null),
	
	/** The big. */
	BIG("big","strong",null),
	
	/** The blink. */
	BLINK("blink","a",null),
	
	/** The center. */
	CENTER("center","div","div_center"),
	
	/** The font. */
	FONT("font",null,null),
	
	/** The marquee. */
	MARQUEE("marquee","span",null),
	
	/** The multicol. */
	MULTICOL("multicol",null,null),
	
	/** The nobr. */
	NOBR("nobr","span","span_nobr"),
	
	/** The spacer. */
	SPACER("spacer",null,null),
	
	/** The dl. */
	DL("dl","span","span_dl"),
	
	/** The u. */
	U("u","span","span_u"),
	
	/** The em. */
	EM("em","span","span_em"),
	
	/** The tt. */
	TT("tt","span","tt_span"),
	
	/** The op. */
	OP("o:p","span","span_remove"),
	
	/** The U5 p. */
	U5P("u5:p","span","span_remove"),
	
	/** The U7 p. */
	U7P("u7:p","span","span_remove");
	
	/** Etiqueta html4 que ha quedado obsoleta. */
	private String node;
	
	/** Etiqueta html5 por la que es reemplazada */
	private String replace;
	
	/** Clase css que es necesario incluirle a la etiqueta html5 reeemplazada,
	 * para que el diseño sea smilar a como se comportaba la etiqueta html4  */
	private String cssClass;
	
	
	/** The Constant DIV_CENTER_CLASS. */
	public static final String DIV_CENTER_CLASS = ".div_center{text-align: center;}";
	
	/** The Constant SPAN_DL. */
	public static final String SPAN_DL = ".span_dl{display: block;margin-top: 1em; margin-bottom: 1em;margin-left: 0;margin-right: 0;}";
	
	/** The Constant SPAN_U. */
	public static final String SPAN_U = ".span_u{text-decoration:underline}";
	
	/** The Constant SPAN_EM. */
	public static final String SPAN_EM = ".span_em{font-style:italic}";
	
	/** The Constant SPAN_TT. */
	public static final String SPAN_TT = ".span_tt{font-family:'Lucida Console', monospace}";
	
	/** The Constant SPAN_OP. */
	public static final String SPAN_OP = ".span_remove{display:none}";
	
	/** The Constant SPAN_NOBR. */
	public static final String SPAN_NOBR = ".span_nobr{white-space:nowrap;}";
	
	/**
	 * Instantiates a new _ html4 node deprecated.
	 *
	 * @param node the node
	 * @param replace the replace
	 * @param cssClass the css class
	 */
	private Html4NodeDeprecated(String node, String replace, String cssClass){
		this.node = node;
		this.replace = replace;
		this.cssClass = cssClass;
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

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString(){
		return node;
	}

	/**
	 * Gets the replace.
	 *
	 * @return the replace
	 */
	public String getReplace() {
		return replace;
	}

	/**
	 * Sets the replace.
	 *
	 * @param replace the new replace
	 */
	public void setReplace(String replace) {
		this.replace = replace;
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
	
}
