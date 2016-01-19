package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessageTag implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID of the profile that was tagged.
	 */
	@Column(name = "message_tage_id")
	private String id;
	/**
	 * The text used in the tag.
	 */
	@Column(name = "message_tage_name")
	private String name;
	/**
	 * Indicates which type of profile is tagged.
	 */
	@Column(name = "message_tage_type")
	private String type;
	/**
	 * Where the first character of the tagged text is in the message, measured
	 * in unicode code points.
	 */
	@Column(name = "message_tage_offset")
	private Integer offset;
	/**
	 * How many unicode code points this tag consists of, after the offset.
	 */
	@Column(name = "message_tage_length")
	private Integer length;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
