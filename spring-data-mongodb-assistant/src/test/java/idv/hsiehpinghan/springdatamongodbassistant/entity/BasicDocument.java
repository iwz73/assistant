package idv.hsiehpinghan.springdatamongodbassistant.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

	public BasicDocument(ObjectId idValue, double doubleValue, String stringValue, List<String> arrayValue,
			byte[] binDataValue, ObjectId objectIdValue, boolean boolValue, Date dateValue, Object nullValue,
			int intValue, Long longValue) {
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

	public boolean getBoolValue() {
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

	@Override
	public String toString() {
		return "BasicDocument [idValue=" + idValue + ", doubleValue=" + doubleValue + ", stringValue=" + stringValue
				+ ", arrayValue=" + arrayValue + ", binDataValue=" + Arrays.toString(binDataValue) + ", objectIdValue="
				+ objectIdValue + ", boolValue=" + boolValue + ", dateValue=" + dateValue + ", nullValue=" + nullValue
				+ ", intValue=" + intValue + ", longValue=" + longValue + "]";
	}

}