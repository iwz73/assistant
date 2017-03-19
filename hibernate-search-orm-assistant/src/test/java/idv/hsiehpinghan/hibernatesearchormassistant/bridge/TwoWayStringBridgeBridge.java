package idv.hsiehpinghan.hibernatesearchormassistant.bridge;

import java.util.Map;

import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;

public class TwoWayStringBridgeBridge implements TwoWayStringBridge, ParameterizedBridge {
	public double round;
	public int length;
	public String pad;

	@Override
	public void setParameterValues(Map<String, String> parameters) {
		if (parameters.containsKey("round")) {
			round = Double.parseDouble(String.valueOf(parameters.get("round")));
		}
		if (parameters.containsKey("length")) {
			length = Integer.parseInt(String.valueOf(parameters.get("length")));
		}
		if (parameters.containsKey("pad")) {
			pad = String.valueOf(parameters.get("pad"));
		}
	}

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

	@Override
	public Object stringToObject(String stringValue) {
		return Double.parseDouble(stringValue);
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
