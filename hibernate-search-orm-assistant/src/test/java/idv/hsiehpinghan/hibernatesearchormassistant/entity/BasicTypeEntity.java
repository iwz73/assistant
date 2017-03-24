package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.BigDecimalBridge;
import org.hibernate.search.bridge.builtin.BigIntegerBridge;
import org.hibernate.search.bridge.builtin.BooleanBridge;
import org.hibernate.search.bridge.builtin.DoubleBridge;
import org.hibernate.search.bridge.builtin.EnumBridge;
import org.hibernate.search.bridge.builtin.FloatBridge;
import org.hibernate.search.bridge.builtin.IntegerBridge;
import org.hibernate.search.bridge.builtin.LongBridge;
import org.hibernate.search.bridge.builtin.ShortBridge;
import org.hibernate.search.bridge.builtin.UriBridge;
import org.hibernate.search.bridge.builtin.UrlBridge;

import idv.hsiehpinghan.hibernatesearchormassistant.enumeration.Enumeration;

@Entity
@Indexed
public class BasicTypeEntity implements Serializable {
	public static final String DEFAULT_FIELD = "string";
	private static final long serialVersionUID = 1L;
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	// primative
	@Field
	@FieldBridge(impl = BooleanBridge.class)
	private boolean primativeBoolean;
	@Field
	@FieldBridge(impl = BooleanBridge.class)
	private Boolean wrappedBoolean;
	private byte primativeByte;
	private Byte wrappedByte;
	private char primativeChar;
	private Character wrappedChar;
	@Field
	@FieldBridge(impl = DoubleBridge.class)
	private double primativeDouble;
	@Field
	@FieldBridge(impl = DoubleBridge.class)
	private Double wrappedDouble;
	@Field
	@FieldBridge(impl = FloatBridge.class)
	private float primativeFloat;
	@Field
	@FieldBridge(impl = FloatBridge.class)
	private Float wrappedFloat;
	@Field
	@FieldBridge(impl = IntegerBridge.class)
	private int primativeInt;
	@Field
	@FieldBridge(impl = IntegerBridge.class)
	private Integer wrappedInt;
	@Field
	@FieldBridge(impl = LongBridge.class)
	private long primativeLong;
	@Field
	@FieldBridge(impl = LongBridge.class)
	private Long wrappedLong;
	@Field
	@FieldBridge(impl = ShortBridge.class)
	private short primativeShort;
	@Field
	@FieldBridge(impl = ShortBridge.class)
	private Short wrappedShort;
	// object
	@Field
	private String string;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String lobString;
	@Field
	@FieldBridge(impl = BigIntegerBridge.class)
	private BigInteger bigInteger;
	@Field
	@FieldBridge(impl = BigDecimalBridge.class)
	private BigDecimal bigDecimal;
	private Locale locale;
	private TimeZone timeZone;
	private Currency currency;
	private Class<BasicTypeEntity> clazz;
	private Serializable serializable;
	@Field
	private Date date;
	@Field
	@Temporal(TemporalType.DATE)
	private Date dateDate;
	@Field
	@Temporal(TemporalType.TIME)
	private Date timeDate;
	@Field
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestampDate;
	// sql
	private Calendar calendar;
	@Temporal(TemporalType.DATE)
	private Calendar dateCalendar;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestampCalendar;
	private java.sql.Date sqlDate;
	private java.sql.Time sqlTime;
	private java.sql.Timestamp sqlTimestamp;
	private java.sql.Clob clob;
	@Column(name = "[blob]")
	private java.sql.Blob blob;
	// array
	private byte[] byteArray;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] lobByteArray;
	private char[] charArray;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private char[] lobCharArray;
	@Field
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumBridge.class)
	private Enumeration stringEnumeration;
	@Field
	@Enumerated(EnumType.ORDINAL)
	@FieldBridge(impl = EnumBridge.class)
	private Enumeration ordinalEnumeration;
	@Field
	@FieldBridge(impl = UrlBridge.class)
	private URL url;
	@Field
	@FieldBridge(impl = UriBridge.class)
	private URI uri;
	@Field
	private String englishString_0;
	@Field(analyzer = @Analyzer(impl = StandardAnalyzer.class), boost = @Boost(0.5f))
	private String englishString_1;
	@Field
	@DateBridge(resolution = Resolution.YEAR)
	private Date yearResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.MONTH)
	private Date monthResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.DAY)
	private Date dayResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.HOUR)
	private Date hourResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.MINUTE)
	private Date minuteResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.SECOND)
	private Date secondResolutionDate;
	@Field
	@DateBridge(resolution = Resolution.MILLISECOND)
	private Date millisecondResolutionDate;
	@Field(analyze = Analyze.NO)
	private String noAnalyzeString;
	@Fields({ @Field(name = "multiAnalyzeStringYes", analyze = Analyze.YES),
			@Field(name = "multiAnalyzeStringNo", analyze = Analyze.NO) })
	private String multiAnalyzeString;
	@Field(store = Store.YES)
	private String projectionString;
	@Field(analyze = Analyze.NO)
	private String sortString;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLobString() {
		return lobString;
	}

	public void setLobString(String lobString) {
		this.lobString = lobString;
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

	public Class<BasicTypeEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Class<BasicTypeEntity> clazz) {
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

	public Date getDateDate() {
		return dateDate;
	}

	public void setDateDate(Date dateDate) {
		this.dateDate = dateDate;
	}

	public Date getTimeDate() {
		return timeDate;
	}

	public void setTimeDate(Date timeDate) {
		this.timeDate = timeDate;
	}

	public Date getTimestampDate() {
		return timestampDate;
	}

	public void setTimestampDate(Date timestampDate) {
		this.timestampDate = timestampDate;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Calendar getDateCalendar() {
		return dateCalendar;
	}

	public void setDateCalendar(Calendar dateCalendar) {
		this.dateCalendar = dateCalendar;
	}

	public Calendar getTimestampCalendar() {
		return timestampCalendar;
	}

	public void setTimestampCalendar(Calendar timestampCalendar) {
		this.timestampCalendar = timestampCalendar;
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

	public java.sql.Clob getClob() {
		return clob;
	}

	public void setClob(java.sql.Clob clob) {
		this.clob = clob;
	}

	public java.sql.Blob getBlob() {
		return blob;
	}

	public void setBlob(java.sql.Blob blob) {
		this.blob = blob;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public byte[] getLobByteArray() {
		return lobByteArray;
	}

	public void setLobByteArray(byte[] lobByteArray) {
		this.lobByteArray = lobByteArray;
	}

	public char[] getCharArray() {
		return charArray;
	}

	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}

	public char[] getLobCharArray() {
		return lobCharArray;
	}

	public void setLobCharArray(char[] lobCharArray) {
		this.lobCharArray = lobCharArray;
	}

	public Enumeration getStringEnumeration() {
		return stringEnumeration;
	}

	public void setStringEnumeration(Enumeration stringEnumeration) {
		this.stringEnumeration = stringEnumeration;
	}

	public Enumeration getOrdinalEnumeration() {
		return ordinalEnumeration;
	}

	public void setOrdinalEnumeration(Enumeration ordinalEnumeration) {
		this.ordinalEnumeration = ordinalEnumeration;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getEnglishString_0() {
		return englishString_0;
	}

	public void setEnglishString_0(String englishString_0) {
		this.englishString_0 = englishString_0;
	}

	public String getEnglishString_1() {
		return englishString_1;
	}

	public void setEnglishString_1(String englishString_1) {
		this.englishString_1 = englishString_1;
	}

	public Date getYearResolutionDate() {
		return yearResolutionDate;
	}

	public void setYearResolutionDate(Date yearResolutionDate) {
		this.yearResolutionDate = yearResolutionDate;
	}

	public Date getMonthResolutionDate() {
		return monthResolutionDate;
	}

	public void setMonthResolutionDate(Date monthResolutionDate) {
		this.monthResolutionDate = monthResolutionDate;
	}

	public Date getDayResolutionDate() {
		return dayResolutionDate;
	}

	public void setDayResolutionDate(Date dayResolutionDate) {
		this.dayResolutionDate = dayResolutionDate;
	}

	public Date getHourResolutionDate() {
		return hourResolutionDate;
	}

	public void setHourResolutionDate(Date hourResolutionDate) {
		this.hourResolutionDate = hourResolutionDate;
	}

	public Date getMinuteResolutionDate() {
		return minuteResolutionDate;
	}

	public void setMinuteResolutionDate(Date minuteResolutionDate) {
		this.minuteResolutionDate = minuteResolutionDate;
	}

	public Date getSecondResolutionDate() {
		return secondResolutionDate;
	}

	public void setSecondResolutionDate(Date secondResolutionDate) {
		this.secondResolutionDate = secondResolutionDate;
	}

	public Date getMillisecondResolutionDate() {
		return millisecondResolutionDate;
	}

	public void setMillisecondResolutionDate(Date millisecondResolutionDate) {
		this.millisecondResolutionDate = millisecondResolutionDate;
	}

	public String getNoAnalyzeString() {
		return noAnalyzeString;
	}

	public void setNoAnalyzeString(String noAnalyzeString) {
		this.noAnalyzeString = noAnalyzeString;
	}

	public String getMultiAnalyzeString() {
		return multiAnalyzeString;
	}

	public void setMultiAnalyzeString(String multiAnalyzeString) {
		this.multiAnalyzeString = multiAnalyzeString;
	}

	public String getProjectionString() {
		return projectionString;
	}

	public void setProjectionString(String projectionString) {
		this.projectionString = projectionString;
	}

	public String getSortString() {
		return sortString;
	}

	public void setSortString(String sortString) {
		this.sortString = sortString;
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
