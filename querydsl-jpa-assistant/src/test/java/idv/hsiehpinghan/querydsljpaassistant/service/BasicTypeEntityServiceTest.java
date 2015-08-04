package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.enumeration.Enumeration;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTypeEntityServiceTest {
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
	private String lobString = "string";
	private BigInteger bigInteger = BigInteger.ONE;
	private BigDecimal bigDecimal = BigDecimal.ONE;
	private Locale locale = Locale.CHINESE;
	private TimeZone timeZone = TimeZone.getDefault();
	private Currency currency = Currency.getInstance(Locale.US);
	private Class<BasicTypeEntity> clazz = BasicTypeEntity.class;
	private Serializable serializable = new BasicTypeEntity();
	private Date date = Calendar.getInstance().getTime();
	private Date dateDate = Calendar.getInstance().getTime();
	private Date timeDate = Calendar.getInstance().getTime();
	private Date timestampDate = new Date();
	private Calendar calendar = Calendar.getInstance();
	private Calendar dateCalendar = Calendar.getInstance();
	private Calendar timestampCalendar = Calendar.getInstance();
	private java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Time sqlTime = new java.sql.Time(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(Calendar
			.getInstance().getTimeInMillis());
	private java.sql.Clob clob;
	private java.sql.Blob blob;
	private byte[] byteArray = getByteArray();
	private byte[] lobByteArray = getByteArray();
	private char[] charArray = getCharArray();
	private char[] lobCharArray = getCharArray();
	private Enumeration stringEnumeration = Enumeration.ENUM_2;
	private Enumeration ordinalEnumeration = Enumeration.ENUM_3;
	private Integer id;
	private Integer newId;
	private BasicTypeService service;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(BasicTypeService.class);
	}

	@Test
	public void save() {
		BasicTypeEntity entity = generateBasicTypeEntity();
		service.save(entity);
		id = entity.getId();
		Assert.assertEquals(service.findOne(id).getString(), string);
	}

	@Test(dependsOnMethods = { "save" })
	public void findOne1() {
		Assert.assertEquals(service.findOne1(id).getString(), string);
	}

	@Test(dependsOnMethods = { "save" })
	public void countByString() {
		Assert.assertTrue(service.countByString(string) > 0);
	}

	@Test(dependsOnMethods = { "save" })
	public void exists() {
		Assert.assertTrue(service.exists(string));
	}

	@Test(dependsOnMethods = { "save" })
	public void findAllDescentById() {
		BasicTypeEntity newEntity = generateBasicTypeEntity();
		service.save(newEntity);
		newId = newEntity.getId();
		Integer before = Integer.MAX_VALUE;
		for (BasicTypeEntity entity : service.findAllDescentById(string)) {
			Integer current = entity.getId();
			Assert.assertTrue(before.intValue() > current.intValue());
			before = current;
		}
	}

	@Test(dependsOnMethods = { "findAllDescentById" })
	public void delete() {
		service.delete(newId);
		Assert.assertNull(service.findOne(newId));
	}

	@Test
	public void findAllWithPage() {
		int totalSize = 10;
		int sliceSize = 2;
		for (int i = 0; i < totalSize; ++i) {
			service.save(generateBasicTypeEntity());
		}
		int page = 0;
		Page<BasicTypeEntity> entityPage = service
				.findAllWithPage(string, page);
		Assert.assertEquals(entityPage.getSize(), sliceSize);
		Assert.assertTrue(entityPage.getTotalPages() >= (totalSize / sliceSize));
	}

	@AfterClass
	public void afterClass() {
		for (BasicTypeEntity entity : service.findAll()) {
			service.delete(entity.getId());
		}
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
		entity.setString(lobString);
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
}
