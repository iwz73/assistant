package idv.hsiehpinghan.nekohtmlassistant.vo;

public class ElementVo {
	private String serialAncestorNodeName;
	private String serialAncestorIndex;
	private String serialAncestorClass;
	private String serialPosterityNodeName;
	private String serialPosterityIndex;
	private String serialPosterityClass;
	private String visibleText;

	public ElementVo(String serialAncestorNodeName, String serialAncestorIndex, String serialAncestorClass,
			String serialPosterityNodeName, String serialPosterityIndex, String serialPosterityClass,
			String visibleText) {
		super();
		this.serialAncestorNodeName = serialAncestorNodeName;
		this.serialAncestorIndex = serialAncestorIndex;
		this.serialAncestorClass = serialAncestorClass;
		this.serialPosterityNodeName = serialPosterityNodeName;
		this.serialPosterityIndex = serialPosterityIndex;
		this.serialPosterityClass = serialPosterityClass;
		this.visibleText = visibleText;
	}

	public String getSerialAncestorNodeName() {
		return serialAncestorNodeName;
	}

	public void setSerialAncestorNodeName(String serialAncestorNodeName) {
		this.serialAncestorNodeName = serialAncestorNodeName;
	}

	public String getSerialAncestorIndex() {
		return serialAncestorIndex;
	}

	public void setSerialAncestorIndex(String serialAncestorIndex) {
		this.serialAncestorIndex = serialAncestorIndex;
	}

	public String getSerialAncestorClass() {
		return serialAncestorClass;
	}

	public void setSerialAncestorClass(String serialAncestorClass) {
		this.serialAncestorClass = serialAncestorClass;
	}

	public String getSerialPosterityNodeName() {
		return serialPosterityNodeName;
	}

	public void setSerialPosterityNodeName(String serialPosterityNodeName) {
		this.serialPosterityNodeName = serialPosterityNodeName;
	}

	public String getSerialPosterityIndex() {
		return serialPosterityIndex;
	}

	public void setSerialPosterityIndex(String serialPosterityIndex) {
		this.serialPosterityIndex = serialPosterityIndex;
	}

	public String getSerialPosterityClass() {
		return serialPosterityClass;
	}

	public void setSerialPosterityClass(String serialPosterityClass) {
		this.serialPosterityClass = serialPosterityClass;
	}

	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

	@Override
	public String toString() {
		return serialPosterityClass;
	}

}
