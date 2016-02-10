package idv.hsiehpinghan.springjdbcassistant.test;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springjdbcassistant.service.BasicTypeService;
import idv.hsiehpinghan.springjdbcassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	private Reader clobReader;
	private InputStream blobInputStream;
	private byte[] byteArray = getByteArray();

	private ApplicationContext applicationContext;
	private InputStream clobIs;
	private long clobContentLength;
	private InputStreamReader clobIsr;
	private InputStream blobIs;
	private long blobContentLength;
	private BasicTypeService service;
	private BasicTypeEntity entity;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		clobReader = generateClobReader();
		blobInputStream = generateBlobInputStream();
		service = applicationContext.getBean(BasicTypeService.class);
	}

	@Test
	public void insertByAbstractLobCreatingPreparedStatementCallback()
			throws Exception {
		entity = generateBasicTypeEntity();
		int result = service
				.insertByAbstractLobCreatingPreparedStatementCallback(entity,
						clobContentLength, blobContentLength);
		Assert.assertEquals(result, 1);
	}

	// @Test
	// public void insertByPreparedStatement() {
	// entity = generateBasicTypeEntity();
	// int result = service.insertByPreparedStatement(entity);
	// Assert.assertEquals(result, 1);
	// }
	//
	// @Test
	// public void queryForObjectByRowMapper() throws Exception {
	// BasicTypeEntity entity = service.queryForObjectByRowMapper(this.entity
	// .getId());
	// Assert.assertEquals(entity.isPrimativeBoolean(), primativeBoolean);
	// Assert.assertEquals(String.valueOf(entity.getPrimativeByte()),
	// String.valueOf(primativeByte));
	// Assert.assertEquals(entity.getPrimativeDouble(), primativeDouble);
	// Assert.assertEquals(entity.getPrimativeFloat(), primativeFloat);
	// Assert.assertEquals(entity.getPrimativeInt(), primativeInt);
	// Assert.assertEquals(entity.getPrimativeLong(), primativeLong);
	// Assert.assertEquals(entity.getPrimativeShort(), primativeShort);
	// Assert.assertEquals(entity.getString(), string);
	// Assert.assertEquals(entity.getBigDecimal().doubleValue(),
	// bigDecimal.doubleValue());
	// Assert.assertEquals(
	// DateFormatUtils.format(entity.getSqlDate(), "yyyy/MM/dd"),
	// DateFormatUtils.format(sqlDate.getTime(), "yyyy/MM/dd"));
	// Assert.assertEquals(
	// DateFormatUtils.format(entity.getSqlTime(), "hh:mm:ss"),
	// DateFormatUtils.format(sqlTime, "hh:mm:ss"));
	// Assert.assertEquals(entity.getSqlTimestamp(), sqlTimestamp);
	// Assert.assertEquals(entity.getClob(), null);
	// Assert.assertEquals(entity.getBlob(), null);
	// Assert.assertEquals(entity.getByteArray(), byteArray);
	// }

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
		entity.setClobReader(clobReader);
		entity.setBlobInputStream(blobInputStream);
		entity.setByteArray(byteArray);
		return entity;
	}

	private Reader generateClobReader() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/file/file.xml");
		clobIs = resource.getInputStream();
		clobContentLength = resource.contentLength();
		clobIsr = new InputStreamReader(clobIs);
		return clobIsr;
	}

	private InputStream generateBlobInputStream() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/file/image.png");
		blobIs = resource.getInputStream();
		blobContentLength = resource.contentLength();
		return blobIs;
	}

	// private char[] getCharArray() {
	// return new char[] { 'a', 'b', 'c' };
	// }

	private byte[] getByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	@AfterClass
	public void afterClass() throws Exception {
		if (clobIsr != null) {
			clobIsr.close();
		}
		if (clobIs != null) {
			clobIs.close();
		}
		if (blobInputStream != null) {
			blobInputStream.close();
		}
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
