package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddedObjectEmbeddableEntity {
	private String embeddableString;

	public String getEmbeddableString() {
		return embeddableString;
	}

	public void setEmbeddableString(String embeddableString) {
		this.embeddableString = embeddableString;
	}

}
