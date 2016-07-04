package idv.hsiehpinghan.jerseyassistant.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import idv.hsiehpinghan.jerseyassistant.enumeration.Enumeration;

public class JsonVo {
	private boolean primativeBoolean;
	private Boolean wrappedBoolean;
	private byte primativeByte;
	private Byte wrappedByte;
	private char primativeChar;
	private Character wrappedChar;
	private double primativeDouble;
	private Double wrappedDouble;
	private float primativeFloat;
	private Float wrappedFloat;
	private int primativeInt;
	private Integer wrappedInt;
	private long primativeLong;
	private Long wrappedLong;
	private short primativeShort;
	private Short wrappedShort;
	private String string;
	private BigInteger bigInteger;
	private BigDecimal bigDecimal;
	private Date date;
	private Enumeration enumeration;

	public JsonVo() {
	}

	public JsonVo(boolean primativeBoolean, Boolean wrappedBoolean, byte primativeByte, Byte wrappedByte,
			char primativeChar, Character wrappedChar, double primativeDouble, Double wrappedDouble,
			float primativeFloat, Float wrappedFloat, int primativeInt, Integer wrappedInt, long primativeLong,
			Long wrappedLong, short primativeShort, Short wrappedShort, String string, BigInteger bigInteger,
			BigDecimal bigDecimal, Date date, Enumeration enumeration) {
		super();
		this.primativeBoolean = primativeBoolean;
		this.wrappedBoolean = wrappedBoolean;
		this.primativeByte = primativeByte;
		this.wrappedByte = wrappedByte;
		this.primativeChar = primativeChar;
		this.wrappedChar = wrappedChar;
		this.primativeDouble = primativeDouble;
		this.wrappedDouble = wrappedDouble;
		this.primativeFloat = primativeFloat;
		this.wrappedFloat = wrappedFloat;
		this.primativeInt = primativeInt;
		this.wrappedInt = wrappedInt;
		this.primativeLong = primativeLong;
		this.wrappedLong = wrappedLong;
		this.primativeShort = primativeShort;
		this.wrappedShort = wrappedShort;
		this.string = string;
		this.bigInteger = bigInteger;
		this.bigDecimal = bigDecimal;
		this.date = date;
		this.enumeration = enumeration;
	}

	public boolean isPrimativeBoolean() {
		return primativeBoolean;
	}

	public void setPrimativeBoolean(boolean primativeBoolean) {
		this.primativeBoolean = primativeBoolean;
	}

	public Boolean getWrappedBoolean() {
		return wrappedBoolean;
	}

	public void setWrappedBoolean(Boolean wrappedBoolean) {
		this.wrappedBoolean = wrappedBoolean;
	}

	public byte getPrimativeByte() {
		return primativeByte;
	}

	public void setPrimativeByte(byte primativeByte) {
		this.primativeByte = primativeByte;
	}

	public Byte getWrappedByte() {
		return wrappedByte;
	}

	public void setWrappedByte(Byte wrappedByte) {
		this.wrappedByte = wrappedByte;
	}

	public char getPrimativeChar() {
		return primativeChar;
	}

	public void setPrimativeChar(char primativeChar) {
		this.primativeChar = primativeChar;
	}

	public Character getWrappedChar() {
		return wrappedChar;
	}

	public void setWrappedChar(Character wrappedChar) {
		this.wrappedChar = wrappedChar;
	}

	public double getPrimativeDouble() {
		return primativeDouble;
	}

	public void setPrimativeDouble(double primativeDouble) {
		this.primativeDouble = primativeDouble;
	}

	public Double getWrappedDouble() {
		return wrappedDouble;
	}

	public void setWrappedDouble(Double wrappedDouble) {
		this.wrappedDouble = wrappedDouble;
	}

	public float getPrimativeFloat() {
		return primativeFloat;
	}

	public void setPrimativeFloat(float primativeFloat) {
		this.primativeFloat = primativeFloat;
	}

	public Float getWrappedFloat() {
		return wrappedFloat;
	}

	public void setWrappedFloat(Float wrappedFloat) {
		this.wrappedFloat = wrappedFloat;
	}

	public int getPrimativeInt() {
		return primativeInt;
	}

	public void setPrimativeInt(int primativeInt) {
		this.primativeInt = primativeInt;
	}

	public Integer getWrappedInt() {
		return wrappedInt;
	}

	public void setWrappedInt(Integer wrappedInt) {
		this.wrappedInt = wrappedInt;
	}

	public long getPrimativeLong() {
		return primativeLong;
	}

	public void setPrimativeLong(long primativeLong) {
		this.primativeLong = primativeLong;
	}

	public Long getWrappedLong() {
		return wrappedLong;
	}

	public void setWrappedLong(Long wrappedLong) {
		this.wrappedLong = wrappedLong;
	}

	public short getPrimativeShort() {
		return primativeShort;
	}

	public void setPrimativeShort(short primativeShort) {
		this.primativeShort = primativeShort;
	}

	public Short getWrappedShort() {
		return wrappedShort;
	}

	public void setWrappedShort(Short wrappedShort) {
		this.wrappedShort = wrappedShort;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

}
