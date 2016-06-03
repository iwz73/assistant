package idv.hsiehpinghan.nekohtmlassistant.vo;

public class ElementVo {
	private String serialNodeName;
	private String serialIndex;
	private String serialClass;
	private String visibleText;

	public ElementVo(String serialNodeName, String serialIndex, String serialClass, String visibleText) {
		super();
		this.serialNodeName = serialNodeName;
		this.serialIndex = serialIndex;
		this.serialClass = serialClass;
		this.visibleText = visibleText;
	}

	public String getSerialNodeName() {
		return serialNodeName;
	}

	public void setSerialNodeName(String serialNodeName) {
		this.serialNodeName = serialNodeName;
	}

	public String getSerialIndex() {
		return serialIndex;
	}

	public void setSerialIndex(String serialIndex) {
		this.serialIndex = serialIndex;
	}

	public String getSerialClass() {
		return serialClass;
	}

	public void setSerialClass(String serialClass) {
		this.serialClass = serialClass;
	}

	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

	@Override
	public String toString() {
		return "ElementVo [serialNodeName=" + serialNodeName + ", serialIndex=" + serialIndex + ", serialClass="
				+ serialClass + ", visibleText=" + visibleText + "]";
	}

}
