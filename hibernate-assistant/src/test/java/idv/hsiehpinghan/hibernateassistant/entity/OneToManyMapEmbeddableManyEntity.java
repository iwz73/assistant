package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OneToManyMapEmbeddableManyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	private OneToManyMapEmbeddableEmbeddedEntity embeddable;
	@ManyToOne
	private OneToManyMapEmbeddableOneEntity one;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OneToManyMapEmbeddableEmbeddedEntity getEmbeddable() {
		return embeddable;
	}

	public void setEmbeddable(OneToManyMapEmbeddableEmbeddedEntity embeddable) {
		this.embeddable = embeddable;
	}

	public OneToManyMapEmbeddableOneEntity getOne() {
		return one;
	}

	public void setOne(OneToManyMapEmbeddableOneEntity one) {
		this.one = one;
	}

}
