package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.social.facebook.api.Post;

@Embeddable
public class Privacy implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Text that describes the privacy settings, as they would appear on
	 * Facebook.
	 */
	@Column(name = "privacy_description")
	private String description;
	/**
	 * The actual privacy setting.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "privacy_value")
	private Post.PrivacyType value;
	/**
	 * If value is CUSTOM, this indicates which group of friends can see the
	 * post.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "privacy_friends")
	private Post.FriendsPrivacyType friends;
	@Column(name = "privacy_networks")
	private String networks;
	/**
	 * If value is CUSTOM, this is a comma-separated ID list of users and
	 * friendlists (if any) that can see the post.
	 */
	@Column(name = "privacy_allow")
	private String allow;
	/**
	 * If value is CUSTOM, this is a comma-separated ID list of users and
	 * friendlists (if any) that cannot see the post.
	 */
	@Column(name = "privacy_deny")
	private String deny;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Post.PrivacyType getValue() {
		return value;
	}

	public void setValue(Post.PrivacyType value) {
		this.value = value;
	}

	public Post.FriendsPrivacyType getFriends() {
		return friends;
	}

	public void setFriends(Post.FriendsPrivacyType friends) {
		this.friends = friends;
	}

	public String getNetworks() {
		return networks;
	}

	public void setNetworks(String networks) {
		this.networks = networks;
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getDeny() {
		return deny;
	}

	public void setDeny(String deny) {
		this.deny = deny;
	}

}
