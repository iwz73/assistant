package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "STORY_ATTACHMENT")
public class StoryAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	@ElementCollection
	@Column(name = "description_tags")
	private List<String> descriptionTags;
	private String type;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String url;
	@Embedded
	private StoryAttachmentTarget target;
	@ManyToOne
	@JoinColumn(name = "media")
	private StoryAttachmentMedia media;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getDescriptionTags() {
		return descriptionTags;
	}

	public void setDescriptionTags(List<String> descriptionTags) {
		this.descriptionTags = descriptionTags;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public StoryAttachmentTarget getTarget() {
		return target;
	}

	public void setTarget(StoryAttachmentTarget target) {
		this.target = target;
	}

	public StoryAttachmentMedia getMedia() {
		return media;
	}

	public void setMedia(StoryAttachmentMedia media) {
		this.media = media;
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
		StoryAttachment other = (StoryAttachment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
