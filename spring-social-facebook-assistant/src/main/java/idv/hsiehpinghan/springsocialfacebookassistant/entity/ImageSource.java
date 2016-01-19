package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

@Embeddable
public class ImageSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name = "image_source_source")
	@Type(type = "org.hibernate.type.TextType")
	private String source;
	@Column(name = "image_source_height")
	private int height;
	@Column(name = "image_source_width")
	private int width;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
