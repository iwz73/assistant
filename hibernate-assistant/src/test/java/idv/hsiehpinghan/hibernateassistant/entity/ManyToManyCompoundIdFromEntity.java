package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyCompoundIdFromEntity {
	@EmbeddedId
	private ManyToManyCompoundIdFromIdEntity id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "manyToManyCompoundIdJoinTable", joinColumns = {
			@JoinColumn(name = "manyToManyCompoundIdFromEntityId1", referencedColumnName = "fromKey1"),
			@JoinColumn(name = "manyToManyCompoundIdFromEntityId2", referencedColumnName = "fromKey2") }, inverseJoinColumns = @JoinColumn(name = "manyToManyCompoundIdToEntityId"))
	private Collection<ManyToManyCompoundIdToEntity> tos;

	public ManyToManyCompoundIdFromIdEntity getId() {
		return id;
	}

	public void setId(ManyToManyCompoundIdFromIdEntity id) {
		this.id = id;
	}

	public Collection<ManyToManyCompoundIdToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<ManyToManyCompoundIdToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(ManyToManyCompoundIdToEntity to) {
		if (this.tos == null) {
			this.tos = new HashSet<ManyToManyCompoundIdToEntity>();
		}
		this.tos.add(to);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManyToManyCompoundIdFromEntity other = (ManyToManyCompoundIdFromEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
