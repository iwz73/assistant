package idv.hsiehpinghan.seleniumchromedriverassistant.enumeration;

/**
 * reference : https://www.w3schools.com/cssref/default.asp
 */
public enum CssPropertyEnumeration {
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
//	CAPTION_SIDE("caption-side"),
//	CARET_COLOR("caret-color)
//	@CHARSET("@charset"),
//	CLEAR("clear)
//	CLIP("clip)
//	COLOR("color)
//	COLUMN_COUNT("column-count)
//	COLUMN_FILL("column-fill)
//	COLUMN_GAP("column-gap)
//	COLUMN_RULE("column-rule)
//	COLUMN_RULE_COLOR("column-rule-color)
//	COLUMN_RULE_STYLE("column-rule-style)
//	COLUMN_RULE_WIDTH("column-rule-width)
//	COLUMN_SPAN("column-span)
//	COLUMN_WIDTH("column-width)
//	COLUMNS("columns)
//	CONTENT("content)
//	COUNTER_INCREMENT("counter-increment)
//	COUNTER_RESET("counter-reset)
//	CURSOR("cursor)
//	DIRECTION("direction)
//	DISPLAY("display"),
//	EMPTY_CELLS("empty-cells)
//	FILTER("filter)
//	FLEX("flex)
//	FLEX_BASIS("flex-basis)
//	FLEX_DIRECTION("flex-direction"),
//	FLEX_FLOW("flex-flow"),
//	FLEX_GROW("flex-grow"),
//	FLEX_SHRINK("flex-shrink"),
//	FLEX_WRAP("flex-wrap"),
//	FLOAT("float)
//	FONT("font"),
//	@FONT_FACE("@font-face"),
//	FONT_FAMILY("font-family)
//	FONT_KERNING("font-kerning"),
//	FONT_SIZE("font-size"),
//	FONT_SIZE_ADJUST("font-size-adjust"),
//	FONT_STRETCH("font-stretch"),
//	FONT_STYLE("font-style"),
//	FONT_VARIANT("font-variant"),
//	FONT_WEIGHT("font-weight"),
//	GRID("grid)
//	GRID_AREA("grid-area"),
//	GRID_AUTO_COLUMNS("grid-auto-columns"),
//	GRID_AUTO_FLOW("grid-auto-flow"),
//	GRID_AUTO_ROWS("grid-auto-rows"),
//	GRID_COLUMN("grid-column"),
//	GRID_COLUMN_END("grid-column-end"),
//	GRID_COLUMN_GAP("grid-column-gap"),
//	GRID_COLUMN_START("grid-column-start"),
//	GRID_GAP("grid-gap"),
//	GRID_ROW("grid-row"),
//	GRID_ROW_END("grid-row-end"),
//	GRID_ROW_GAP("grid-row-gap"),
//	GRID_ROW_START("grid-row-start"),
//	GRID_TEMPLATE("grid-template"),
//	GRID_TEMPLATE_AREAS("grid-template-areas"),
//	GRID_TEMPLATE_COLUMNS("grid-template-columns"),
//	GRID_TEMPLATE_ROWS("grid-template-rows"),
//	HANGING_PUNCTUATION("hanging-punctuation)
//	HEIGHT("height)
//	JUSTIFY_CONTENT("justify-content"),
//	@KEYFRAMES("@keyframes"),
//	LEFT("left"),
//	LETTER_SPACING("letter-spacing)
//	LINE_HEIGHT("line-height)
//	LIST_STYLE("list-style)
//	LIST_STYLE_IMAGE("list-style-image"),
//	LIST_STYLE_POSITION("list-style-position"),
//	LIST_STYLE_TYPE("list-style-type"),
//	MARGIN("margin)
//	MARGIN_BOTTOM("margin-bottom"),
//	MARGIN_LEFT("margin-left"),
//	MARGIN_RIGHT("margin-right"),
//	MARGIN_TOP("margin-top"),
//	MAX_HEIGHT("max-height)
//	MAX_WIDTH("max-width"),
//	@MEDIA("@media"),
//	MIN_HEIGHT("min-height"),
//	MIN_WIDTH("min-width)
//	OBJECT_FIT("object-fit)
//	OPACITY("opacity)
//	ORDER("order"),
//	OUTLINE("outline)
//	OUTLINE_COLOR("outline-color"),
//	OUTLINE_OFFSET("outline-offset"),
//	OUTLINE_STYLE("outline-style"),
//	OUTLINE_WIDTH("outline-width"),
//	OVERFLOW("overflow)
//	OVERFLOW_X("overflow-x"),
//	OVERFLOW_Y("overflow-y"),
//	PADDING("padding)
//	PADDING_BOTTOM("padding-bottom"),
//	PADDING_LEFT("padding-left"),
//	PADDING_RIGHT("padding-right"),
//	PADDING_TOP("padding-top"),
//	PAGE_BREAK_AFTER("page-break-after)
//	PAGE_BREAK_BEFORE("page-break-before)
//	PAGE_BREAK_INSIDE("page-break-inside)
//	PERSPECTIVE("perspective)
//	PERSPECTIVE_ORIGIN("perspective-origin"),
//	POSITION("position)
//	QUOTES("quotes"),
//	RESIZE("resize)
//	RIGHT("right"),
//	TAB_SIZE("tab-size)
//	TABLE_LAYOUT("table-layout)
//	TEXT_ALIGN("text-align)
//	TEXT_ALIGN_LAST("text-align-last"),
//	TEXT_DECORATION("text-decoration"),
//	TEXT_DECORATION_COLOR("text-decoration-color"),
//	TEXT_DECORATION_LINE("text-decoration-line"),
//	TEXT_DECORATION_STYLE("text-decoration-style"),
//	TEXT_INDENT("text-indent"),
//	TEXT_JUSTIFY("text-justify"),
//	TEXT_OVERFLOW("text-overflow"),
//	TEXT_SHADOW("text-shadow"),
//	TEXT_TRANSFORM("text-transform"),
//	TOP("top)
//	TRANSFORM("transform"),
//	TRANSFORM_ORIGIN("transform-origin)
//	TRANSFORM_STYLE("transform-style"),
//	TRANSITION("transition)
//	TRANSITION_DELAY("transition-delay"),
//	TRANSITION_DURATION("transition-duration"),
//	TRANSITION_PROPERTY("transition-property"),
//	TRANSITION_TIMING_FUNCTION("transition-timing-function"),
//	UNICODE_BIDI("unicode-bidi"),
//	USER_SELECT("user-select)
//	VERTICAL_ALIGN("vertical-align)
//	VISIBILITY("visibility)
//	WHITE_SPACE("white-space"),
//	WIDTH("width)
//	WORD_BREAK("word-break)
//	WORD_SPACING("word-spacing"),
//	WORD_WRAP("word-wrap)
//	Z_INDEX("z-index)


	;

	private String name;

	private CssPropertyEnumeration(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
