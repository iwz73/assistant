package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LifeCycleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String string;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
