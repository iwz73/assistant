package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PageParking implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Whether lot parking is available
	 */
	@Column(name = "page_parking_lot")
	private boolean lot;
	/**
	 * Whether street parking is available
	 */
	@Column(name = "page_parking_street")
	private boolean street;
	/**
	 * Whether valet parking is available
	 */
	@Column(name = "page_parking_valet")
	private boolean valet;

	public boolean isLot() {
		return lot;
	}

	public void setLot(boolean lot) {
		this.lot = lot;
	}

	public boolean isStreet() {
		return street;
	}

	public void setStreet(boolean street) {
		this.street = street;
	}

	public boolean isValet() {
		return valet;
	}

	public void setValet(boolean valet) {
		this.valet = valet;
	}

}
