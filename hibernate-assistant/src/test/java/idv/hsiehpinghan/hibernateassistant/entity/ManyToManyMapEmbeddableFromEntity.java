package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyMapEmbeddableFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ManyToManyMapEmbeddableJoinTable", joinColumns = @JoinColumn(name = "ManyToManyMapEmbeddableFromEntityId"), inverseJoinColumns = @JoinColumn(name = "ManyToManyMapEmbeddableToEntityId"))
	private Map<ManyToManyMapEmbeddableValueObject, ManyToManyMapEmbeddableToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<ManyToManyMapEmbeddableValueObject, ManyToManyMapEmbeddableToEntity> getTos() {
		return tos;
	}

	public void setTos(
			Map<ManyToManyMapEmbeddableValueObject, ManyToManyMapEmbeddableToEntity> tos) {
		this.tos = tos;
	}

}
