package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "MAILING_ADDRESS")
public class MailingAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The mailing address ID
	 */
	@Id
	private String id;
	/**
	 * Address city name
	 */
	private String city;
	/**
	 * Page representing the address city
	 */
	@ManyToOne
	@JoinColumn(name = "city_page")
	private Reference cityPage;
	/**
	 * Country of the address
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String country;
	/**
	 * Street address
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String street1;
	/**
	 * Second part of the street address - apt, suite, etc
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String street2;
	/**
	 * Region or state of the address
	 */
	private String region;
	/**
	 * Postal code of the address
	 */
	private String postalCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Reference getCityPage() {
		return cityPage;
	}

	public void setCityPage(Reference cityPage) {
		this.cityPage = cityPage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailingAddress other = (MailingAddress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
