package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ManyToOneManyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	private ManyToOneOneEntity one;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManyToOneOneEntity getOne() {
		return one;
	}

	public void setOne(ManyToOneOneEntity one) {
		this.one = one;
	}

}
