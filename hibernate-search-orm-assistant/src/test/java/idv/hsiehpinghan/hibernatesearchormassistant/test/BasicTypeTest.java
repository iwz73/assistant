package idv.hsiehpinghan.hibernatesearchormassistant.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hibernatesearchormassistant.service.BasicTypeService;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.InputStreamUtility;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.ReaderUtility;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class BasicTypeTest extends AbstractTestNGSpringContextTests {
	private boolean primativeBoolean = true;
	private Boolean wrappedBoolean = true;
	private byte primativeByte = 0x1;
	private Byte wrappedByte = 0x1;
	private char primativeChar = 'a';
	private Character wrappedChar = 'a';
	private double primativeDouble = 1.1;
	private Double wrappedDouble = 1.1;
	private float primativeFloat = 1.1f;
	private Float wrappedFloat = 1.1f;
	private int primativeInt = 1;
	private Integer wrappedInt = 1;
	private long primativeLong = 1L;
	private Long wrappedLong = 1L;
	private short primativeShort = 1;
	private Short wrappedShort = 1;
	private String string = "string";
	private String lobString = "lobString";
	private BigInteger bigInteger = BigInteger.ONE;
	private BigDecimal bigDecimal = BigDecimal.ONE;
	private Locale locale = Locale.CHINESE;
	private TimeZone timeZone = TimeZone.getDefault();
	private Currency currency = Currency.getInstance(Locale.US);
	private Class<BasicTypeEntity> clazz = BasicTypeEntity.class;
	private Serializable serializable = new String("serializable");
	private Date date = Calendar.getInstance().getTime();
	private Date dateDate = Calendar.getInstance().getTime();
	private Date timeDate = Calendar.getInstance().getTime();
	private Date timestampDate = new Date();
	private Calendar calendar = Calendar.getInstance();
	private Calendar dateCalendar = Calendar.getInstance();
	private Calendar timestampCalendar = Calendar.getInstance();
	private java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	private java.sql.Time sqlTime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
	private java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	private java.sql.Clob clob;
	private java.sql.Blob blob;
	private byte[] byteArray = getByteArray();
	private byte[] lobByteArray = getByteArray();
	private char[] charArray = getCharArray();
	private char[] lobCharArray = getCharArray();
	private Enumeration stringEnumeration = Enumeration.ENUM_2;
	private Enumeration ordinalEnumeration = Enumeration.ENUM_3;
	@Autowired
	private BasicTypeService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		clob = new SerialClob(getCharArray());
		blob = new SerialBlob(getByteArray());
	}

	@Test
	public void basicType() throws Exception {
		BasicTypeEntity entity = generateBasicTypeEntity();
		service.save(entity);
		int id = entity.getId();
		BasicTypeEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.isPrimativeBoolean(), primativeBoolean);
		Assert.assertEquals(returnEntity.getWrappedBoolean(), wrappedBoolean);
		Assert.assertEquals(String.valueOf(returnEntity.getPrimativeByte()), String.valueOf(primativeByte));
		Assert.assertEquals(returnEntity.getWrappedByte(), wrappedByte);
		Assert.assertEquals(returnEntity.getPrimativeChar(), primativeChar);
		Assert.assertEquals(returnEntity.getWrappedChar(), wrappedChar);
		Assert.assertEquals(returnEntity.getPrimativeDouble(), primativeDouble);
		Assert.assertEquals(returnEntity.getWrappedDouble(), wrappedDouble);
		Assert.assertEquals(returnEntity.getPrimativeFloat(), primativeFloat);
		Assert.assertEquals(returnEntity.getWrappedFloat(), wrappedFloat);
		Assert.assertEquals(returnEntity.getPrimativeInt(), primativeInt);
		Assert.assertEquals(returnEntity.getWrappedInt(), wrappedInt);
		Assert.assertEquals(returnEntity.getPrimativeLong(), primativeLong);
		Assert.assertEquals(returnEntity.getWrappedLong(), wrappedLong);
		Assert.assertEquals(returnEntity.getPrimativeShort(), primativeShort);
		Assert.assertEquals(returnEntity.getWrappedShort(), wrappedShort);
		Assert.assertEquals(returnEntity.getString(), string);
		Assert.assertEquals(returnEntity.getLobString(), lobString);
		Assert.assertEquals(returnEntity.getBigInteger(), bigInteger);
		Assert.assertEquals(returnEntity.getBigDecimal().doubleValue(), bigDecimal.doubleValue());
		Assert.assertEquals(returnEntity.getLocale(), locale);
		Assert.assertEquals(returnEntity.getTimeZone(), timeZone);
		Assert.assertEquals(returnEntity.getCurrency(), currency);
		Assert.assertEquals(returnEntity.getClazz(), clazz);
		Assert.assertEquals(returnEntity.getSerializable().getClass(), serializable.getClass());
		Assert.assertEquals(returnEntity.getDate().getTime(), DateUtils.round(date, Calendar.SECOND).getTime());
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getDateDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(dateDate, "yyyy/MM/dd"));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getTimeDate(), "hh:mm:ss"),
				DateFormatUtils.format(timeDate, "hh:mm:ss"));
		Assert.assertEquals(returnEntity.getTimestampDate().getTime(),
				DateUtils.round(timestampDate, Calendar.SECOND).getTime());
		Assert.assertEquals(returnEntity.getCalendar(), DateUtils.round(calendar, Calendar.SECOND));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getDateCalendar(), "yyyy/MM/dd"),
				DateFormatUtils.format(dateCalendar, "yyyy/MM/dd"));
		Assert.assertEquals(returnEntity.getTimestampCalendar(), DateUtils.round(timestampCalendar, Calendar.SECOND));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getSqlDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(sqlDate.getTime(), "yyyy/MM/dd"));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getSqlTime(), "hh:mm:ss"),
				DateFormatUtils.format(sqlTime, "hh:mm:ss"));
		Assert.assertEquals(returnEntity.getSqlTimestamp().getTime(),
				DateUtils.round(sqlTimestamp, Calendar.SECOND).getTime());
		Assert.assertEquals(service.findClobAsString(id), convertToString(clob));
		Assert.assertEquals(service.findBlobAsString(id), convertToString(blob));
		Assert.assertEquals(returnEntity.getByteArray(), byteArray);
		Assert.assertEquals(returnEntity.getLobByteArray(), lobByteArray);
		Assert.assertEquals(returnEntity.getCharArray(), charArray);
		Assert.assertEquals(returnEntity.getLobCharArray(), lobCharArray);
		Assert.assertEquals(returnEntity.getStringEnumeration(), stringEnumeration);
		Assert.assertEquals(returnEntity.getOrdinalEnumeration(), ordinalEnumeration);
	}

	private BasicTypeEntity generateBasicTypeEntity() {
		BasicTypeEntity entity = new BasicTypeEntity();
		entity.setPrimativeBoolean(primativeBoolean);
		entity.setWrappedBoolean(wrappedBoolean);
		entity.setPrimativeByte(primativeByte);
		entity.setWrappedByte(wrappedByte);
		entity.setPrimativeChar(primativeChar);
		entity.setWrappedChar(wrappedChar);
		entity.setPrimativeDouble(primativeDouble);
		entity.setWrappedDouble(wrappedDouble);
		entity.setPrimativeFloat(primativeFloat);
		entity.setWrappedFloat(wrappedFloat);
		entity.setPrimativeInt(primativeInt);
		entity.setWrappedInt(wrappedInt);
		entity.setPrimativeLong(primativeLong);
		entity.setWrappedLong(wrappedLong);
		entity.setPrimativeShort(primativeShort);
		entity.setWrappedShort(wrappedShort);
		entity.setString(string);
		entity.setLobString(lobString);
		entity.setBigInteger(bigInteger);
		entity.setBigDecimal(bigDecimal);
		entity.setLocale(locale);
		entity.setTimeZone(timeZone);
		entity.setCurrency(currency);
		entity.setClazz(clazz);
		entity.setSerializable(serializable);
		entity.setDate(date);
		entity.setDateDate(dateDate);
		entity.setTimeDate(timeDate);
		entity.setTimestampDate(timestampDate);
		entity.setCalendar(calendar);
		entity.setDateCalendar(dateCalendar);
		entity.setTimestampCalendar(timestampCalendar);
		entity.setSqlDate(sqlDate);
		entity.setSqlTime(sqlTime);
		entity.setSqlTimestamp(sqlTimestamp);
		entity.setClob(clob);
		entity.setBlob(blob);
		entity.setByteArray(byteArray);
		entity.setLobByteArray(lobByteArray);
		entity.setCharArray(charArray);
		entity.setLobCharArray(lobCharArray);
		entity.setStringEnumeration(stringEnumeration);
		entity.setOrdinalEnumeration(ordinalEnumeration);
		return entity;
	}

	private char[] getCharArray() {
		return new char[] { 'a', 'b', 'c' };
	}

	private byte[] getByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	private String convertToString(java.sql.Clob clob) throws Exception {
		Reader reader = clob.getCharacterStream();
		return ReaderUtility.readAsString(reader);
	}

	private String convertToString(java.sql.Blob blob) throws SQLException, IOException {
		InputStream inputStream = blob.getBinaryStream();
		return InputStreamUtility.readAsString(inputStream);
	}

}