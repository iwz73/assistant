package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyUnidirectionFromEntity {
	@Id
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ManyToManyUnidirectionFromEntity_ManyToManyUnidirectionToEntity", joinColumns = @JoinColumn(name = "ManyToManyUnidirectionFromEntityId"), inverseJoinColumns = @JoinColumn(name = "ManyToManyUnidirectionToEntityId"))
	private Collection<ManyToManyUnidirectionToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyUnidirectionToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<ManyToManyUnidirectionToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(ManyToManyUnidirectionToEntity to) {
		if (this.tos == null) {
			this.tos = new ArrayList<ManyToManyUnidirectionToEntity>();
		}
		this.tos.add(to);
	}
}
