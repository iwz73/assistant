package idv.hsiehpinghan.neo4jassistant.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.PostLoad;
import org.neo4j.ogm.annotation.Properties;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.neo4j.ogm.annotation.typeconversion.EnumString;
import org.neo4j.ogm.annotation.typeconversion.NumberString;
import org.neo4j.ogm.typeconversion.InstantLongConverter;
import org.neo4j.ogm.typeconversion.InstantStringConverter;
import org.neo4j.ogm.typeconversion.LocalDateStringConverter;
import org.neo4j.ogm.typeconversion.LocalDateTimeStringConverter;
import org.neo4j.ogm.typeconversion.OffsettDateTimeStringConverter;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import idv.hsiehpinghan.neo4jassistant.enumeration.Enumeration;
import idv.hsiehpinghan.neo4jassistant.relationship.BasicRelationship;

/**
 * reference : https://www.javadoc.io/doc/org.neo4j/neo4j-ogm-core/3.1.4
 */
@NodeEntity
// @CompositeIndex(value = { "string" }, unique = true) // enterprise version
// only
public class BasicNode extends BaseNode {
	@Labels
	private Set<String> labels;
	// primative
	private boolean primativeBoolean;
	private Boolean wrappedBoolean;
	private byte primativeByte;
	private Byte wrappedByte;
	@NumberString(value = Byte.class)
	private Byte byteString;
	private char primativeChar;
	private Character wrappedChar;
	private double primativeDouble;
	private Double wrappedDouble;
	@NumberString(value = Double.class)
	private Double doubleString;
	private float primativeFloat;
	private Float wrappedFloat;
	@NumberString(value = Float.class)
	private Float floatString;
	private int primativeInt;
	private Integer wrappedInt;
	@NumberString(value = Integer.class)
	private Integer integerString;
	private long primativeLong;
	private Long wrappedLong;
	@NumberString(value = Long.class)
	private Long longString;
	private short primativeShort;
	private Short wrappedShort;
	// object
	@Convert(value = UuidStringConverter.class)
	private UUID uuid;
	private String string = "string";
	private BigDecimal bigDecimal;
	@NumberString(value = BigDecimal.class)
	private BigDecimal bigDecimalString;
	private BigInteger bigInteger;
	@NumberString(value = BigInteger.class)
	private BigInteger bigIntegerString;
	private Enumeration enum_;
	@EnumString(value = Enumeration.class)
	private Enumeration enumString;
	// array
	private byte[] byteArray;
	private String[] stringArray;
	// collection
	private List<Date> dateList;
	private List<Enumeration> enumList;
	// map
	@Properties(prefix = "map_", delimiter = ",", allowCast = true)
	private Map<String, Integer> map;
	// temporal
	private Date date;
	@DateLong
	private Date dateLong;
	@DateString
	private Date dateString;
	private Instant instant;
	@Convert(value = InstantLongConverter.class)
	private Instant instantLong;
	@Convert(value = InstantStringConverter.class)
	private Instant instantString;
	private LocalDate localDate;
	@Convert(value = LocalDateStringConverter.class)
	private LocalDate localDateString;
	private LocalDateTime localDateTime;
	@Convert(value = LocalDateTimeStringConverter.class)
	private LocalDateTime localDateTimeString;
	private OffsetDateTime offsetDateTime;
	@Convert(value = OffsettDateTimeStringConverter.class)
	private OffsetDateTime offsetDateTimeString;
	// transient
	@Transient
	private String transientValue;
	// postLoad
	@Transient
	private String postLoadValue;
	// index
	@Index(unique = true)
	private String indexString;
	// // required
	// @Required // enterprise version only
	// private String requiredString;
	// relationship
	@Relationship(type = "type")
	private BasicNode outcomeNode;
	@Relationship(type = "type", direction = "INCOMING")
	private BasicNode incomeNode;
	@Relationship(type = "basic")
	private Set<BasicRelationship> outcomeRelationships;
	@Relationship(type = "basic", direction = "INCOMING")
	private Set<BasicRelationship> incomeRelationships;

	public BasicNode() {
		super();
	}

	public BasicNode(String id, Set<String> labels, boolean primativeBoolean, Boolean wrappedBoolean,
			byte primativeByte, Byte wrappedByte, Byte byteString, char primativeChar, Character wrappedChar,
			double primativeDouble, Double wrappedDouble, Double doubleString, float primativeFloat, Float wrappedFloat,
			Float floatString, int primativeInt, Integer wrappedInt, Integer integerString, long primativeLong,
			Long wrappedLong, Long longString, short primativeShort, Short wrappedShort, UUID uuid, String string,
			BigDecimal bigDecimal, BigDecimal bigDecimalString, BigInteger bigInteger, BigInteger bigIntegerString,
			Enumeration enum_, Enumeration enumString, byte[] byteArray, String[] stringArray, List<Date> dateList,
			List<Enumeration> enumList, Map<String, Integer> map, Date date, Date dateLong, Date dateString,
			Instant instant, Instant instantLong, Instant instantString, LocalDate localDate, LocalDate localDateString,
			LocalDateTime localDateTime, LocalDateTime localDateTimeString, OffsetDateTime offsetDateTime,
			OffsetDateTime offsetDateTimeString, String transientValue, String indexString, BasicNode outcomeNode,
			BasicNode incomeNode, Set<BasicRelationship> outcomeRelationships,
			Set<BasicRelationship> incomeRelationships) {
		super(id);
		this.labels = labels;
		this.primativeBoolean = primativeBoolean;
		this.wrappedBoolean = wrappedBoolean;
		this.primativeByte = primativeByte;
		this.wrappedByte = wrappedByte;
		this.byteString = byteString;
		this.primativeChar = primativeChar;
		this.wrappedChar = wrappedChar;
		this.primativeDouble = primativeDouble;
		this.wrappedDouble = wrappedDouble;
		this.doubleString = doubleString;
		this.primativeFloat = primativeFloat;
		this.wrappedFloat = wrappedFloat;
		this.floatString = floatString;
		this.primativeInt = primativeInt;
		this.wrappedInt = wrappedInt;
		this.integerString = integerString;
		this.primativeLong = primativeLong;
		this.wrappedLong = wrappedLong;
		this.longString = longString;
		this.primativeShort = primativeShort;
		this.wrappedShort = wrappedShort;
		this.uuid = uuid;
		this.string = string;
		this.bigDecimal = bigDecimal;
		this.bigDecimalString = bigDecimalString;
		this.bigInteger = bigInteger;
		this.bigIntegerString = bigIntegerString;
		this.enum_ = enum_;
		this.enumString = enumString;
		this.byteArray = byteArray;
		this.stringArray = stringArray;
		this.dateList = dateList;
		this.enumList = enumList;
		this.map = map;
		this.date = date;
		this.dateLong = dateLong;
		this.dateString = dateString;
		this.instant = instant;
		this.instantLong = instantLong;
		this.instantString = instantString;
		this.localDate = localDate;
		this.localDateString = localDateString;
		this.localDateTime = localDateTime;
		this.localDateTimeString = localDateTimeString;
		this.offsetDateTime = offsetDateTime;
		this.offsetDateTimeString = offsetDateTimeString;
		this.transientValue = transientValue;
		this.indexString = indexString;
		this.outcomeNode = outcomeNode;
		this.incomeNode = incomeNode;
		this.outcomeRelationships = outcomeRelationships;
		this.incomeRelationships = incomeRelationships;
	}

	public Set<String> getLabels() {
		return labels;
	}

	public void setLabels(Set<String> labels) {
		this.labels = labels;
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

	public Byte getByteString() {
		return byteString;
	}

	public void setByteString(Byte byteString) {
		this.byteString = byteString;
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

	public Double getDoubleString() {
		return doubleString;
	}

	public void setDoubleString(Double doubleString) {
		this.doubleString = doubleString;
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

	public Float getFloatString() {
		return floatString;
	}

	public void setFloatString(Float floatString) {
		this.floatString = floatString;
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

	public Integer getIntegerString() {
		return integerString;
	}

	public void setIntegerString(Integer integerString) {
		this.integerString = integerString;
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

	public Long getLongString() {
		return longString;
	}

	public void setLongString(Long longString) {
		this.longString = longString;
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

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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

	public BigDecimal getBigDecimalString() {
		return bigDecimalString;
	}

	public void setBigDecimalString(BigDecimal bigDecimalString) {
		this.bigDecimalString = bigDecimalString;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public BigInteger getBigIntegerString() {
		return bigIntegerString;
	}

	public void setBigIntegerString(BigInteger bigIntegerString) {
		this.bigIntegerString = bigIntegerString;
	}

	public Enumeration getEnum_() {
		return enum_;
	}

	public void setEnum_(Enumeration enum_) {
		this.enum_ = enum_;
	}

	public Enumeration getEnumString() {
		return enumString;
	}

	public void setEnumString(Enumeration enumString) {
		this.enumString = enumString;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public String[] getStringArray() {
		return stringArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	public List<Date> getDateList() {
		return dateList;
	}

	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}

	public List<Enumeration> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<Enumeration> enumList) {
		this.enumList = enumList;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateLong() {
		return dateLong;
	}

	public void setDateLong(Date dateLong) {
		this.dateLong = dateLong;
	}

	public Date getDateString() {
		return dateString;
	}

	public void setDateString(Date dateString) {
		this.dateString = dateString;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Instant getInstantLong() {
		return instantLong;
	}

	public void setInstantLong(Instant instantLong) {
		this.instantLong = instantLong;
	}

	public Instant getInstantString() {
		return instantString;
	}

	public void setInstantString(Instant instantString) {
		this.instantString = instantString;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public LocalDate getLocalDateString() {
		return localDateString;
	}

	public void setLocalDateString(LocalDate localDateString) {
		this.localDateString = localDateString;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public LocalDateTime getLocalDateTimeString() {
		return localDateTimeString;
	}

	public void setLocalDateTimeString(LocalDateTime localDateTimeString) {
		this.localDateTimeString = localDateTimeString;
	}

	public OffsetDateTime getOffsetDateTime() {
		return offsetDateTime;
	}

	public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
		this.offsetDateTime = offsetDateTime;
	}

	public OffsetDateTime getOffsetDateTimeString() {
		return offsetDateTimeString;
	}

	public void setOffsetDateTimeString(OffsetDateTime offsetDateTimeString) {
		this.offsetDateTimeString = offsetDateTimeString;
	}

	public String getTransientValue() {
		return transientValue;
	}

	public void setTransientValue(String transientValue) {
		this.transientValue = transientValue;
	}

	public String getPostLoadValue() {
		return postLoadValue;
	}

	public void setPostLoadValue(String postLoadValue) {
		this.postLoadValue = postLoadValue;
	}

	public String getIndexString() {
		return indexString;
	}

	public void setIndexString(String indexString) {
		this.indexString = indexString;
	}

	public BasicNode getOutcomeNode() {
		return outcomeNode;
	}

	public void setOutcomeNode(BasicNode outcomeNode) {
		this.outcomeNode = outcomeNode;
	}

	public BasicNode getIncomeNode() {
		return incomeNode;
	}

	public void setIncomeNode(BasicNode incomeNode) {
		this.incomeNode = incomeNode;
	}

	public Set<BasicRelationship> getOutcomeRelationships() {
		return outcomeRelationships;
	}

	public void setOutcomeRelationships(Set<BasicRelationship> outcomeRelationships) {
		this.outcomeRelationships = outcomeRelationships;
	}

	public Set<BasicRelationship> getIncomeRelationships() {
		return incomeRelationships;
	}

	public void setIncomeRelationships(Set<BasicRelationship> incomeRelationships) {
		this.incomeRelationships = incomeRelationships;
	}

	@PostLoad
	private void postLoad() {
		postLoadValue = "postLoadValue";
	}

}
