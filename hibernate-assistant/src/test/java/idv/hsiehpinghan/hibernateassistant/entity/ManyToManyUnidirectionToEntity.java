package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ManyToManyUnidirectionToEntity {
	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
