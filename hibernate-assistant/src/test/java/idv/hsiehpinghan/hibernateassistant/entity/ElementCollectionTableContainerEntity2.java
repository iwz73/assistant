package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class ElementCollectionTableContainerEntity2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ElementCollection
	@CollectionTable(name = "elementCollectionTableEmbeddableEntity2", joinColumns = @JoinColumn(name = "ElementCollectionTableContainerEntity2Id"))
	private Set<ElementCollectionTableEmbeddableEntity2> elements;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ElementCollectionTableEmbeddableEntity2> getElements() {
		return elements;
	}

	public void setElements(
			Set<ElementCollectionTableEmbeddableEntity2> elements) {
		this.elements = elements;
	}

}
