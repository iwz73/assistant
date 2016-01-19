package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
//import org.springframework.social.facebook.api.AgeRange;
//import org.springframework.social.facebook.api.CoverPhoto;
//import org.springframework.social.facebook.api.Currency;
//import org.springframework.social.facebook.api.Device;
//import org.springframework.social.facebook.api.EducationExperience;
//import org.springframework.social.facebook.api.Experience;
//import org.springframework.social.facebook.api.Location;
//import org.springframework.social.facebook.api.PaymentPricePoints;
//import org.springframework.social.facebook.api.Reference;
//import org.springframework.social.facebook.api.SecuritySettings;
//import org.springframework.social.facebook.api.VideoUploadLimits;
//import org.springframework.social.facebook.api.WorkEntry;
//import org.springframework.social.facebook.api.Page.PriceRange;
import org.springframework.social.facebook.api.AgeRange;

@Entity
@Table(name = "PAGE")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The id of this person's user account. This ID is unique to each app and
	 * cannot be used across different apps. Our upgrade guide provides more
	 * information about app-specific IDs
	 */
	@Id
	private String id;
	/**
	 * The About Me section of this person's profile
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String about;
	@Embedded
	private Location address;
	/**
	 * The age segment for this person expressed as a minimum and maximum age.
	 * For example, more than 18, less than 21.
	 */
	@Enumerated(EnumType.STRING)
	private AgeRange ageRange;
	/**
	 * The person's bio
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String bio;
	/**
	 * The person's birthday. This is a fixed format string, like MM/DD/YYYY.
	 * However, people can control who can see the year they were born
	 * separately from the month and day so this string can be only the year
	 * (YYYY) or the month + day (MM/DD)
	 */
	private String birthday;
	/**
	 * The person's cover photo
	 */
	@ManyToOne
	@JoinColumn(name = "cover")
	private CoverPhoto cover;
	/**
	 * The person's local currency information
	 */
	@Embedded
	private Currency currency;
	/**
	 * The list of devices the person is using. This will return only iOS and
	 * Android devices
	 */
	@ElementCollection
	private List<Device> devices;
	/**
	 * The person's education
	 */
	@ManyToMany
	@Column(name = "education")
	@JoinTable(name = "J_USER_EDUCATION", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "education_experience_id"))
	private List<EducationExperience> education;
	/**
	 * The person's primary email address listed on their profile. This field
	 * will not be returned if no valid email address is available
	 */
	private String email;
	/**
	 * Athletes the person likes
	 */
	@ManyToMany
	@Column(name = "favorite_athletes")
	@JoinTable(name = "J_USER_FAVORITE_ATHLETES", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> favoriteAthletes;
	/**
	 * Sports teams the person likes
	 */
	@ManyToMany
	@Column(name = "favorite_teams")
	@JoinTable(name = "J_USER_FAVORITE_TEAMS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> favoriteTeams;
	/**
	 * The person's first name
	 */
	private String firstName;
	/**
	 * The gender selected by this person, male or female. This value will be
	 * omitted if the gender is set to a custom value
	 */
	private String gender;
	/**
	 * The person's hometown
	 */
	@ManyToOne
	@JoinColumn(name = "hometown")
	private Reference hometown;
	/**
	 * The person's inspirational people
	 */
	@ManyToMany
	@Column(name = "inspirational_people")
	@JoinTable(name = "J_USER_INSPIRATIONAL_PEOPLE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> inspirationalPeople;
	/**
	 * Is the app making the request installed?
	 */
	private boolean installed;
	/**
	 * Install type
	 */
	private String installType;
	/**
	 * Genders the person is interested in
	 */
	@ElementCollection
	@Column(name = "interested_in")
	private List<String> interestedIn;
	/**
	 * People with large numbers of followers can have the authenticity of their
	 * identity manually verified by Facebook. This field indicates whether the
	 * person's profile is verified in this way. This is distinct from the
	 * verified field
	 */
	private boolean isIdentityVerified;
	/**
	 * Facebook Pages representing the languages this person knows
	 */
	@ManyToMany
	@Column(name = "languages")
	@JoinTable(name = "J_USER_LANGUAGES", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> languages;
	/**
	 * The person's last name
	 */
	@Column(name = "last_name")
	private String lastName;
	/**
	 * A link to the person's Timeline
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String link;
	// private Locale locale;
	/**
	 * The person's current location as entered by them on their profile. This
	 * field is not related to check-ins
	 */
	@ManyToOne
	@JoinColumn(name = "location")
	private Reference location;
	/**
	 * The person's middle name
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String middleName;
	/**
	 * What the person is interested in meeting for
	 */
	@ElementCollection
	@Column(name = "meeting_for")
	private List<String> meetingFor;
	/**
	 * The person's full name
	 */
	private String name;
	/**
	 * The person's name formatted to correctly handle Chinese, Japanese, or
	 * Korean ordering
	 */
	@Column(name = "name_format")
	private String nameFormat;
	/**
	 * The person's payment pricepoints
	 */
	@ManyToOne
	@JoinColumn(name = "payment_pricepoints")
	private PaymentPricePoints paymentPricePoints;
	/**
	 * The person's political views
	 */
	private String political;
	/**
	 * The person's favorite quotes
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String quotes;
	/**
	 * The person's relationship status
	 */
	@Column(name = "relationship_status")
	private String relationshipStatus;
	/**
	 * The person's religion
	 */
	private String religion;
	/**
	 * Security settings
	 */
	@ManyToOne
	@JoinColumn(name = "security_settings")
	private SecuritySettings securitySettings;
	/**
	 * The person's significant other
	 */
	@ManyToOne
	@JoinColumn(name = "significant_other")
	private Reference significantOther;
	/**
	 * Sports this person likes
	 */
	@ManyToMany
	@Column(name = "sports")
	@JoinTable(name = "J_USER_SPORTS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "experience_id"))
	private List<Experience> sports;
	/**
	 * Platform test group
	 */
	private int testGroup;
	/**
	 * A string containing an anonymous, but unique identifier for the person.
	 * You can use this identifier with third parties
	 */
	@Column(name = "third_party_id")
	private String thirdPartyId;
	/**
	 * The person's current timezone offset from UTC
	 */
	private Float timezone;
	/**
	 * Updated time
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time")
	private Date updatedTime;
	/**
	 * Indicates whether the account has been verified. This is distinct from
	 * the is_verified field. Someone is considered verified if they take any of
	 * the following actions: Register for mobile Confirm their account via SMS
	 * Enter a valid credit card
	 */
	private Boolean verified;
	/**
	 * Can the viewer send a gift to this person?
	 */
	@Column(name = "viewer_can_send_gift")
	private boolean viewerCanSendGift;
	/**
	 * The person's website
	 */
	private String website;
	/**
	 * Details of a person`s work experience
	 */
	@ManyToMany
	@Column(name = "work")
	@JoinTable(name = "J_USER_WORK", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "work_entry_id"))
	private List<WorkEntry> work;
	/**
	 * Video upload limits
	 */
	@Embedded
	@Column(name = "video_upload_limits")
	private VideoUploadLimits videoUploadLimits;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public CoverPhoto getCover() {
		return cover;
	}

	public void setCover(CoverPhoto cover) {
		this.cover = cover;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<EducationExperience> getEducation() {
		return education;
	}

	public void setEducation(List<EducationExperience> education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reference> getFavoriteAthletes() {
		return favoriteAthletes;
	}

	public void setFavoriteAthletes(List<Reference> favoriteAthletes) {
		this.favoriteAthletes = favoriteAthletes;
	}

	public List<Reference> getFavoriteTeams() {
		return favoriteTeams;
	}

	public void setFavoriteTeams(List<Reference> favoriteTeams) {
		this.favoriteTeams = favoriteTeams;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Reference getHometown() {
		return hometown;
	}

	public void setHometown(Reference hometown) {
		this.hometown = hometown;
	}

	public List<Reference> getInspirationalPeople() {
		return inspirationalPeople;
	}

	public void setInspirationalPeople(List<Reference> inspirationalPeople) {
		this.inspirationalPeople = inspirationalPeople;
	}

	public boolean isInstalled() {
		return installed;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public List<String> getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(List<String> interestedIn) {
		this.interestedIn = interestedIn;
	}

	public boolean isIdentityVerified() {
		return isIdentityVerified;
	}

	public void setIdentityVerified(boolean isIdentityVerified) {
		this.isIdentityVerified = isIdentityVerified;
	}

	public List<Reference> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Reference> languages) {
		this.languages = languages;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Reference getLocation() {
		return location;
	}

	public void setLocation(Reference location) {
		this.location = location;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public List<String> getMeetingFor() {
		return meetingFor;
	}

	public void setMeetingFor(List<String> meetingFor) {
		this.meetingFor = meetingFor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFormat() {
		return nameFormat;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}

	public PaymentPricePoints getPaymentPricePoints() {
		return paymentPricePoints;
	}

	public void setPaymentPricePoints(PaymentPricePoints paymentPricePoints) {
		this.paymentPricePoints = paymentPricePoints;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getQuotes() {
		return quotes;
	}

	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public SecuritySettings getSecuritySettings() {
		return securitySettings;
	}

	public void setSecuritySettings(SecuritySettings securitySettings) {
		this.securitySettings = securitySettings;
	}

	public Reference getSignificantOther() {
		return significantOther;
	}

	public void setSignificantOther(Reference significantOther) {
		this.significantOther = significantOther;
	}

	public List<Experience> getSports() {
		return sports;
	}

	public void setSports(List<Experience> sports) {
		this.sports = sports;
	}

	public int getTestGroup() {
		return testGroup;
	}

	public void setTestGroup(int testGroup) {
		this.testGroup = testGroup;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public Float getTimezone() {
		return timezone;
	}

	public void setTimezone(Float timezone) {
		this.timezone = timezone;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public boolean isViewerCanSendGift() {
		return viewerCanSendGift;
	}

	public void setViewerCanSendGift(boolean viewerCanSendGift) {
		this.viewerCanSendGift = viewerCanSendGift;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<WorkEntry> getWork() {
		return work;
	}

	public void setWork(List<WorkEntry> work) {
		this.work = work;
	}

	public VideoUploadLimits getVideoUploadLimits() {
		return videoUploadLimits;
	}

	public void setVideoUploadLimits(VideoUploadLimits videoUploadLimits) {
		this.videoUploadLimits = videoUploadLimits;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
