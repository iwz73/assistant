package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmbeddedObjectContainerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	private EmbeddedObjectEmbeddableEntity embedded;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EmbeddedObjectEmbeddableEntity getEmbedded() {
		return embedded;
	}

	public void setEmbedded(EmbeddedObjectEmbeddableEntity embedded) {
		this.embedded = embedded;
	}

}
