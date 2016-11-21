package idv.hsiehpinghan.springbatchassistant.vo;

public class FlatFileJsonVo {
	private int lineNumber;
	private Long longValue;
	private String stringValue;
	private SubMapFlatFileJsonVo subMapFlatFileJsonVo;

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public SubMapFlatFileJsonVo getSubMapFlatFileJsonVo() {
		return subMapFlatFileJsonVo;
	}

	public void setSubMapFlatFileJsonVo(SubMapFlatFileJsonVo subMapFlatFileJsonVo) {
		this.subMapFlatFileJsonVo = subMapFlatFileJsonVo;
	}

	public static class SubMapFlatFileJsonVo {
		private Float floatValue;

		public Float getFloatValue() {
			return floatValue;
		}

		public void setFloatValue(Float floatValue) {
			this.floatValue = floatValue;
		}

	}

}
