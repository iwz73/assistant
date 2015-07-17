package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OneToManyMapKeyManyEntity {
	@Id
	private Integer targetId;
	@ManyToOne
	private OneToManyMapKeyOneEntity one;

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public OneToManyMapKeyOneEntity getOne() {
		return one;
	}

	public void setOne(OneToManyMapKeyOneEntity one) {
		this.one = one;
	}

}
