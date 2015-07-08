package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOnePkMappingToEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(mappedBy = "to")
	private OneToOnePkMappingFromEntity from;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToOnePkMappingFromEntity getFrom() {
		return from;
	}

	public void setFrom(OneToOnePkMappingFromEntity from) {
		this.from = from;
	}

}
