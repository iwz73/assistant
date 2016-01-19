package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AdminCreator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "admin_creator_id")
	private String id;
	@Column(name = "admin_creator_name")
	private String name;
	@Column(name = "admin_creator_namespace")
	private String namespace;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
