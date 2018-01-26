package idv.hsiehpinghan.seleniumchromedriverassistant.enumeration;

/**
 * reference : https://www.w3schools.com/cssref/default.asp
 */
public enum CssPropertyEnumeration {
	// @formatter:off 
	ALIGN_CONTENT("align-content"),	// stretch|center|flex-start|flex-end|space-between|space-around|initial|inherit
	ALIGN_ITEMS("align-items"),	// stretch|center|flex-start|flex-end|baseline|initial|inherit
	ALIGN_SELF("align-self"),	// auto|stretch|center|flex-start|flex-end|baseline|initial|inherit
	ANIMATION_DELAY("animation-delay"),	// time|initial|inherit
	ANIMATION_DIRECTION("animation-direction"),	// normal|reverse|alternate|alternate-reverse|initial|inherit
	ANIMATION_DURATION("animation-duration"),	// time|initial|inherit
	ANIMATION_FILL_MODE("animation-fill-mode"),	// none|forwards|backwards|both|initial|inherit
	ANIMATION_ITERATION_COUNT("animation-iteration-count"),	// number|infinite|initial|inherit
	ANIMATION_NAME("animation-name"),	// keyframename|none|initial|inherit
	ANIMATION_PLAY_STATE("animation-play-state"),	// paused|running|initial|inherit
	ANIMATION_TIMING_FUNCTION("animation-timing-function"),	// linear|ease|ease-in|ease-out|ease-in-out|step-start|step-end|steps(int,start|end)|cubic-bezier(n,n,n,n)|initial|inherit
	BACKFACE_VISIBILITY("backface-visibility"),	// visible|hidden|initial|inherit
	BACKGROUND_ATTACHMENT("background-attachment"),	// scroll|fixed|local|initial|inherit
	BACKGROUND_BLEND_MODE("background-blend-mode"),	// normal|multiply|screen|overlay|darken|lighten|color-dodge|saturation|color|luminosity
	BACKGROUND_CLIP("background-clip"),	// border-box|padding-box|content-box|initial|inherit
	BACKGROUND_COLOR("background-color"),	// color|transparent|initial|inherit
	BACKGROUND_ORIGIN("background-origin"),	// padding-box|border-box|content-box|initial|inherit;
	BACKGROUND_POSITION("background-position"),	// left top|left center|left bottom|right top|right center|right bottom|center top|center center|center bottom|x% y%|xpos ypos|initial|inherit
	BACKGROUND_REPEAT("background-repeat"),	// repeat|repeat-x|repeat-y|no-repeat|initial|inherit
	BACKGROUND_SIZE("background-size"),	// auto|length|cover|contain|initial|inherit
	BORDER_BOTTOM_COLOR("border-bottom-color"),	// color|transparent|initial|inherit
	BORDER_BOTTOM_LEFT_RADIUS("border-bottom-left-radius"),	// length|% [length|%]|initial|inherit
	BORDER_BOTTOM_RIGHT_RADIUS("border-bottom-right-radius"),	// length|% [length|%]|initial|inherit
	BORDER_BOTTOM_STYLE("border-bottom-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	BORDER_BOTTOM_WIDTH("border-bottom-width"),	// medium|thin|thick|length|initial|inherit
	BORDER_COLLAPSE("border-collapse"),	// separate|collapse|initial|inherit
	BORDER_COLOR("border-color"),	// color|transparent|initial|inherit
	BORDER_IMAGE_OUTSET("border-image-outset"),	// length|number|initial|inherit
	BORDER_IMAGE_REPEAT("border-image-repeat"),	// stretch|repeat|round|initial|inherit
	BORDER_IMAGE_SLICE("border-image-slice"),	// number|%|fill|initial|inherit
	BORDER_IMAGE_WIDTH("border-image-width"),	// number|%|auto|initial|inherit
	BORDER_LEFT_COLOR("border-left-color"),	// color|transparent|initial|inherit
	BORDER_LEFT_STYLE("border-left-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	BORDER_LEFT_WIDTH("border-left-width"),	// medium|thin|thick|length|initial|inherit
	BORDER_RADIUS("border-radius"),	// 1-4 length|% / 1-4 length|%|initial|inherit
	BORDER_RIGHT_COLOR("border-right-color"),	// color|transparent|initial|inherit
	BORDER_RIGHT_STYLE("border-right-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	BORDER_RIGHT_WIDTH("border-right-width"),	// medium|thin|thick|length|initial|inherit
	BORDER_SPACING("border-spacing"),	// length|initial|inherit
	BORDER_STYLE("border-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	BORDER_TOP_COLOR("border-top-color"),	// color|transparent|initial|inherit
	BORDER_TOP_LEFT_RADIUS("border-top-left-radius"),	// length|% [length|%]|initial|inherit
	BORDER_TOP_RIGHT_RADIUS("border-top-right-radius"),	// length|% [length|%]|initial|inherit
	BORDER_TOP_STYLE("border-top-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	BORDER_TOP_WIDTH("border-top-width"),	// medium|thin|thick|length|initial|inherit
	BORDER_WIDTH("border-width"),	// medium|thin|thick|length|initial|inherit
	BOTTOM("bottom"),	// auto|length|initial|inherit
	BOX_SHADOW("box-shadow"),	// none|h-offset v-offset blur spread color |inset|initial|inherit
	BOX_SIZING("box-sizing"),	// content-box|border-box|initial|inherit
	CAPTION_SIDE("caption-side"),	// top|bottom|initial|inherit
	CARET_COLOR("caret-color"),	// auto|color
	COLOR("color"),	// color|initial|inherit
	COLUMN_COUNT("column-count"),	// number|auto|initial|inherit
	COLUMN_FILL("column-fill"),	// balance|auto|initial|inherit
	COLUMN_GAP("column-gap"),	// length|normal|initial|inherit
	COLUMN_RULE_COLOR("column-rule-color"),	// color|initial|inherit
	COLUMN_RULE_STYLE("column-rule-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	COLUMN_RULE_WIDTH("column-rule-width"),	// medium|thin|thick|length|initial|inherit
	COLUMN_SPAN("column-span"),	// 1|all|initial|inherit
	COLUMN_WIDTH("column-width"),	// auto|length|initial|inherit
	CURSOR("cursor"),	// alias|all-scroll|auto|cell|context-menu|col-resize|copy|crosshair|default|e-resize|ew-resize|grab|grabbing|help|move|n-resize|ne-resize|nesw-resize|ns-resize|nw-resize|nwse-resize|no-drop|none|not-allowed|pointer|progress|row-resize|s-resize|se-resize|sw-resize|text|URL|vertical-text|w-resize|wait|zoom-in|zoom-out|initial|inherit
	DIRECTION("direction"),	// ltr|rtl|initial|inherit
	DISPLAY("display"),	// inline |block |flex |inline-block |inline-flex |inline-table |list-item |run-in |table |table-caption |table-column-group |table-header-group |table-footer-group |table-row-group |table-cell |table-column |table-row |none |initial |inherit 
	EMPTY_CELLS("empty-cells"),	// show|hide|initial|inherit
	FILTER("filter"),	// none |blur(px) |brightness(%) |contrast(%) |drop-shadow(h-shadow v-shadow blur spread color) |grayscale(%) |hue-rotate(deg) |invert(%) |opacity(%) |saturate(%) |sepia(%) |url() |initial |inherit 
	FLEX_BASIS("flex-basis"),	// number|auto|initial|inherit
	FLEX_DIRECTION("flex-direction"),	// row|row-reverse|column|column-reverse|initial|inherit
	FLEX_GROW("flex-grow"),	// number|initial|inherit
	FLEX_SHRINK("flex-shrink"),	// number|initial|inherit
	FLEX_WRAP("flex-wrap"),	// nowrap|wrap|wrap-reverse|initial|inherit
	FLOAT("float"),	// none|left|right|initial|inherit
	FONT_FAMILY("font-family"),	// family-name|generic-family|initial|inherit
	FONT_KERNING("font-kerning"),	// auto|normal|none
	FONT_SIZE("font-size"),	// medium|xx-small|x-small|small|large|x-large|xx-large|smaller|larger|length|initial|inherit
	FONT_SIZE_ADJUST("font-size-adjust"),	// number|none|initial|inherit
	FONT_STRETCH("font-stretch"),	// ultra-condensed|extra-condensed|condensed|semi-condensed|normal|semi-expanded|expanded|extra-expanded|ultra-expanded|initial|inherit
	FONT_STYLE("font-style"),	// normal|italic|oblique|initial|inherit
	FONT_VARIANT("font-variant"),	// normal|small-caps|initial|inherit
	FONT_WEIGHT("font-weight"),	// normal|bold|bolder|lighter|number|initial|inherit
	GRID_AUTO_COLUMNS("grid-auto-columns"),	// auto|max-content|min-content|length
	GRID_AUTO_FLOW("grid-auto-flow"),	// row|columns|row dense|column dense
	GRID_AUTO_ROWS("grid-auto-rows"),	// auto|max-content|min-content|length
	GRID_COLUMN_END("grid-column-end"),	// auto|column-line|span n
	GRID_COLUMN_GAP("grid-column-gap"),	// length
	GRID_COLUMN_START("grid-column-start"),	// auto|column-line
	GRID_ROW_END("grid-row-end"),	// auto|row-line|span n
	GRID_ROW_GAP("grid-row-gap"),	// length
	GRID_ROW_START("grid-row-start"),	// auto|row-line
	GRID_TEMPLATE_AREAS("grid-template-areas"),	// none|itemnames
	GRID_TEMPLATE_COLUMNS("grid-template-columns"),	// none|auto|max-content|min-content|length|initial|inherit
	GRID_TEMPLATE_ROWS("grid-template-rows"),	// none|auto|max-content|min-content|length|initial|inherit
	HANGING_PUNCTUATION("hanging-punctuation"),	// none|first|last|allow-end|force-end|initial|inherit
	HEIGHT("height"),	// auto|length|initial|inherit
	HYPHENS("hyphens"),	// none | manual | auto
	IMAGE_ORIENTATION("image-orientation"),	// from-image|deg|flip
	IMAGE_RENDERING("image-rendering"),	// auto|crisp-edges|pixelated
	IMAGE_RESOLUTION("image-resolution"),	// value
	JUSTIFY_CONTENT("justify-content"),	// flex-start|flex-end|center|space-between|space-around|initial|inherit
	LEFT("left"),	// auto|length|initial|inherit
	LETTER_SPACING("letter-spacing"),	// normal|length|initial|inherit
	LINE_BREAK("line-break"),	// auto | loose | normal | strict
	LINE_HEIGHT("line-height"),	// normal|number|length|initial|inherit
	LIST_STYLE_POSITION("list-style-position"),	// inside|outside|initial|inherit
	LIST_STYLE_TYPE("list-style-type"),	// disc|armenian|circle|cjk-ideographic|decimal|decimal-leading-zero|georgian|hebrew|hiragana|hiragana-iroha|katakana|katakana-iroha|lower-alpha|lower-greek|lower-latin|lower-roman|none|square|upper-alpha|upper-greek|upper-latin|upper-roman|initial|inherit
	MARGIN_BOTTOM("margin-bottom"),	// length|auto|initial|inherit
	MARGIN_LEFT("margin-left"),	// length|auto|initial|inherit
	MARGIN_RIGHT("margin-right"),	// length|auto|initial|inherit
	MARGIN_TOP("margin-top"),	// length|auto|initial|inherit
	MAX_HEIGHT("max-height"),	// none|length|initial|inherit
	MAX_WIDTH("max-width"),	// none|length|initial|inherit
	MIN_HEIGHT("min-height"),	// length|initial|inherit
	MIN_WIDTH("min-width"),	// length|initial|inherit
	OPACITY("opacity"),	// number|initial|inherit
	ORDER("order"),	// number|initial|inherit
	ORPHANS("orphans"),	// number
	OUTLINE_COLOR("outline-color"),	// invert|color|initial|inherit
	OUTLINE_OFFSET("outline-offset"),	// length|initial|inherit
	OUTLINE_STYLE("outline-style"),	// none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset|initial|inherit
	OUTLINE_WIDTH("outline-width"),	// medium|thin|thick|length|initial|inherit
	OVERFLOW_WRAP("overflow-wrap"),	// normal|break-word
	OVERFLOW_X("overflow-x"),	// visible|hidden|scroll|auto|initial|inherit
	OVERFLOW_Y("overflow-y"),	// visible|hidden|scroll|auto|initial|inherit
	PADDING_BOTTOM("padding-bottom"),	// length|initial|inherit
	PADDING_LEFT("padding-left"),	// length|initial|inherit
	PADDING_RIGHT("padding-right"),	// length|initial|inherit
	PADDING_TOP("padding-top"),	// length|initial|inherit
	PAGE_BREAK_AFTER("page-break-after"),	// auto|always|avoid|left|right|initial|inherit
	PAGE_BREAK_BEFORE("page-break-before"),	// auto|always|avoid|left|right|initial|inherit
	PAGE_BREAK_INSIDE("page-break-inside"),	// auto|avoid|initial|inherit
	POSITION("position"),	// static|absolute|fixed|relative|sticky|initial|inherit
	QUOTES("quotes"),	// none|string|initial|inherit
	RESIZE("resize"),	// none|both|horizontal|vertical|initial|inherit
	RIGHT("right"),	 // auto|length|initial|inherit
	TAB_SIZE("tab-size"),	// number|length|initial|inherit
	TABLE_LAYOUT("table-layout"),	// auto|fixed|initial|inherit
	TEXT_ALIGN("text-align"),	// left|right|center|justify|initial|inherit
	TEXT_ALIGN_LAST("text-align-last"),	// auto|left|right|center|justify|start|end|initial|inherit
	TEXT_COMBINE_UPRIGHT("text-combine-upright"),	// none|all|digits number
	TEXT_DECORATION_COLOR("text-decoration-color"),	// color|initial|inherit
	TEXT_DECORATION_LINE("text-decoration-line"),	// none|underline|overline|line-through|initial|inherit
	TEXT_DECORATION_STYLE("text-decoration-style"),	// solid|double|dotted|dashed|wavy|initial|inherit
	TEXT_INDENT("text-indent"),	// length|initial|inherit
	TEXT_JUSTIFY("text-justify"),	// auto|inter-word|inter-ideograph|inter-cluster|distribute|kashida|trim|initial|inherit
	TEXT_ORIENTATION("text-orientation"),	// mixed|upright|sideways
	TEXT_OVERFLOW("text-overflow"),	// clip|ellipsis|string|initial|inherit
	TEXT_SHADOW("text-shadow"),	// h-shadow v-shadow blur-radius color|none|initial|inherit
	TEXT_TRANSFORM("text-transform"),	// none|capitalize|uppercase|lowercase|initial|inherit
	TEXT_UNDERLINE_POSITION("text-underline-position"),	// auto|under|left|right
	TOP("top"),	// auto|length|initial|inherit
	TRANSFORM("transform"),	// none|transform-functions|initial|inherit
	TRANSFORM_ORIGIN("transform-origin"),	// x-axis y-axis z-axis|initial|inherit
	TRANSFORM_STYLE("transform-style"),	// flat|preserve-3d|initial|inherit
	TRANSITION_DELAY("transition-delay"),	// time|initial|inherit
	TRANSITION_DURATION("transition-duration"),	// time|initial|inherit
	TRANSITION_PROPERTY("transition-property"),	// none|all|property|initial|inherit
	TRANSITION_TIMING_FUNCTION("transition-timing-function"),	// linear|ease|ease-in|ease-out|ease-in-out|step-start|step-end|steps(int,start|end)|cubic-bezier(n,n,n,n)|initial|inherit
	UNICODE_BIDI("unicode-bidi"),	// normal|embed|bidi-override|initial|inherit
	USER_SELECT("user-select"),	// auto|none|text|all
	VERTICAL_ALIGN("vertical-align"),	// baseline|length|sub|super|top|text-top|middle|bottom|text-bottom|initial|inherit
	VISIBILITY("visibility"),	// visible|hidden|collapse|initial|inherit
	WHITE_SPACE("white-space"),	// normal|nowrap|pre|pre-line|pre-wrap|initial|inherit
	WIDTH("width"),	// auto|value|initial|inherit
	WORD_BREAK("word-break"),	// normal|break-all|keep-all|initial|inherit
	WORD_SPACING("word-spacing"),	// normal|length|initial|inherit
	WORD_WRAP("word-wrap"),	// normal|break-word|initial|inherit
	WRITING_MODE("writing-mode"),	// horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr
	Z_INDEX("z-index");	// auto|number|initial|inherit
	// @formatter:on

	private String name;

	private CssPropertyEnumeration(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
