package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OneToManyMapEmbeddableOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private Map<OneToManyMapEmbeddableEmbeddedEntity, OneToManyMapEmbeddableManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<OneToManyMapEmbeddableEmbeddedEntity, OneToManyMapEmbeddableManyEntity> getMany() {
		return many;
	}

	public void setMany(
			Map<OneToManyMapEmbeddableEmbeddedEntity, OneToManyMapEmbeddableManyEntity> many) {
		this.many = many;
	}

}
