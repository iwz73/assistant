package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_PRICEPOINTS")
public class PaymentPricePoints implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	/**
	 * Mobile payment pricepoints
	 */
	@ElementCollection
	private List<PaymentPricePoint> mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PaymentPricePoint> getMobile() {
		return mobile;
	}

	public void setMobile(List<PaymentPricePoint> mobile) {
		this.mobile = mobile;
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
		PaymentPricePoints other = (PaymentPricePoints) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
