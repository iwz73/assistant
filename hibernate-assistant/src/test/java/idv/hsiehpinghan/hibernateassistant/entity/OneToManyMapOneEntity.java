package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class OneToManyMapOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@MapKeyColumn(name = "mapKey")
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private Map<String, OneToManyMapManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, OneToManyMapManyEntity> getMany() {
		return many;
	}

	public void setMany(Map<String, OneToManyMapManyEntity> many) {
		this.many = many;
	}

}
