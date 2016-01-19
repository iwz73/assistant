package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "device_hardware")
	private String hardware;
	@Column(name = "device_os")
	private String os;

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

}
