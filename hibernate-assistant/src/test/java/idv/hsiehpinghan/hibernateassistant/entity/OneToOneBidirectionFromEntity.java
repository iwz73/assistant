package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneBidirectionFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private OneToOneBidirectionToEntity to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToOneBidirectionToEntity getTo() {
		return to;
	}

	public void setTo(OneToOneBidirectionToEntity to) {
		this.to = to;
	}

}
