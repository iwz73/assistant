package idv.hsiehpinghan.springbatchassistant.vo;

public class FlatFileVo {
	private Long longValue;
	private String stringValue;

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

	@Override
	public String toString() {
		return "FlatFileVo [longValue=" + longValue + ", stringValue=" + stringValue + "]";
	}

}
