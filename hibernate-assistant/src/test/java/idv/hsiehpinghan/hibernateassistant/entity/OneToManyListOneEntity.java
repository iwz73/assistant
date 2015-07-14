package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class OneToManyListOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OrderBy("name DESC")
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private List<OneToManyListManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OneToManyListManyEntity> getMany() {
		return many;
	}

	public void setMany(List<OneToManyListManyEntity> many) {
		this.many = many;
	}

}
