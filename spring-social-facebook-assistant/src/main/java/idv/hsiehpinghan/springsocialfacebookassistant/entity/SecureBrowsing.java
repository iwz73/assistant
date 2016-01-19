package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SecureBrowsing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "Secure_Browsing_enabled")
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
