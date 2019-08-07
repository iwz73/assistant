package idv.hsiehpinghan.commonsbeanutilsassistant.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import idv.hsiehpinghan.commonsbeanutilsassistant.enumeration.Enumeration;

public class BasicTypeVo implements Serializable {
	private static final long serialVersionUID = 1L;
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
	private Locale locale;
	private TimeZone timeZone;
	private Currency currency;
	private Class<BasicTypeVo> clazz;
	private Serializable serializable;
	private Date date;
	private Calendar calendar;
	private java.sql.Date sqlDate;
	private java.sql.Time sqlTime;
	private java.sql.Timestamp sqlTimestamp;
	private byte[] byteArray;
	private char[] charArray;
	private Enumeration enumeration;
	private SubBasicType subBasicType;
	private List<SubBasicType> subBasicTypeList;
	private Set<SubBasicType> subBasicTypeSet;
	private Map<SubBasicType, SubBasicType> subBasicTypeMap;

	public BasicTypeVo() {
		super();
	}

	public BasicTypeVo(boolean primativeBoolean, Boolean wrappedBoolean, byte primativeByte, Byte wrappedByte,
			char primativeChar, Character wrappedChar, double primativeDouble, Double wrappedDouble,
			float primativeFloat, Float wrappedFloat, int primativeInt, Integer wrappedInt, long primativeLong,
			Long wrappedLong, short primativeShort, Short wrappedShort, String string, BigInteger bigInteger,
			BigDecimal bigDecimal, Locale locale, TimeZone timeZone, Currency currency, Class<BasicTypeVo> clazz,
			Serializable serializable, Date date, Calendar calendar, java.sql.Date sqlDate, Time sqlTime,
			Timestamp sqlTimestamp, byte[] byteArray, char[] charArray, Enumeration enumeration,
			SubBasicType subBasicType, List<SubBasicType> subBasicTypeList, Set<SubBasicType> subBasicTypeSet,
			Map<SubBasicType, SubBasicType> subBasicTypeMap) {
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
		this.locale = locale;
		this.timeZone = timeZone;
		this.currency = currency;
		this.clazz = clazz;
		this.serializable = serializable;
		this.date = date;
		this.calendar = calendar;
		this.sqlDate = sqlDate;
		this.sqlTime = sqlTime;
		this.sqlTimestamp = sqlTimestamp;
		this.byteArray = byteArray;
		this.charArray = charArray;
		this.enumeration = enumeration;
		this.subBasicType = subBasicType;
		this.subBasicTypeList = subBasicTypeList;
		this.subBasicTypeSet = subBasicTypeSet;
		this.subBasicTypeMap = subBasicTypeMap;
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

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Class<BasicTypeVo> getClazz() {
		return clazz;
	}

	public void setClazz(Class<BasicTypeVo> clazz) {
		this.clazz = clazz;
	}

	public Serializable getSerializable() {
		return serializable;
	}

	public void setSerializable(Serializable serializable) {
		this.serializable = serializable;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
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

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public char[] getCharArray() {
		return charArray;
	}

	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public SubBasicType getSubBasicType() {
		return subBasicType;
	}

	public void setSubBasicType(SubBasicType subBasicType) {
		this.subBasicType = subBasicType;
	}

	public List<SubBasicType> getSubBasicTypeList() {
		return subBasicTypeList;
	}

	public void setSubBasicTypeList(List<SubBasicType> subBasicTypeList) {
		this.subBasicTypeList = subBasicTypeList;
	}

	public Set<SubBasicType> getSubBasicTypeSet() {
		return subBasicTypeSet;
	}

	public void setSubBasicTypeSet(Set<SubBasicType> subBasicTypeSet) {
		this.subBasicTypeSet = subBasicTypeSet;
	}

	public Map<SubBasicType, SubBasicType> getSubBasicTypeMap() {
		return subBasicTypeMap;
	}

	public void setSubBasicTypeMap(Map<SubBasicType, SubBasicType> subBasicTypeMap) {
		this.subBasicTypeMap = subBasicTypeMap;
	}

	@Override
	public String toString() {
		return "BasicTypeVo [primativeBoolean=" + primativeBoolean + ", wrappedBoolean=" + wrappedBoolean
				+ ", primativeByte=" + primativeByte + ", wrappedByte=" + wrappedByte + ", primativeChar="
				+ primativeChar + ", wrappedChar=" + wrappedChar + ", primativeDouble=" + primativeDouble
				+ ", wrappedDouble=" + wrappedDouble + ", primativeFloat=" + primativeFloat + ", wrappedFloat="
				+ wrappedFloat + ", primativeInt=" + primativeInt + ", wrappedInt=" + wrappedInt + ", primativeLong="
				+ primativeLong + ", wrappedLong=" + wrappedLong + ", primativeShort=" + primativeShort
				+ ", wrappedShort=" + wrappedShort + ", string=" + string + ", bigInteger=" + bigInteger
				+ ", bigDecimal=" + bigDecimal + ", locale=" + locale + ", timeZone=" + timeZone + ", currency="
				+ currency + ", clazz=" + clazz + ", serializable=" + serializable + ", date=" + date + ", calendar="
				+ calendar + ", sqlDate=" + sqlDate + ", sqlTime=" + sqlTime + ", sqlTimestamp=" + sqlTimestamp
				+ ", byteArray=" + Arrays.toString(byteArray) + ", charArray=" + Arrays.toString(charArray)
				+ ", enumeration=" + enumeration + ", subBasicType=" + subBasicType + ", subBasicTypeList="
				+ subBasicTypeList + ", subBasicTypeSet=" + subBasicTypeSet + ", subBasicTypeMap=" + subBasicTypeMap
				+ "]";
	}

	public static class SubBasicType implements Comparable<SubBasicType> {
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
		private Locale locale;
		private TimeZone timeZone;
		private Currency currency;
		private Class<BasicTypeVo> clazz;
		private Serializable serializable;
		private Date date;
		private Calendar calendar;
		private java.sql.Date sqlDate;
		private java.sql.Time sqlTime;
		private java.sql.Timestamp sqlTimestamp;
		private byte[] byteArray;
		private char[] charArray;
		private Enumeration enumeration;
		private List<String> stringList;
		private Set<String> stringSet;
		private Map<String, String> stringMap;

		public SubBasicType() {
			super();
		}

		public SubBasicType(boolean primativeBoolean, Boolean wrappedBoolean, byte primativeByte, Byte wrappedByte,
				char primativeChar, Character wrappedChar, double primativeDouble, Double wrappedDouble,
				float primativeFloat, Float wrappedFloat, int primativeInt, Integer wrappedInt, long primativeLong,
				Long wrappedLong, short primativeShort, Short wrappedShort, String string, BigInteger bigInteger,
				BigDecimal bigDecimal, Locale locale, TimeZone timeZone, Currency currency, Class<BasicTypeVo> clazz,
				Serializable serializable, Date date, Calendar calendar, java.sql.Date sqlDate, Time sqlTime,
				Timestamp sqlTimestamp, byte[] byteArray, char[] charArray, Enumeration enumeration,
				List<String> stringList, Set<String> stringSet, Map<String, String> stringMap) {
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
			this.locale = locale;
			this.timeZone = timeZone;
			this.currency = currency;
			this.clazz = clazz;
			this.serializable = serializable;
			this.date = date;
			this.calendar = calendar;
			this.sqlDate = sqlDate;
			this.sqlTime = sqlTime;
			this.sqlTimestamp = sqlTimestamp;
			this.byteArray = byteArray;
			this.charArray = charArray;
			this.enumeration = enumeration;
			this.stringList = stringList;
			this.stringSet = stringSet;
			this.stringMap = stringMap;
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

		public Locale getLocale() {
			return locale;
		}

		public void setLocale(Locale locale) {
			this.locale = locale;
		}

		public TimeZone getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(TimeZone timeZone) {
			this.timeZone = timeZone;
		}

		public Currency getCurrency() {
			return currency;
		}

		public void setCurrency(Currency currency) {
			this.currency = currency;
		}

		public Class<BasicTypeVo> getClazz() {
			return clazz;
		}

		public void setClazz(Class<BasicTypeVo> clazz) {
			this.clazz = clazz;
		}

		public Serializable getSerializable() {
			return serializable;
		}

		public void setSerializable(Serializable serializable) {
			this.serializable = serializable;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Calendar getCalendar() {
			return calendar;
		}

		public void setCalendar(Calendar calendar) {
			this.calendar = calendar;
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

		public byte[] getByteArray() {
			return byteArray;
		}

		public void setByteArray(byte[] byteArray) {
			this.byteArray = byteArray;
		}

		public char[] getCharArray() {
			return charArray;
		}

		public void setCharArray(char[] charArray) {
			this.charArray = charArray;
		}

		public Enumeration getEnumeration() {
			return enumeration;
		}

		public void setEnumeration(Enumeration enumeration) {
			this.enumeration = enumeration;
		}

		public List<String> getStringList() {
			return stringList;
		}

		public void setStringList(List<String> stringList) {
			this.stringList = stringList;
		}

		public Set<String> getStringSet() {
			return stringSet;
		}

		public void setStringSet(Set<String> stringSet) {
			this.stringSet = stringSet;
		}

		public Map<String, String> getStringMap() {
			return stringMap;
		}

		public void setStringMap(Map<String, String> stringMap) {
			this.stringMap = stringMap;
		}

		@Override
		public String toString() {
			return "SubBasicType [primativeBoolean=" + primativeBoolean + ", wrappedBoolean=" + wrappedBoolean
					+ ", primativeByte=" + primativeByte + ", wrappedByte=" + wrappedByte + ", primativeChar="
					+ primativeChar + ", wrappedChar=" + wrappedChar + ", primativeDouble=" + primativeDouble
					+ ", wrappedDouble=" + wrappedDouble + ", primativeFloat=" + primativeFloat + ", wrappedFloat="
					+ wrappedFloat + ", primativeInt=" + primativeInt + ", wrappedInt=" + wrappedInt
					+ ", primativeLong=" + primativeLong + ", wrappedLong=" + wrappedLong + ", primativeShort="
					+ primativeShort + ", wrappedShort=" + wrappedShort + ", string=" + string + ", bigInteger="
					+ bigInteger + ", bigDecimal=" + bigDecimal + ", locale=" + locale + ", timeZone=" + timeZone
					+ ", currency=" + currency + ", clazz=" + clazz + ", serializable=" + serializable + ", date="
					+ date + ", calendar=" + calendar + ", sqlDate=" + sqlDate + ", sqlTime=" + sqlTime
					+ ", sqlTimestamp=" + sqlTimestamp + ", byteArray=" + Arrays.toString(byteArray) + ", charArray="
					+ Arrays.toString(charArray) + ", enumeration=" + enumeration + ", stringList=" + stringList
					+ ", stringSet=" + stringSet + ", stringMap=" + stringMap + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + primativeInt;
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
			SubBasicType other = (SubBasicType) obj;
			if (primativeInt != other.primativeInt)
				return false;
			return true;
		}

		@Override
		public int compareTo(SubBasicType o) {
			return Integer.compare(primativeInt, o.getPrimativeInt());
		}

	}
}
