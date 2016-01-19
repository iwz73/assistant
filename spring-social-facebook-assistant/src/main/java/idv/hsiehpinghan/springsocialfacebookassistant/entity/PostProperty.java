package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

@Embeddable
public class PostProperty implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The property name.
	 */
	@Column(name = "post_property_name")
	private String name;
	/**
	 * The value of the property.
	 */
	@Column(name = "post_property_text")
	private String text;
	/**
	 * Any link associated with the property.
	 */
	@Lob
	@Column(name = "post_property_href")
	@Type(type = "org.hibernate.type.TextType")
	private String href;

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getHref() {
		return href;
	}

}
