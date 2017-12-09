package idv.hsiehpinghan.seleniumchromedriverassistant.enumeration;

public enum BoundingClientRectEnumeration {
	// @formatter:off
	TOP("top"),
	LEFT("left"),
	BOTTOM("bottom"),
	WIDTH("width"),
	X("x"),
	Y("y"),
	RIGHT("right"),
	HEIGHT("height");
	// @formatter:on

	private String name;

	private BoundingClientRectEnumeration(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
