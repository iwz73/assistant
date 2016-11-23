package idv.hsiehpinghan.springbatchassistant.vo;

public class RetryVo {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (longValue ^ (longValue >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetryVo other = (RetryVo) obj;
		if (longValue != other.longValue)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RetryVo [longValue=" + longValue + ", stringValue=" + stringValue + "]";
	}

}
