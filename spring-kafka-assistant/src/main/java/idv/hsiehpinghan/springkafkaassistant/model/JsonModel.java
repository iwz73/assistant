package idv.hsiehpinghan.springkafkaassistant.model;

public class JsonModel {
	private Integer integer;
	private String string;

	public JsonModel() {
		super();
	}

	public JsonModel(Integer integer, String string) {
		super();
		this.integer = integer;
		this.string = string;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "JsonModel [integer=" + integer + ", string=" + string + "]";
	}

}
