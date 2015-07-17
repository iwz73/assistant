package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class OneToManyMapEmbeddableOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "OneToManyMapEmbeddableJoinTable", joinColumns = @JoinColumn(name = "OneToManyMapEmbeddableOneEntityId"), inverseJoinColumns = @JoinColumn(name = "OneToManyMapEmbeddableManyEntityId"))
	private Map<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> getMany() {
		return many;
	}

	public void setMany(
			Map<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> many) {
		this.many = many;
	}

}
