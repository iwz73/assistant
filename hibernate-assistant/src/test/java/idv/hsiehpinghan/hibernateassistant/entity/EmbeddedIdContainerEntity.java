package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmbeddedIdContainerEntity {
	@EmbeddedId
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

}
