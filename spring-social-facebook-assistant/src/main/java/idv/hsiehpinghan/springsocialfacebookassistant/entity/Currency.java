package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "currency_currency_offset")
	private int currencyOffset;
	@Column(name = "currency_user_currency")
	private String userCurrency;
	@Column(name = "currency_usd_exchange")
	private float usdExchange;
	@Column(name = "currency_usd_exchange_inverse")
	private float usdExchangeInverse;

	public int getCurrencyOffset() {
		return currencyOffset;
	}

	public void setCurrencyOffset(int currencyOffset) {
		this.currencyOffset = currencyOffset;
	}

	public String getUserCurrency() {
		return userCurrency;
	}

	public void setUserCurrency(String userCurrency) {
		this.userCurrency = userCurrency;
	}

	public float getUsdExchange() {
		return usdExchange;
	}

	public void setUsdExchange(float usdExchange) {
		this.usdExchange = usdExchange;
	}

	public float getUsdExchangeInverse() {
		return usdExchangeInverse;
	}

	public void setUsdExchangeInverse(float usdExchangeInverse) {
		this.usdExchangeInverse = usdExchangeInverse;
	}

}
