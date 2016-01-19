package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
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
import javax.persistence.MapKeyColumn;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.social.facebook.api.Page.PriceRange;

@Entity
@Table(name = "PAGE")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Page ID. No access token is required to access this field
	 */
	@Id
	private String id;
	/**
	 * Information about the Page
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String about;
	/**
	 * The access token you can use to act as the Page. Only visible to Page
	 * Admins
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "access_token")
	private String accessToken;
	/**
	 * Affiliation of this person. Applicable to Pages representing people
	 */
	private String affiliation;
	/**
	 * App ID for app-owned Pages and app Pages
	 */
	private String appId;
	/**
	 * Artists the band likes. Applicable to Bands
	 */
	@Column(name = "artists_we_like")
	private String artistsWeLike;
	/**
	 * Dress code of the business. Applicable to Restaurants or Nightlife. Can
	 * be one of Casual, Dressy or Unspecified
	 */
	private String attire;
	/**
	 * The awards information of the film. Applicable to Films
	 */
	private String awards;
	/**
	 * band_interests
	 */
	@Column(name = "band_interests")
	private String bandInterests;
	/**
	 * band_members
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "band_members")
	private String bandMembers;
	/**
	 * The best available Page on Facebook for the concept represented by this
	 * Page. The best available Page takes into account authenticity and the
	 * number of likes
	 */
	@Column(name = "best_page")
	private Page bestPage;
	/**
	 * Biography of the band. Applicable to Bands
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String bio;
	/**
	 * Birthday of this person. Applicable to Pages representing people
	 */
	private String birthday;
	/**
	 * Booking agent of the band. Applicable to Bands
	 */
	@Column(name = "booking_agent")
	private String bookingAgent;
	/**
	 * Year vehicle was built. Applicable to Vehicles
	 */
	private String built;
	/**
	 * The Business associated with this Page. Visible only with a page access
	 * token or a user access token that has admin rights on the page
	 */
	@ManyToOne
	@JoinColumn(name = "business")
	private Reference business;
	/**
	 * Whether the current session user can post on this Page
	 */
	@Column(name = "can_post")
	private boolean canPost;
	/**
	 * The Page's category. e.g. Product/Service, Computers/Technology
	 */
	private String category;
	/**
	 * The Page's sub-categories
	 */
	@ManyToMany
	@Column(name = "category_list")
	@JoinTable(name = "J_PAGE_CATEGORY_LIST", joinColumns = @JoinColumn(name = "page_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> categoryList;
	/**
	 * Number of checkins at a place represented by a Page
	 */
	private int checkins;
	/**
	 * Update the company_overview field
	 */
	@Lob
	@Column(name = "company_overview")
	@Type(type = "org.hibernate.type.TextType")
	private String companyOverview;
	/**
	 * Update the contact_address field
	 */
	@ManyToOne
	@JoinColumn(name = "contact_address")
	private MailingAddress contactAddress;
	/**
	 * If this is a Page in a Global Pages hierarchy, the number of people who
	 * are being directed to this Page.
	 */
	@Column(name = "country_page_likes")
	private int countryPageLikes;
	/**
	 * Information about the page's cover photo
	 */
	@ManyToOne
	@JoinColumn(name = "cover")
	private CoverPhoto cover;
	/**
	 * Culinary team of the business. Applicable to Restaurants or Nightlife
	 */
	private String culinaryTeam;
	/**
	 * Current location of the Page
	 */
	@Lob
	@Column(name = "current_location")
	@Type(type = "org.hibernate.type.TextType")
	private String currentLocation;
	/**
	 * The description of the Page
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	/**
	 * The description of the Page in raw HTML
	 */
	@Lob
	@Column(name = "description_html")
	@Type(type = "org.hibernate.type.TextType")
	private String descriptionHtml;
	/**
	 * The director of the film. Applicable to Films
	 */
	@Column(name = "directed_by")
	private String directedBy;
	/**
	 * The emails listed in the About section of a Page
	 */
	@ElementCollection
	private List<String> emails;
	/**
	 * The social sentence and like count information for this Page. This is the
	 * same info used for the like button
	 */
	@Embedded
	private Engagement engagement;
	/**
	 * Features of the vehicle. Applicable to Vehicles
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String features;
	/**
	 * The restaurant's food styles. Applicable to Restaurants
	 */
	@Lob
	@Column(name = "food_styles")
	@Type(type = "org.hibernate.type.TextType")
	private String foodStyles;
	/**
	 * When the company was founded. Applicable to Pages in the Company category
	 */
	private String founded;
	/**
	 * General information provided by the Page
	 */
	@Lob
	@Column(name = "general_info")
	@Type(type = "org.hibernate.type.TextType")
	private String generalInfo;
	/**
	 * General manager of the business. Applicable to Restaurants or Nightlife
	 */
	@Column(name = "general_manager")
	private String generalManager;
	/**
	 * The genre of the film. Applicable to Films
	 */
	private String genre;
	/**
	 * The name of the Page with country codes appended for Global Brand Pages.
	 * Only visible to the Page admin
	 */
	@Column(name = "global_brand_page_name")
	private String globalBrandPageName;
	// private Reference globalBrandParentPage;
	/**
	 * This brand's global Root ID
	 */
	@Column(name = "global_brand_root_id")
	private String globalBrandRootId;
	/**
	 * Indicates whether this Page has added the app making the query in a Page
	 * tab
	 */
	@Column(name = "has_added_app")
	private boolean hasAddedApp;
	/**
	 * Hometown of the band. Applicable to Bands
	 */
	private String hometown;
	/**
	 * Indicates a single range of opening hours for a day. Each day can have 2
	 * different hours ranges. The keys in the map are in the form of
	 * {day}_{number}_{status}. {day} should be the first 3 characters of the
	 * day of the week, {number} should be either 1 or 2 to allow for the two
	 * different hours ranges per day. {status} should be either open or close
	 * to delineate the start or end of a time range. An example would be
	 * mon_1_open with value 17:00 and mon_1_close with value 21:15 which would
	 * represent a single opening range of 5pm to 9:15pm on Mondays
	 */
	@ElementCollection
	@CollectionTable(name = "PAGE_HOURS")
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	private Map<String, String> hours;
	/**
	 * Influences on the band. Applicable to Bands
	 */
	private String influences;
	/**
	 * Indicates whether the Page is a community Page
	 */
	private boolean isCommunityPage;
	/**
	 * Whether the business corresponding to this Page is permanently closed
	 */
	private boolean isPermanentlyClosed;
	/**
	 * Indicates whether the Page is published and visible to non-admins
	 */
	private boolean isPublished;
	/**
	 * Indicates whether the Page is unclaimed
	 */
	private boolean isUnclaimed;
	/**
	 * Pages with a large number of followers can be manually verified by
	 * Facebook as having an authentic identity. This field indicates whether
	 * the page is verified by this process
	 */
	private boolean isVerified;
	/**
	 * The number of users who like the Page. For Global Brand Pages this is the
	 * count for all pages across the brand.
	 */
	private int likes;
	/**
	 * The Page's Facebook URL
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String link;
	@Embedded
	private Location location;
	/**
	 * Members of this org. Applicable to Pages representing Team Orgs
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String members;
	/**
	 * Update the mission field
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String mission;
	/**
	 * MPG of the vehicle. Applicable to Vehicles
	 */
	private String mpg;
	/**
	 * The name of the Page
	 */
	private String name;
	/**
	 * The TV network for the TV show. Applicable to TV Shows
	 */
	private String network;
	/**
	 * The number of people who have liked the Page, since the last login. Only
	 * visible to a page admin
	 */
	@Column(name = "new_like_count")
	private int newLikeCount;
	/**
	 * Offer eligibility status. Only visible to a page admin
	 */
	@Column(name = "offer_eligible")
	private boolean offerEligible;
	/**
	 * Parent Page for this Page
	 */
	@ManyToOne
	@JoinColumn(name = "parent_page")
	private Reference parentPage;
	/**
	 * Parking information. Applicable to Businesses and Places
	 */
	@Embedded
	private PageParking parking;
	/**
	 * Payment options accepted by the business. Applicable to Restaurants or
	 * Nightlife
	 */
	@Embedded
	@Column(name = "payment_options")
	private PagePaymentOptions paymentOptions;
	/**
	 * Personal information. Applicable to Pages representing People
	 */
	@Lob
	@Column(name = "personal_info")
	@Type(type = "org.hibernate.type.TextType")
	private String personalInfo;
	/**
	 * Personal interests. Applicable to Pages representing People
	 */
	@Column(name = "personal_interests")
	private String personalInterests;
	/**
	 * Pharmacy safety information. Applicable to Pharmaceutical companies
	 */
	@Column(name = "pharma_safety_info")
	private String pharmaSafetyInfo;
	/**
	 * Phone number provided by a Page
	 */
	private String phone;
	/**
	 * The plot outline of the film. Applicable to Films
	 */
	@Column(name = "plot_outline")
	private String plotOutline;
	/**
	 * Press contact information of the band. Applicable to Bands
	 */
	@Column(name = "press_contact")
	private String pressContact;
	/**
	 * Price range of the business. Applicable to Restaurants or Nightlife. Can
	 * be one of $ (0-10), $$ (10-30), $$$ (30-50), $$$$ (50+) or Unspecified
	 */
	@Column(name = "price_range")
	@Enumerated(EnumType.STRING)
	private PriceRange priceRange;
	/**
	 * The productor of the film. Applicable to Films
	 */
	@Column(name = "produced_by")
	private String producedBy;
	/**
	 * The products of this company. Applicable to Companies
	 */
	private String products;
	/**
	 * Reason why a post isn't eligible for boosting. Only visible to Page
	 * Admins
	 */
	@Column(name = "promotion_eligible")
	private boolean promotionEligible;
	/**
	 * Reason, for which boosted posts are not eligible. Only visible to a page
	 * admin
	 */
	@Column(name = "promotion_ineligible_reason")
	private String promotionIneligibleReason;
	/**
	 * Public transit to the business. Applicable to Restaurants or Nightlife
	 */
	@Column(name = "public_transit")
	private String publicTransit;
	/**
	 * Record label of the band. Applicable to Bands
	 */
	@Column(name = "record_label")
	private String recordLabel;
	/**
	 * The film's release date. Applicable to Films
	 */
	@Column(name = "release_date")
	private String releaseDate;
	/**
	 * Services the restaurant provides. Applicable to Restaurants
	 */
	@Embedded
	@Column(name = "restaurant_services")
	private PageRestaurantServices restaurantServices;
	/**
	 * The restaurant's specialties. Applicable to Restaurants
	 */
	@Embedded
	@Column(name = "restaurant_specialties")
	private PageRestaurantSpecialties restaurantSpecialties;
	/**
	 * The air schedule of the TV show. Applicable to TV Shows
	 */
	private String schedule;
	/**
	 * The screenwriter of the film. Applicable to Films
	 */
	@Column(name = "screenplay_by")
	private String screenplayBy;
	/**
	 * The season information of the TV Show. Applicable to TV Shows
	 */
	private String season;
	/**
	 * The cast of the film. Applicable to Films
	 */
	private String starring;
	/**
	 * Unique store number for this location Page
	 */
	@Column(name = "store_number")
	private int storeNumber;
	/**
	 * The studio for the film production. Applicable to Films
	 */
	private String studio;
	/**
	 * The number of people talking about this Page
	 */
	private int talkingAboutCount;
	/**
	 * Unread message count for the Page. Only visible to a page admin
	 */
	@Column(name = "unread_message_count")
	private int unreadMessageCount;
	/**
	 * Number of unread notifications. Only visible to a page admin
	 */
	@Column(name = "unread_notif_count")
	private int unreadNotifCount;
	/**
	 * Unseen message count for the Page. Only visible to a page admin
	 */
	@Column(name = "unseen_message_count")
	private int unseenMessageCount;
	/**
	 * The alias of the Page. For example, for www.facebook.com/platform the
	 * username is 'platform'
	 */
	private String username;
	@Embedded
	@Column(name = "voip_info")
	private VoipInfo voipInfo;
	/**
	 * The URL of the Page's website
	 */
	@Lob
	@Column(name = "website")
	@Type(type = "org.hibernate.type.TextType")
	private String website;
	/**
	 * The number of visits to this Page's location. If the Page setting Show
	 * map, check-ins and star ratings on the Page (under Page Settings > Page
	 * Info > Address) is disabled, then this value will also be disabled
	 */
	@Column(name = "were_here_count")
	private int wereHereCount;
	/**
	 * The writer of the TV show. Applicable to TV Shows
	 */
	@Column(name = "written_by")
	private String writtenBy;

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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getArtistsWeLike() {
		return artistsWeLike;
	}

	public void setArtistsWeLike(String artistsWeLike) {
		this.artistsWeLike = artistsWeLike;
	}

	public String getAttire() {
		return attire;
	}

	public void setAttire(String attire) {
		this.attire = attire;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getBandInterests() {
		return bandInterests;
	}

	public void setBandInterests(String bandInterests) {
		this.bandInterests = bandInterests;
	}

	public String getBandMembers() {
		return bandMembers;
	}

	public void setBandMembers(String bandMembers) {
		this.bandMembers = bandMembers;
	}

	public Page getBestPage() {
		return bestPage;
	}

	public void setBestPage(Page bestPage) {
		this.bestPage = bestPage;
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

	public String getBookingAgent() {
		return bookingAgent;
	}

	public void setBookingAgent(String bookingAgent) {
		this.bookingAgent = bookingAgent;
	}

	public String getBuilt() {
		return built;
	}

	public void setBuilt(String built) {
		this.built = built;
	}

	public Reference getBusiness() {
		return business;
	}

	public void setBusiness(Reference business) {
		this.business = business;
	}

	public boolean isCanPost() {
		return canPost;
	}

	public void setCanPost(boolean canPost) {
		this.canPost = canPost;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Reference> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Reference> categoryList) {
		this.categoryList = categoryList;
	}

	public int getCheckins() {
		return checkins;
	}

	public void setCheckins(int checkins) {
		this.checkins = checkins;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public MailingAddress getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(MailingAddress contactAddress) {
		this.contactAddress = contactAddress;
	}

	public int getCountryPageLikes() {
		return countryPageLikes;
	}

	public void setCountryPageLikes(int countryPageLikes) {
		this.countryPageLikes = countryPageLikes;
	}

	public CoverPhoto getCover() {
		return cover;
	}

	public void setCover(CoverPhoto cover) {
		this.cover = cover;
	}

	public String getCulinaryTeam() {
		return culinaryTeam;
	}

	public void setCulinaryTeam(String culinaryTeam) {
		this.culinaryTeam = culinaryTeam;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	public void setDescriptionHtml(String descriptionHtml) {
		this.descriptionHtml = descriptionHtml;
	}

	public String getDirectedBy() {
		return directedBy;
	}

	public void setDirectedBy(String directedBy) {
		this.directedBy = directedBy;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Engagement getEngagement() {
		return engagement;
	}

	public void setEngagement(Engagement engagement) {
		this.engagement = engagement;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getFoodStyles() {
		return foodStyles;
	}

	public void setFoodStyles(String foodStyles) {
		this.foodStyles = foodStyles;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public String getGeneralInfo() {
		return generalInfo;
	}

	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
	}

	public String getGeneralManager() {
		return generalManager;
	}

	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGlobalBrandPageName() {
		return globalBrandPageName;
	}

	public void setGlobalBrandPageName(String globalBrandPageName) {
		this.globalBrandPageName = globalBrandPageName;
	}

	public String getGlobalBrandRootId() {
		return globalBrandRootId;
	}

	public void setGlobalBrandRootId(String globalBrandRootId) {
		this.globalBrandRootId = globalBrandRootId;
	}

	public boolean isHasAddedApp() {
		return hasAddedApp;
	}

	public void setHasAddedApp(boolean hasAddedApp) {
		this.hasAddedApp = hasAddedApp;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public Map<String, String> getHours() {
		return hours;
	}

	public void setHours(Map<String, String> hours) {
		this.hours = hours;
	}

	public String getInfluences() {
		return influences;
	}

	public void setInfluences(String influences) {
		this.influences = influences;
	}

	public boolean isCommunityPage() {
		return isCommunityPage;
	}

	public void setCommunityPage(boolean isCommunityPage) {
		this.isCommunityPage = isCommunityPage;
	}

	public boolean isPermanentlyClosed() {
		return isPermanentlyClosed;
	}

	public void setPermanentlyClosed(boolean isPermanentlyClosed) {
		this.isPermanentlyClosed = isPermanentlyClosed;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public boolean isUnclaimed() {
		return isUnclaimed;
	}

	public void setUnclaimed(boolean isUnclaimed) {
		this.isUnclaimed = isUnclaimed;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getMpg() {
		return mpg;
	}

	public void setMpg(String mpg) {
		this.mpg = mpg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public int getNewLikeCount() {
		return newLikeCount;
	}

	public void setNewLikeCount(int newLikeCount) {
		this.newLikeCount = newLikeCount;
	}

	public boolean isOfferEligible() {
		return offerEligible;
	}

	public void setOfferEligible(boolean offerEligible) {
		this.offerEligible = offerEligible;
	}

	public Reference getParentPage() {
		return parentPage;
	}

	public void setParentPage(Reference parentPage) {
		this.parentPage = parentPage;
	}

	public PageParking getParking() {
		return parking;
	}

	public void setParking(PageParking parking) {
		this.parking = parking;
	}

	public PagePaymentOptions getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(PagePaymentOptions paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public String getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getPersonalInterests() {
		return personalInterests;
	}

	public void setPersonalInterests(String personalInterests) {
		this.personalInterests = personalInterests;
	}

	public String getPharmaSafetyInfo() {
		return pharmaSafetyInfo;
	}

	public void setPharmaSafetyInfo(String pharmaSafetyInfo) {
		this.pharmaSafetyInfo = pharmaSafetyInfo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlotOutline() {
		return plotOutline;
	}

	public void setPlotOutline(String plotOutline) {
		this.plotOutline = plotOutline;
	}

	public String getPressContact() {
		return pressContact;
	}

	public void setPressContact(String pressContact) {
		this.pressContact = pressContact;
	}

	public PriceRange getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
	}

	public String getProducedBy() {
		return producedBy;
	}

	public void setProducedBy(String producedBy) {
		this.producedBy = producedBy;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public boolean isPromotionEligible() {
		return promotionEligible;
	}

	public void setPromotionEligible(boolean promotionEligible) {
		this.promotionEligible = promotionEligible;
	}

	public String getPromotionIneligibleReason() {
		return promotionIneligibleReason;
	}

	public void setPromotionIneligibleReason(String promotionIneligibleReason) {
		this.promotionIneligibleReason = promotionIneligibleReason;
	}

	public String getPublicTransit() {
		return publicTransit;
	}

	public void setPublicTransit(String publicTransit) {
		this.publicTransit = publicTransit;
	}

	public String getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public PageRestaurantServices getRestaurantServices() {
		return restaurantServices;
	}

	public void setRestaurantServices(PageRestaurantServices restaurantServices) {
		this.restaurantServices = restaurantServices;
	}

	public PageRestaurantSpecialties getRestaurantSpecialties() {
		return restaurantSpecialties;
	}

	public void setRestaurantSpecialties(
			PageRestaurantSpecialties restaurantSpecialties) {
		this.restaurantSpecialties = restaurantSpecialties;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getScreenplayBy() {
		return screenplayBy;
	}

	public void setScreenplayBy(String screenplayBy) {
		this.screenplayBy = screenplayBy;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public int getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public void setTalkingAboutCount(int talkingAboutCount) {
		this.talkingAboutCount = talkingAboutCount;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(int unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}

	public int getUnreadNotifCount() {
		return unreadNotifCount;
	}

	public void setUnreadNotifCount(int unreadNotifCount) {
		this.unreadNotifCount = unreadNotifCount;
	}

	public int getUnseenMessageCount() {
		return unseenMessageCount;
	}

	public void setUnseenMessageCount(int unseenMessageCount) {
		this.unseenMessageCount = unseenMessageCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public VoipInfo getVoipInfo() {
		return voipInfo;
	}

	public void setVoipInfo(VoipInfo voipInfo) {
		this.voipInfo = voipInfo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getWereHereCount() {
		return wereHereCount;
	}

	public void setWereHereCount(int wereHereCount) {
		this.wereHereCount = wereHereCount;
	}

	public String getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(String writtenBy) {
		this.writtenBy = writtenBy;
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
		Page other = (Page) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
