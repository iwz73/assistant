package idv.hsiehpinghan.nekohtmlassistant.vo;

public class ElementVo {
	private String serialNodeName;
	private String serialIndex;
	private String visibleText;

	public ElementVo(String serialNodeName, String serialIndex, String visibleText) {
		super();
		this.serialNodeName = serialNodeName;
		this.serialIndex = serialIndex;
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

	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

}
