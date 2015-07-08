package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class OneToOnePkMappingFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@PrimaryKeyJoinColumn
	@OneToOne(cascade = CascadeType.ALL)
	private OneToOnePkMappingToEntity to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToOnePkMappingToEntity getTo() {
		return to;
	}

	public void setTo(OneToOnePkMappingToEntity to) {
		this.to = to;
	}

}
