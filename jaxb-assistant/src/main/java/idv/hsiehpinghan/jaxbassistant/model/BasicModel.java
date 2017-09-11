package idv.hsiehpinghan.jaxbassistant.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicModel {
	@XmlAttribute
	private String stringXmlAttribute;
	@XmlAttribute
	private int intXmlAttribute;
	@XmlElement
	private String stringXmlElement;
	@XmlElement
	private int intXmlElement;

	public BasicModel() {
		super();
	}

	public BasicModel(String stringXmlAttribute, int intXmlAttribute, String stringXmlElement, int intXmlElement) {
		super();
		this.stringXmlAttribute = stringXmlAttribute;
		this.intXmlAttribute = intXmlAttribute;
		this.stringXmlElement = stringXmlElement;
		this.intXmlElement = intXmlElement;
	}

	public String getStringXmlAttribute() {
		return stringXmlAttribute;
	}

	public void setStringXmlAttribute(String stringXmlAttribute) {
		this.stringXmlAttribute = stringXmlAttribute;
	}

	public int getIntXmlAttribute() {
		return intXmlAttribute;
	}

	public void setIntXmlAttribute(int intXmlAttribute) {
		this.intXmlAttribute = intXmlAttribute;
	}

	public String getStringXmlElement() {
		return stringXmlElement;
	}

	public void setStringXmlElement(String stringXmlElement) {
		this.stringXmlElement = stringXmlElement;
	}

	public int getIntXmlElement() {
		return intXmlElement;
	}

	public void setIntXmlElement(int intXmlElement) {
		this.intXmlElement = intXmlElement;
	}

}
