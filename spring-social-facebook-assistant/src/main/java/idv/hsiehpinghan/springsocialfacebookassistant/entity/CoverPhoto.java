package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "COVER_PHOTO")
public class CoverPhoto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The ID of the cover photo
	 */
	@Id
	private String id;
	/**
	 * When non-zero, the cover image overflows horizontally. The value
	 * indicates the offset percentage of the total image width from the left
	 * [0-100]
	 */
	@Column(name = "offset_x")
	private int offsetX;
	/**
	 * When non-zero, the cover photo overflows vertically. The value indicates
	 * the offset percentage of the total image height from the top [0-100]
	 */
	@Column(name = "offset_y")
	private int offsetY;
	/**
	 * Direct URL for the person's cover photo image
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String source;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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
		CoverPhoto other = (CoverPhoto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
