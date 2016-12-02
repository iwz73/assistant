package idv.hsiehpinghan.springdatamongodbassistant.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import idv.hsiehpinghan.springdatamongodbassistant.enumeration.Enumeration;

@Document
public class BasicDocument {
	@Id
	private ObjectId idValue;
	private double doubleValue;
	private String stringValue;
	private List<String> arrayValue;
	private byte[] binDataValue;
	private ObjectId objectIdValue;
	private boolean boolValue;
	private Date dateValue;
	private Object nullValue;
	private int intValue;
	private Long longValue;
	private Enumeration enumerationValue;
	private SubDocument subDocument;

	public BasicDocument() {
	}

	public BasicDocument(ObjectId idValue, double doubleValue, String stringValue, List<String> arrayValue,
			byte[] binDataValue, ObjectId objectIdValue, boolean boolValue, Date dateValue, Object nullValue,
			int intValue, Long longValue, Enumeration enumerationValue, String string_0, String string_1,
			String string_2) {
		super();
		this.idValue = idValue;
		this.doubleValue = doubleValue;
		this.stringValue = stringValue;
		this.arrayValue = arrayValue;
		this.binDataValue = binDataValue;
		this.objectIdValue = objectIdValue;
		this.boolValue = boolValue;
		this.dateValue = dateValue;
		this.nullValue = nullValue;
		this.intValue = intValue;
		this.longValue = longValue;
		this.enumerationValue = enumerationValue;
		this.subDocument = new SubDocument(string_0, string_1, string_2);
	}

	public ObjectId getIdValue() {
		return idValue;
	}

	public void setIdValue(ObjectId idValue) {
		this.idValue = idValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public List<String> getArrayValue() {
		return arrayValue;
	}

	public void setArrayValue(List<String> arrayValue) {
		this.arrayValue = arrayValue;
	}

	public byte[] getBinDataValue() {
		return binDataValue;
	}

	public void setBinDataValue(byte[] binDataValue) {
		this.binDataValue = binDataValue;
	}

	public ObjectId getObjectIdValue() {
		return objectIdValue;
	}

	public void setObjectIdValue(ObjectId objectIdValue) {
		this.objectIdValue = objectIdValue;
	}

	public boolean isBoolValue() {
		return boolValue;
	}

	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Object getNullValue() {
		return nullValue;
	}

	public void setNullValue(Object nullValue) {
		this.nullValue = nullValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public SubDocument getSubDocument() {
		return subDocument;
	}

	public void setSubDocument(SubDocument subDocument) {
		this.subDocument = subDocument;
	}

	public Enumeration getEnumerationValue() {
		return enumerationValue;
	}

	public void setEnumerationValue(Enumeration enumerationValue) {
		this.enumerationValue = enumerationValue;
	}

	@Override
	public String toString() {
		return "BasicDocument [idValue=" + idValue + ", doubleValue=" + doubleValue + ", stringValue=" + stringValue
				+ ", arrayValue=" + arrayValue + ", binDataValue=" + Arrays.toString(binDataValue) + ", objectIdValue="
				+ objectIdValue + ", boolValue=" + boolValue + ", dateValue=" + dateValue + ", nullValue=" + nullValue
				+ ", intValue=" + intValue + ", longValue=" + longValue + ", enumerationValue=" + enumerationValue
				+ ", subDocument=" + subDocument + "]";
	}

	public static class SubDocument {
		private String string_0;
		private String string_1;
		private String string_2;

		public SubDocument(String string_0, String string_1, String string_2) {
			super();
			this.string_0 = string_0;
			this.string_1 = string_1;
			this.string_2 = string_2;
		}

		public String getString_0() {
			return string_0;
		}

		public void setString_0(String string_0) {
			this.string_0 = string_0;
		}

		public String getString_1() {
			return string_1;
		}

		public void setString_1(String string_1) {
			this.string_1 = string_1;
		}

		public String getString_2() {
			return string_2;
		}

		public void setString_2(String string_2) {
			this.string_2 = string_2;
		}

		@Override
		public String toString() {
			return "SubDocument [string_0=" + string_0 + ", string_1=" + string_1 + ", string_2=" + string_2 + "]";
		}

	}
}