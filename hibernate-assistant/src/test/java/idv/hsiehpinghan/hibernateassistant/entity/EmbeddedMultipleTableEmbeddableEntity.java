package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddedMultipleTableEmbeddableEntity {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
