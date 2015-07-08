package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneBidirectionToEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(mappedBy = "to")
	private OneToOneBidirectionFromEntity from;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToOneBidirectionFromEntity getFrom() {
		return from;
	}

	public void setFrom(OneToOneBidirectionFromEntity from) {
		this.from = from;
	}

}
