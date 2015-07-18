package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class OneToManyDerivedEmbeddedIdOneEntity {
	@EmbeddedId
	private OneToManyDerivedEmbeddedIdOneIdEntity id;
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private Collection<OneToManyDerivedEmbeddedIdManyEntity> many;

	public OneToManyDerivedEmbeddedIdOneIdEntity getId() {
		return id;
	}

	public void setId(OneToManyDerivedEmbeddedIdOneIdEntity id) {
		this.id = id;
	}

	public Collection<OneToManyDerivedEmbeddedIdManyEntity> getMany() {
		return many;
	}

	public void setMany(Collection<OneToManyDerivedEmbeddedIdManyEntity> many) {
		this.many = many;
	}

}
