package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Parent Page ID
	 */
	@Column(name = "location_id")
	private String id;
	/**
	 * Latitude
	 */
	@Column(name = "location_latitude")
	private double latitude;
	/**
	 * Longitude
	 */
	@Column(name = "location_longitude")
	private double longitude;
	/**
	 * Street
	 */
	@Column(name = "location_street")
	private String street;
	/**
	 * City
	 */
	@Column(name = "location_city")
	private String city;
	/**
	 * State
	 */
	@Column(name = "location_state")
	private String state;
	/**
	 * Country
	 */
	@Column(name = "location_country")
	private String country;
	/**
	 * Zip code
	 */
	@Column(name = "location_zip")
	private String zip;
	// private String description;
	/**
	 * The parent location if this location is located within another location
	 */
	@Column(name = "location_located_in")
	private String locatedIn;
	/**
	 * Name
	 */
	@Column(name = "location_name")
	private String name;
	/**
	 * Region
	 */
	@Column(name = "location_region")
	private String region;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLocatedIn() {
		return locatedIn;
	}

	public void setLocatedIn(String locatedIn) {
		this.locatedIn = locatedIn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
