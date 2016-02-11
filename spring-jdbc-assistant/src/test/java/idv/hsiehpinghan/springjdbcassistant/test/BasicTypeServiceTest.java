package idv.hsiehpinghan.springjdbcassistant.test;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springjdbcassistant.service.BasicTypeService;
import idv.hsiehpinghan.springjdbcassistant.suit.TestngSuitSetting;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTypeServiceTest {
	// original field
	private boolean primativeBoolean = true;
	private byte primativeByte = 0x1;
	private double primativeDouble = 1.1;
	private float primativeFloat = 1.1f;
	private int primativeInt = 1;
	private long primativeLong = 1L;
	private short primativeShort = 1;
	private String string = "string";
	private BigDecimal bigDecimal = BigDecimal.ONE;
	private java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Time sqlTime = new java.sql.Time(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(Calendar
			.getInstance().getTimeInMillis());
	private byte[] byteArray = getByteArray();
	// updated field
	private boolean primativeBoolean_u = false;
	private byte primativeByte_u = 0x2;
	private double primativeDouble_u = 2.2;
	private float primativeFloat_u = 2.2f;
	private int primativeInt_u = 2;
	private long primativeLong_u = 2L;
	private short primativeShort_u = 2;
	private String string_u = "string_u";
	private BigDecimal bigDecimal_u = BigDecimal.TEN;
	private java.sql.Date sqlDate_u = new java.sql.Date(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Time sqlTime_u = new java.sql.Time(Calendar.getInstance()
			.getTimeInMillis());
	private java.sql.Timestamp sqlTimestamp_u = new java.sql.Timestamp(Calendar
			.getInstance().getTimeInMillis());
	private byte[] byteArray_u = getByteArray_u();

	private ApplicationContext applicationContext;
	private BasicTypeService service;
	private BasicTypeEntity entity;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(BasicTypeService.class);
	}

	@Test
	public void insertByPreparedStatementCreator() {
		entity = generateBasicTypeEntity();
		int result = service.insertByPreparedStatementCreator(entity);
		Assert.assertEquals(result, 1);
	}

	@Test(dependsOnMethods = { "insertByPreparedStatementCreator" })
	public void queryForObjectByRowMapper() throws Exception {
		BasicTypeEntity entity = service.queryForObjectByRowMapper(this.entity
				.getId());
		Assert.assertEquals(entity.isPrimativeBoolean(), primativeBoolean);
		Assert.assertEquals(String.valueOf(entity.getPrimativeByte()),
				String.valueOf(primativeByte));
		Assert.assertEquals(entity.getPrimativeDouble(), primativeDouble);
		Assert.assertEquals(entity.getPrimativeFloat(), primativeFloat);
		Assert.assertEquals(entity.getPrimativeInt(), primativeInt);
		Assert.assertEquals(entity.getPrimativeLong(), primativeLong);
		Assert.assertEquals(entity.getPrimativeShort(), primativeShort);
		Assert.assertEquals(entity.getString(), string);
		Assert.assertEquals(entity.getBigDecimal().doubleValue(),
				bigDecimal.doubleValue());
		Assert.assertEquals(
				DateFormatUtils.format(entity.getSqlDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(sqlDate.getTime(), "yyyy/MM/dd"));
		Assert.assertEquals(
				DateFormatUtils.format(entity.getSqlTime(), "hh:mm:ss"),
				DateFormatUtils.format(sqlTime, "hh:mm:ss"));
		Assert.assertEquals(entity.getSqlTimestamp(), sqlTimestamp);
		Assert.assertEquals(entity.getByteArray(), byteArray);
	}

	@Test(dependsOnMethods = { "queryForObjectByRowMapper" })
	public void updateByPreparedStatementCreator() {
		Long id = this.entity.getId();
		BasicTypeEntity entity = generateBasicTypeEntity_u(id);
		int result = service.updateByPreparedStatementCreator(entity);
		Assert.assertEquals(result, 1);
		BasicTypeEntity returnEntity = service.queryForObjectByRowMapper(id);
		Assert.assertEquals(returnEntity.isPrimativeBoolean(),
				primativeBoolean_u);
		Assert.assertEquals(String.valueOf(returnEntity.getPrimativeByte()),
				String.valueOf(primativeByte_u));
		Assert.assertEquals(returnEntity.getPrimativeDouble(),
				primativeDouble_u);
		Assert.assertEquals(returnEntity.getPrimativeFloat(), primativeFloat_u);
		Assert.assertEquals(returnEntity.getPrimativeInt(), primativeInt_u);
		Assert.assertEquals(returnEntity.getPrimativeLong(), primativeLong_u);
		Assert.assertEquals(returnEntity.getPrimativeShort(), primativeShort_u);
		Assert.assertEquals(returnEntity.getString(), string_u);
		Assert.assertEquals(returnEntity.getBigDecimal().doubleValue(),
				bigDecimal_u.doubleValue());
		Assert.assertEquals(
				DateFormatUtils.format(returnEntity.getSqlDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(sqlDate_u.getTime(), "yyyy/MM/dd"));
		Assert.assertEquals(
				DateFormatUtils.format(returnEntity.getSqlTime(), "hh:mm:ss"),
				DateFormatUtils.format(sqlTime_u, "hh:mm:ss"));
		Assert.assertEquals(returnEntity.getSqlTimestamp(), sqlTimestamp_u);
		Assert.assertEquals(returnEntity.getByteArray(), byteArray_u);
	}

	@Test(dependsOnMethods = { "updateByPreparedStatementCreator" })
	public void deleteByPreparedStatementCreator() {
		Long id = this.entity.getId();
		service.deleteByPreparedStatementCreator(id);
		Assert.assertFalse(service.exists(id));
	}

	private BasicTypeEntity generateBasicTypeEntity() {
		BasicTypeEntity entity = new BasicTypeEntity();
		entity.setId(System.currentTimeMillis());
		entity.setPrimativeBoolean(primativeBoolean);
		entity.setPrimativeByte(primativeByte);
		entity.setPrimativeDouble(primativeDouble);
		entity.setPrimativeFloat(primativeFloat);
		entity.setPrimativeInt(primativeInt);
		entity.setPrimativeLong(primativeLong);
		entity.setPrimativeShort(primativeShort);
		entity.setString(string);
		entity.setBigDecimal(bigDecimal);
		entity.setSqlDate(sqlDate);
		entity.setSqlTime(sqlTime);
		entity.setSqlTimestamp(sqlTimestamp);
		entity.setByteArray(byteArray);
		return entity;
	}

	private BasicTypeEntity generateBasicTypeEntity_u(Long id) {
		BasicTypeEntity entity = new BasicTypeEntity();
		entity.setId(id);
		entity.setPrimativeBoolean(primativeBoolean_u);
		entity.setPrimativeByte(primativeByte_u);
		entity.setPrimativeDouble(primativeDouble_u);
		entity.setPrimativeFloat(primativeFloat_u);
		entity.setPrimativeInt(primativeInt_u);
		entity.setPrimativeLong(primativeLong_u);
		entity.setPrimativeShort(primativeShort_u);
		entity.setString(string_u);
		entity.setBigDecimal(bigDecimal_u);
		entity.setSqlDate(sqlDate_u);
		entity.setSqlTime(sqlTime_u);
		entity.setSqlTimestamp(sqlTimestamp_u);
		entity.setByteArray(byteArray_u);
		return entity;
	}

	private byte[] getByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	private byte[] getByteArray_u() {
		return new byte[] { 0x11, 0x22, 0x33 };
	}
}
