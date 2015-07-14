package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class OneToManyOrderColumnOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	@OrderColumn(name = "orderColumn")
	private List<OneToManyOrderColumnManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OneToManyOrderColumnManyEntity> getMany() {
		return many;
	}

	public void setMany(List<OneToManyOrderColumnManyEntity> many) {
		this.many = many;
	}

}
