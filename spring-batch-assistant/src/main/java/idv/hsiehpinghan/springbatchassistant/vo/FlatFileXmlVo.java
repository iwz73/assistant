package idv.hsiehpinghan.springbatchassistant.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlatFileXmlVo {
	@XmlAttribute(name = "attributeValue")
	private String attributeValue;
	// @XmlElement(name = "longValue")
	private Long longValue;
	// @XmlElement(name = "stringValue")
	private String stringValue;
	@XmlElement(name = "listValue")
	private SubListFlatFileXmlVo[] SubListFlatFileXmlVos;

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public SubListFlatFileXmlVo[] getSubListFlatFileXmlVos() {
		return SubListFlatFileXmlVos;
	}

	public void setSubListFlatFileXmlVos(SubListFlatFileXmlVo[] subListFlatFileXmlVos) {
		SubListFlatFileXmlVos = subListFlatFileXmlVos;
	}

	public static class SubListFlatFileXmlVo {
		private Float floatValue;

		public Float getFloatValue() {
			return floatValue;
		}

		public void setFloatValue(Float floatValue) {
			this.floatValue = floatValue;
		}

	}
}
