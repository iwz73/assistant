package idv.hsiehpinghan.springcloudstarterassistant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String string;

	public BasicEntity() {
		super();
	}

	public BasicEntity(Integer id, String string) {
		super();
		this.id = id;
		this.string = string;
	}

	public Integer getId() {
		return id;
	}

	public String getString() {
		return string;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "BasicEntity [id=" + id + ", string=" + string + "]";
	}

}