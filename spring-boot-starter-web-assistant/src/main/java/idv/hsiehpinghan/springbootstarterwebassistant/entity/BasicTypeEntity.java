package idv.hsiehpinghan.springbootstarterwebassistant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BasicTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String string;

	public BasicTypeEntity() {
		super();
	}

	public BasicTypeEntity(String string) {
		super();
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

}