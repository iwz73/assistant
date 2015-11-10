package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CascadeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@OneToMany(cascade = CascadeType.PERSIST)
	private Collection<CascadePersistEntity> cascadePersistEntities;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Collection<CascadeMergeEntity> cascadeMergeEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<CascadePersistEntity> getCascadePersistEntities() {
		return cascadePersistEntities;
	}

	public void setCascadePersistEntities(
			Collection<CascadePersistEntity> cascadePersistEntities) {
		this.cascadePersistEntities = cascadePersistEntities;
	}

	public Collection<CascadeMergeEntity> getCascadeMergeEntities() {
		return cascadeMergeEntities;
	}

	public void setCascadeMergeEntities(
			Collection<CascadeMergeEntity> cascadeMergeEntities) {
		this.cascadeMergeEntities = cascadeMergeEntities;
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
		CascadeEntity other = (CascadeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
