package idv.hsiehpinghan.jmxassistant.mxbean;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class CompositeData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int integer;
	private String string;

	@ConstructorProperties({ "integer", "string" })
	public CompositeData(int integer, String string) {
		super();
		this.integer = integer;
		this.string = string;
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
