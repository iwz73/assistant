package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneUnidirectionFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private OneToOneUnidirectionToEntity to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToOneUnidirectionToEntity getTo() {
		return to;
	}

	public void setTo(OneToOneUnidirectionToEntity to) {
		this.to = to;
	}

}
