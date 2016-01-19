package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PagePaymentOptions implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Whether the business accepts American Express as a payment option
	 */
	@Column(name = "page_payment_options_amex")
	private boolean amex;
	/**
	 * Whether the business accepts cash only as a payment option
	 */
	@Column(name = "page_payment_options_cash_Only")
	private boolean cashOnly;
	/**
	 * Whether the business accepts Discover as a payment option
	 */
	@Column(name = "page_payment_options_discover")
	private boolean discover;
	/**
	 * Whether the business accepts MasterCard as a payment option
	 */
	@Column(name = "page_payment_options_mastercard")
	private boolean mastercard;
	/**
	 * Whether the business accepts Visa as a payment option
	 */
	@Column(name = "page_payment_options_visa")
	private boolean visa;

	public boolean isAmex() {
		return amex;
	}

	public void setAmex(boolean amex) {
		this.amex = amex;
	}

	public boolean isCashOnly() {
		return cashOnly;
	}

	public void setCashOnly(boolean cashOnly) {
		this.cashOnly = cashOnly;
	}

	public boolean isDiscover() {
		return discover;
	}

	public void setDiscover(boolean discover) {
		this.discover = discover;
	}

	public boolean isMastercard() {
		return mastercard;
	}

	public void setMastercard(boolean mastercard) {
		this.mastercard = mastercard;
	}

	public boolean isVisa() {
		return visa;
	}

	public void setVisa(boolean visa) {
		this.visa = visa;
	}

}
