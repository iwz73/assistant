package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.social.facebook.api.Group.Privacy;

@Entity
@Table(name = "GROUP")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The group ID
	 */
	@Id
	private String id;
	/**
	 * Information about the group's cover photo.
	 */
	@ManyToOne
	@JoinColumn(name = "cover")
	private CoverPhoto cover;
	/**
	 * A brief description of the group.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	/**
	 * The email address to upload content to the group. Only current members of
	 * the group can use this.
	 */
	private String email;
	/**
	 * The URL for the group's icon.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String icon;
	/**
	 * The group's website.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String link;
	/**
	 * The name of the group.
	 */
	private String name;
	/**
	 * The profile that created this group.
	 */
	@ManyToOne
	@JoinColumn(name = "owner")
	private Reference owner;
	/**
	 * The privacy setting of the group. Possible values: CLOSED OPEN SECRET
	 */
	@Enumerated(EnumType.STRING)
	private Privacy privacy;
	/**
	 * The last time the group was updated (this includes changes in the group's
	 * properties and changes in posts and comments if the session user can see
	 * them).
	 */
	@Column(name = "updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CoverPhoto getCover() {
		return cover;
	}

	public void setCover(CoverPhoto cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reference getOwner() {
		return owner;
	}

	public void setOwner(Reference owner) {
		this.owner = owner;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
