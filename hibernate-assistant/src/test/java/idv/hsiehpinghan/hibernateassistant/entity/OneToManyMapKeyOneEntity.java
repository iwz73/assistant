package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class OneToManyMapKeyOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@MapKey(name = "targetId")
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private Map<Integer, OneToManyMapKeyManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, OneToManyMapKeyManyEntity> getMany() {
		return many;
	}

	public void setMany(Map<Integer, OneToManyMapKeyManyEntity> many) {
		this.many = many;
	}

}
