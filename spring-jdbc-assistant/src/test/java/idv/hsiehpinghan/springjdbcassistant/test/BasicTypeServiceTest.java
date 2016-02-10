package idv.hsiehpinghan.springjdbcassistant.test;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springjdbcassistant.service.BasicTypeService;
import idv.hsiehpinghan.springjdbcassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.streamutility.utility.InputStreamUtility;
import idv.hsiehpinghan.streamutility.utility.ReaderUtility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTypeServiceTest {
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
	private char[] clobCharArray;
	private byte[] blobByteArray;
	private byte[] byteArray = getByteArray();

	private ApplicationContext applicationContext;
	private BasicTypeService service;
	private BasicTypeEntity entity;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(BasicTypeService.class);
		clobCharArray = generateClobCharArray();
		blobByteArray = generateBlobByteArray();
	}

	@Test
	public void insertByPreparedStatement() {
		entity = generateBasicTypeEntity();
		int result = service.insertByPreparedStatement(entity);
		Assert.assertEquals(result, 1);
	}

	@Test(dependsOnMethods = { "insertByPreparedStatement" })
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
		Assert.assertEquals(String.valueOf(entity.getClobCharArray()),
				String.valueOf(clobCharArray));
		Assert.assertEquals(String.valueOf(entity.getBlobByteArray()),
				String.valueOf(blobByteArray));
		Assert.assertEquals(entity.getByteArray(), byteArray);
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
		entity.setClobCharArray(clobCharArray);
		entity.setBlobByteArray(blobByteArray);
		entity.setByteArray(byteArray);
		return entity;
	}

	private char[] generateClobCharArray() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/file/file.xml");
		InputStream inputStream = resource.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		int length = (int) resource.contentLength();
		return ReaderUtility.readAsCharArray(inputStreamReader, length);
	}

	private byte[] generateBlobByteArray() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/file/image.png");
		InputStream inputStream = resource.getInputStream();
		int length = (int) resource.contentLength();
		return InputStreamUtility.readAsByteArray(inputStream, length);
	}

	private byte[] getByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	//
	// private String convertToString(java.sql.Clob clob) throws Exception {
	// Reader reader = clob.getCharacterStream();
	// return ReaderUtility.readAsString(reader);
	// }
	//
	// private String convertToString(java.sql.Blob blob) throws SQLException,
	// IOException {
	// InputStream inputStream = blob.getBinaryStream();
	// return InputStreamUtility.readAsString(inputStream);
	// }

}
