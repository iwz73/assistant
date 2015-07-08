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
public class ManyToManyJoinTableFromEntity {
	@Id
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ManyToManyJoinTableFromEntity_ManyToManyJoinTableToEntity", joinColumns = @JoinColumn(name = "ManyToManyJoinTableFromEntityId"), inverseJoinColumns = @JoinColumn(name = "ManyToManyJoinTableToEntityId"))
	private Collection<ManyToManyJoinTableToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyJoinTableToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<ManyToManyJoinTableToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(ManyToManyJoinTableToEntity to) {
		if (this.tos == null) {
			this.tos = new ArrayList<ManyToManyJoinTableToEntity>();
		}
		this.tos.add(to);
	}
}
