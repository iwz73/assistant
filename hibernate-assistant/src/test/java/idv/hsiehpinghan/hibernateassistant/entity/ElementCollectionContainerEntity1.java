package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ElementCollectionContainerEntity1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ElementCollection
	private Set<ElementCollectionEmbeddableEntity1> elements;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ElementCollectionEmbeddableEntity1> getElements() {
		return elements;
	}

	public void setElements(Set<ElementCollectionEmbeddableEntity1> elements) {
		this.elements = elements;
	}

}
