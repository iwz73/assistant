package idv.hsiehpinghan.jerseyassistant.vo;

public class CrudVo {
	private Integer id;
	private String message;

	public CrudVo() {
	}

	public CrudVo(Integer id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CrudVo [id=" + id + ", message=" + message + "]";
	}

}
