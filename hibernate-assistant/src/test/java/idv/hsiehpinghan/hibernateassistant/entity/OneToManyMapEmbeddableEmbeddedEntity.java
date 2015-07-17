package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OneToManyMapEmbeddableEmbeddedEntity {
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
