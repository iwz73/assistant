package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OneToManyOrderColumnManyEntity {
	@Id
	private Integer id;

	@ManyToOne
	private OneToManyOrderColumnOneEntity one;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToManyOrderColumnOneEntity getOne() {
		return one;
	}

	public void setOne(OneToManyOrderColumnOneEntity one) {
		this.one = one;
	}

}
