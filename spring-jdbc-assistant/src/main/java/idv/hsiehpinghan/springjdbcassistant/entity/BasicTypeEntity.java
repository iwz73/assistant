package idv.hsiehpinghan.springjdbcassistant.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasicTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	// primative
	private boolean primativeBoolean;
	private byte primativeByte;
	private double primativeDouble;
	private float primativeFloat;
	private int primativeInt;
	private long primativeLong;
	private short primativeShort;
	// object
	private String string;
	private BigDecimal bigDecimal;
	private java.sql.Date sqlDate;
	private java.sql.Time sqlTime;
	private java.sql.Timestamp sqlTimestamp;
	private char[] clobCharArray;
	private byte[] blobByteArray;
	// array
	private byte[] byteArray;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPrimativeBoolean() {
		return primativeBoolean;
	}

	public void setPrimativeBoolean(boolean primativeBoolean) {
		this.primativeBoolean = primativeBoolean;
	}

	public byte getPrimativeByte() {
		return primativeByte;
	}

	public void setPrimativeByte(byte primativeByte) {
		this.primativeByte = primativeByte;
	}

	public double getPrimativeDouble() {
		return primativeDouble;
	}

	public void setPrimativeDouble(double primativeDouble) {
		this.primativeDouble = primativeDouble;
	}

	public float getPrimativeFloat() {
		return primativeFloat;
	}

	public void setPrimativeFloat(float primativeFloat) {
		this.primativeFloat = primativeFloat;
	}

	public int getPrimativeInt() {
		return primativeInt;
	}

	public void setPrimativeInt(int primativeInt) {
		this.primativeInt = primativeInt;
	}

	public long getPrimativeLong() {
		return primativeLong;
	}

	public void setPrimativeLong(long primativeLong) {
		this.primativeLong = primativeLong;
	}

	public short getPrimativeShort() {
		return primativeShort;
	}

	public void setPrimativeShort(short primativeShort) {
		this.primativeShort = primativeShort;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}

	public java.sql.Time getSqlTime() {
		return sqlTime;
	}

	public void setSqlTime(java.sql.Time sqlTime) {
		this.sqlTime = sqlTime;
	}

	public java.sql.Timestamp getSqlTimestamp() {
		return sqlTimestamp;
	}

	public void setSqlTimestamp(java.sql.Timestamp sqlTimestamp) {
		this.sqlTimestamp = sqlTimestamp;
	}

	public char[] getClobCharArray() {
		return clobCharArray;
	}

	public void setClobCharArray(char[] clobCharArray) {
		this.clobCharArray = clobCharArray;
	}

	public byte[] getBlobByteArray() {
		return blobByteArray;
	}

	public void setBlobByteArray(byte[] blobByteArray) {
		this.blobByteArray = blobByteArray;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
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
		BasicTypeEntity other = (BasicTypeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
