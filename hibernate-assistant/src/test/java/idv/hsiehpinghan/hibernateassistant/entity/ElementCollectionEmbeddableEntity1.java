package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embeddable;

@Embeddable
public class ElementCollectionEmbeddableEntity1 {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
