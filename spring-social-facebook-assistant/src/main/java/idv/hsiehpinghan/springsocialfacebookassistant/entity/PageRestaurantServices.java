package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PageRestaurantServices implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Whether the restaurant has catering service
	 */
	@Column(name = "page_restaurant_services_catering")
	private boolean catering;
	/**
	 * Whether the restaurant has delivery service
	 */
	@Column(name = "page_restaurant_services_delivery")
	private boolean delivery;
	/**
	 * Whether the restaurant is group-friendly
	 */
	@Column(name = "page_restaurant_services_groups")
	private boolean groups;
	/**
	 * Whether the restaurant is kids-friendly
	 */
	@Column(name = "page_restaurant_services_kids")
	private boolean kids;
	/**
	 * Whether the restaurant has outdoor seating
	 */
	@Column(name = "page_restaurant_services_outdoor")
	private boolean outdoor;
	/**
	 * Whether the restaurant takes reservations
	 */
	@Column(name = "page_restaurant_services_reserve")
	private boolean reserve;
	/**
	 * Whether the restaurant has takeout service
	 */
	@Column(name = "page_restaurant_services_takeout")
	private boolean takeout;
	/**
	 * Whether the restaurant has waiters
	 */
	@Column(name = "page_restaurant_services_waiter")
	private boolean waiter;
	/**
	 * Whether the restaurant welcomes walkins
	 */
	@Column(name = "page_restaurant_services_walkins")
	private boolean walkins;

	public boolean isCatering() {
		return catering;
	}

	public void setCatering(boolean catering) {
		this.catering = catering;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public boolean isGroups() {
		return groups;
	}

	public void setGroups(boolean groups) {
		this.groups = groups;
	}

	public boolean isKids() {
		return kids;
	}

	public void setKids(boolean kids) {
		this.kids = kids;
	}

	public boolean isOutdoor() {
		return outdoor;
	}

	public void setOutdoor(boolean outdoor) {
		this.outdoor = outdoor;
	}

	public boolean isReserve() {
		return reserve;
	}

	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}

	public boolean isTakeout() {
		return takeout;
	}

	public void setTakeout(boolean takeout) {
		this.takeout = takeout;
	}

	public boolean isWaiter() {
		return waiter;
	}

	public void setWaiter(boolean waiter) {
		this.waiter = waiter;
	}

	public boolean isWalkins() {
		return walkins;
	}

	public void setWalkins(boolean walkins) {
		this.walkins = walkins;
	}

}
