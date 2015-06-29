package idv.hsiehpinghan.springmvcassistant.criteria;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ValidationCriteria {
	@NotEmpty
	private String stringValue;

	@Range(min = 1, max = 100)
	private int integerValue;

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
	}

}
