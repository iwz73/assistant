package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ManyToOneJoinColumnManyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "JOIN_COLUMN")
	private ManyToOneJoinColumnOneEntity one;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManyToOneJoinColumnOneEntity getOne() {
		return one;
	}

	public void setOne(ManyToOneJoinColumnOneEntity one) {
		this.one = one;
	}

}
