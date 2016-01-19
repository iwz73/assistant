package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VoipInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Does the viewer have permission to call?
	 */
	@Column(name = "voip_info_has_permission")
	private boolean has_permission;
	/**
	 * Does this user have a pushable mobile app install?
	 */
	@Column(name = "voip_info_has_mobile_app")
	private boolean hasMobileApp;
	/**
	 * Does this user have an unmuted push token?
	 */
	@Column(name = "voip_info_is_pushable")
	private boolean isPushable;
	/**
	 * Is this user currently callable via mobile?
	 */
	@Column(name = "voip_info_is_callable")
	private boolean isCallable;
	/**
	 * Is this user currently callable via dekstop?
	 */
	@Column(name = "voip_info_is_callable_webrtc")
	private boolean isCallableWebRTC;
	/**
	 * Reason code if not callable
	 */
	@Column(name = "voip_info_reason_code")
	private int reasonCode;
	/**
	 * Reason description if not callable
	 */
	@Column(name = "voip_info_reason_description")
	private String reasonDescription;

	public boolean isHas_permission() {
		return has_permission;
	}

	public void setHas_permission(boolean has_permission) {
		this.has_permission = has_permission;
	}

	public boolean isHasMobileApp() {
		return hasMobileApp;
	}

	public void setHasMobileApp(boolean hasMobileApp) {
		this.hasMobileApp = hasMobileApp;
	}

	public boolean isPushable() {
		return isPushable;
	}

	public void setPushable(boolean isPushable) {
		this.isPushable = isPushable;
	}

	public boolean isCallable() {
		return isCallable;
	}

	public void setCallable(boolean isCallable) {
		this.isCallable = isCallable;
	}

	public boolean isCallableWebRTC() {
		return isCallableWebRTC;
	}

	public void setCallableWebRTC(boolean isCallableWebRTC) {
		this.isCallableWebRTC = isCallableWebRTC;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

}
