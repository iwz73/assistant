package idv.hsiehpinghan.springcloudstarterassistant.criteria;

public abstract class BasicCriteria {
	private Integer id;
	private String string;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "BasicCriteria [id=" + id + ", string=" + string + "]";
	}

}
