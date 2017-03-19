package idv.hsiehpinghan.hibernatesearchormassistant.bridge;

import org.hibernate.search.bridge.StringBridge;

public class StringBridgeBridge implements StringBridge {
	public double round = 10.0;
	public int length = 3;
	public String pad = "0";

	@Override
	public String objectToString(Object object) {
		if (object == null)
			return null;
		if ((object instanceof Double) == false) {
			throw new RuntimeException("object class(" + object.getClass() + ") is not instance of Double !!!");
		}
		double value = (Double) object;
		double roundedValue = round(value);
		String result = pad(roundedValue);
		return result;
	}

	private double round(double value) {
		return Math.round(value / round) * round;
	}

	private String pad(double value) {
		String valueString = String.valueOf(value);
		int size = valueString.length();
		if (size > length) {
			throw new RuntimeException("value(" + value + ") size(" + size + ") > length(" + length + ") !!!");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = size; i <= length; ++i) {
			sb.append(pad);
		}
		sb.append(valueString);
		return sb.toString();
	}
}
