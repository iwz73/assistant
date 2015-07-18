package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class OneToManyDerivedEmbeddedIdManyEntity {
	@EmbeddedId
	private OneToManyDerivedEmbeddedIdManyIdEntity id;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "oneKey1", referencedColumnName = "oneKey1", insertable = false, updatable = false),
			@JoinColumn(name = "oneKey2", referencedColumnName = "oneKey2", insertable = false, updatable = false) })
	private OneToManyDerivedEmbeddedIdOneEntity one;

	public OneToManyDerivedEmbeddedIdManyIdEntity getId() {
		return id;
	}

	public void setId(OneToManyDerivedEmbeddedIdManyIdEntity id) {
		this.id = id;
	}

	public OneToManyDerivedEmbeddedIdOneEntity getOne() {
		return one;
	}

	public void setOne(OneToManyDerivedEmbeddedIdOneEntity one) {
		this.one = one;
	}

}
