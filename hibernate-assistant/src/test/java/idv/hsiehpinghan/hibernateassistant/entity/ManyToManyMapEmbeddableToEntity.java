package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyMapEmbeddableToEntity {
	@Id
	private Integer id;
	@Embedded
	private ManyToManyMapEmbeddableEmbeddedEntity embeddable;
	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyMapEmbeddableFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManyToManyMapEmbeddableEmbeddedEntity getEmbeddable() {
		return embeddable;
	}

	public void setEmbeddable(ManyToManyMapEmbeddableEmbeddedEntity embeddable) {
		this.embeddable = embeddable;
	}

	public Collection<ManyToManyMapEmbeddableFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<ManyToManyMapEmbeddableFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(ManyToManyMapEmbeddableFromEntity from) {
		if (froms == null) {
			froms = new HashSet<ManyToManyMapEmbeddableFromEntity>();
		}
	}
}
