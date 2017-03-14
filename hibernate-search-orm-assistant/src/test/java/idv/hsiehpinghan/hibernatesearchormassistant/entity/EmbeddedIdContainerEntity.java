package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import idv.hsiehpinghan.hibernatesearchormassistant.bridge.EmbeddedIdTwoWayFieldBridge;

@Entity
@Indexed
public class EmbeddedIdContainerEntity {
	@EmbeddedId
	@DocumentId
	@FieldBridge(impl = EmbeddedIdTwoWayFieldBridge.class)
	private EmbeddedIdEmbeddableEntity id;
	private long salary;

	public EmbeddedIdEmbeddableEntity getId() {
		return id;
	}

	public void setId(EmbeddedIdEmbeddableEntity id) {
		this.id = id;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
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
		EmbeddedIdContainerEntity other = (EmbeddedIdContainerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
