package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PaymentPricePoint implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Credits
	 */
	private float credits;
	/**
	 * Local currency
	 */
	@Column(name = "local_currency")
	private String localCurrency;
	/**
	 * User price
	 */
	@Column(name = "user_price")
	private String userPrice;

	public float getCredits() {
		return credits;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}

	public String getLocalCurrency() {
		return localCurrency;
	}

	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}

	public String getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(String userPrice) {
		this.userPrice = userPrice;
	}

}
