package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Engagement implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Number of people who like this
	 */
	@Column(name = "engagement_count")
	private int count;
	/**
	 * Abbreviated string representation of count
	 */
	@Column(name = "engagement_count_string")
	private String countString;
	/**
	 * Abbreviated string representation of count if the viewer likes the object
	 */
	@Column(name = "engagement_count_string_with_like")
	private String countStringWithLike;
	/**
	 * Abbreviated string representation of count if the viewer does not like
	 * the object
	 */
	@Column(name = "engagement_count_string_without_like")
	private String countStringWithoutLike;
	/**
	 * Text that the like button would currently display
	 */
	@Column(name = "engagement_social_sentence")
	private String socialSentence;
	/**
	 * Text that the like button would display if the viewer likes the object
	 */
	@Column(name = "engagement_social_sentence_with_like")
	private String socialSentenceWithLike;
	/**
	 * Text that the like button would display if the viewer does not like the
	 * object
	 */
	@Column(name = "engagement_social_sentence_without_like")
	private String socialSentenceWithoutLike;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCountString() {
		return countString;
	}

	public void setCountString(String countString) {
		this.countString = countString;
	}

	public String getCountStringWithLike() {
		return countStringWithLike;
	}

	public void setCountStringWithLike(String countStringWithLike) {
		this.countStringWithLike = countStringWithLike;
	}

	public String getCountStringWithoutLike() {
		return countStringWithoutLike;
	}

	public void setCountStringWithoutLike(String countStringWithoutLike) {
		this.countStringWithoutLike = countStringWithoutLike;
	}

	public String getSocialSentence() {
		return socialSentence;
	}

	public void setSocialSentence(String socialSentence) {
		this.socialSentence = socialSentence;
	}

	public String getSocialSentenceWithLike() {
		return socialSentenceWithLike;
	}

	public void setSocialSentenceWithLike(String socialSentenceWithLike) {
		this.socialSentenceWithLike = socialSentenceWithLike;
	}

	public String getSocialSentenceWithoutLike() {
		return socialSentenceWithoutLike;
	}

	public void setSocialSentenceWithoutLike(String socialSentenceWithoutLike) {
		this.socialSentenceWithoutLike = socialSentenceWithoutLike;
	}

}
