package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The comment ID
	 */
	@Id
	private String id;
	/**
	 * Link or photo attached to the comment
	 */
	@ManyToOne
	@JoinColumn(name = "attachment")
	private StoryAttachment attachment;
	/**
	 * Whether the viewer can reply to this comment
	 */
	@Column(name = "can_comment")
	private boolean canComment;
	/**
	 * Whether the viewer can remove this comment
	 */
	@Column(name = "can_remove")
	private boolean canRemove;
	/**
	 * Number of replies to this comment
	 */
	@Column(name = "comment_count")
	private Integer commentCount;
	/**
	 * The time this comment was made
	 */
	@Column(name = "created_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	/**
	 * The person that made this comment
	 */
	@ManyToOne
	@JoinColumn(name = "[from]")
	private Reference from;
	/**
	 * Number of times this comment was liked
	 */
	@Column(name = "like_count")
	private Integer likeCount;
	/**
	 * The comment text
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String message;
	/**
	 * An array of Profiles tagged in message.
	 */
	@ElementCollection
	private List<MessageTag> messageTags;
	/**
	 * For comment replies, this the comment that this is a reply to
	 */
	@ManyToOne
	@JoinColumn(name = "parent")
	private Comment parent;
	/**
	 * Whether the viewer has liked this comment
	 */
	@Column(name = "user_likes")
	private boolean userLikes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StoryAttachment getAttachment() {
		return attachment;
	}

	public void setAttachment(StoryAttachment attachment) {
		this.attachment = attachment;
	}

	public boolean isCanComment() {
		return canComment;
	}

	public void setCanComment(boolean canComment) {
		this.canComment = canComment;
	}

	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Reference getFrom() {
		return from;
	}

	public void setFrom(Reference from) {
		this.from = from;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MessageTag> getMessageTags() {
		return messageTags;
	}

	public void setMessageTags(List<MessageTag> messageTags) {
		this.messageTags = messageTags;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public boolean isUserLikes() {
		return userLikes;
	}

	public void setUserLikes(boolean userLikes) {
		this.userLikes = userLikes;
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
