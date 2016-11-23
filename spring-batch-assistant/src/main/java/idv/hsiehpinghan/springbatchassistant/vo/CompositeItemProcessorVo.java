package idv.hsiehpinghan.springbatchassistant.vo;

public class CompositeItemProcessorVo {
	private long longValue;
	private String stringValue;

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
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
		return "ValidatorJavaBeanVo [longValue=" + longValue + ", stringValue=" + stringValue + "]";
	}

}
