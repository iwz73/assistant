package idv.hsiehpinghan.springbatchassistant.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ValidatorJavaBeanVo {
	@Min(3)
	private long longValue;
	@NotNull
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
