package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyCompoundIdToEntity {
	@Id
	private Integer id;

	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyCompoundIdFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyCompoundIdFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<ManyToManyCompoundIdFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(ManyToManyCompoundIdFromEntity from) {
		if (this.froms == null) {
			this.froms = new HashSet<ManyToManyCompoundIdFromEntity>();
		}
		this.froms.add(from);
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
		ManyToManyCompoundIdToEntity other = (ManyToManyCompoundIdToEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
