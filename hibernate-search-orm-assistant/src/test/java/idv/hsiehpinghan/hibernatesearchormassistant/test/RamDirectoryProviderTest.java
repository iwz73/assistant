package idv.hsiehpinghan.hibernatesearchormassistant.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.TermsQuery;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.RamDirectoryProviderEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hibernatesearchormassistant.service.RamDirectoryProviderService;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.InputStreamUtility;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.ReaderUtility;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class RamDirectoryProviderTest extends AbstractTestNGSpringContextTests {
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
	private String string = "this is a lucene string";
	private String lobString = "lobString";
	private BigInteger bigInteger = BigInteger.ONE;
	private BigDecimal bigDecimal = BigDecimal.ONE;
	private Locale locale = Locale.CHINESE;
	private TimeZone timeZone = TimeZone.getDefault();
	private Currency currency = Currency.getInstance(Locale.US);
	private Class<RamDirectoryProviderEntity> clazz = RamDirectoryProviderEntity.class;
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
	private URL url = getUrl();
	private URI uri = getUri();
	private String englishString_0 = "this is a lucene string_0";
	private String englishString_1 = "this is a lucene string_1";
	private Date yearResolutionDate = Calendar.getInstance().getTime();
	private Date monthResolutionDate = Calendar.getInstance().getTime();
	private Date dayResolutionDate = Calendar.getInstance().getTime();
	private Date hourResolutionDate = Calendar.getInstance().getTime();
	private Date minuteResolutionDate = Calendar.getInstance().getTime();
	private Date secondResolutionDate = Calendar.getInstance().getTime();
	private Date millisecondResolutionDate = Calendar.getInstance().getTime();
	private String noAnalyzeString = "no analyze string";
	private String multiAnalyzeString = "multi analyze string";

	private int id;
	@Autowired
	private RamDirectoryProviderService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		clob = new SerialClob(getCharArray());
		blob = new SerialBlob(getByteArray());
	}

	@Test
	public void save() throws Exception {
		RamDirectoryProviderEntity entity = generateRamDirectoryProviderEntity();
		service.save(entity);
		id = entity.getId();
		RamDirectoryProviderEntity returnEntity = service.findOne(id);
		assertRamDirectoryProviderEntity(returnEntity);
	}

	@Test(dependsOnMethods = { "save" })
	public void saveOrUpdate() throws Exception {
		RamDirectoryProviderEntity entity = generateRamDirectoryProviderEntity();
		service.saveOrUpdate(entity);
		id = entity.getId();
		String queryString = "+string:lucene +id:" + id;
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(RamDirectoryProviderEntity.DEFAULT_FIELD, analyzer);
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<RamDirectoryProviderEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	@Test(dependsOnMethods = { "saveOrUpdate" })
	public void reindexAll() {
		int result = service.reindexAll();
		Assert.assertTrue(result > 0);
	}

	@Test(dependsOnMethods = { "reindexAll" })
	public void luceneQuery() throws Exception {
		testQueryParser();
		testMultiFieldQueryParser();
		testNoAnalyzeString();
		testMultiAnalyzeString();
	}

	@Test(dependsOnMethods = { "luceneQuery" })
	public void remove() throws Exception {
		RamDirectoryProviderEntity entity = generateRamDirectoryProviderEntity();
		entity.setId(id);
		service.remove(entity);
		String queryString = "+string:lucene +id:" + id;
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(RamDirectoryProviderEntity.DEFAULT_FIELD, analyzer);
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<RamDirectoryProviderEntity> entities = service.luceneQuery(query);
		Assert.assertEquals(entities.size(), 0);
	}

	private void testQueryParser() throws ParseException {
		String queryString = "string:lucene";
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(RamDirectoryProviderEntity.DEFAULT_FIELD, analyzer);
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<RamDirectoryProviderEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private void testMultiFieldQueryParser() throws ParseException {
		String[] fields = { "englishString_0", "englishString_1" };
		Analyzer analyzer = new StandardAnalyzer();
		Map<String, Float> boosts = generateBoostMap(fields);
		QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer, boosts);
		String queryString = "lucene";
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<RamDirectoryProviderEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private void testNoAnalyzeString() throws ParseException {
		org.apache.lucene.search.Query query = new TermsQuery(new Term("noAnalyzeString", noAnalyzeString));
		List<RamDirectoryProviderEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private void testMultiAnalyzeString() throws ParseException {
		org.apache.lucene.search.Query query = null;
		List<RamDirectoryProviderEntity> entities = null;
		String queryString = "multiAnalyzeStringYes:multi";
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(RamDirectoryProviderEntity.DEFAULT_FIELD, analyzer);
		query = queryParser.parse(queryString);
		entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
		query = new TermsQuery(new Term("multiAnalyzeStringNo", multiAnalyzeString));
		entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private Map<String, Float> generateBoostMap(String[] fields) {
		Map<String, Float> map = new HashMap<>();
		for (int i = 0, size = fields.length; i < size; ++i) {
			map.put(fields[i], (float) (i + 1));
		}
		return map;
	}

	private void assertRamDirectoryProviderEntity(RamDirectoryProviderEntity returnEntity)
			throws SQLException, IOException, Exception {
		int id = returnEntity.getId();
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
		Assert.assertTrue(returnEntity.getDate().getTime() - date.getTime() < 1000);
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getDateDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(dateDate, "yyyy/MM/dd"));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getTimeDate(), "hh:mm:ss"),
				DateFormatUtils.format(timeDate, "hh:mm:ss"));
		Assert.assertTrue(returnEntity.getTimestampDate().getTime() - timestampDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getCalendar().getTimeInMillis() - calendar.getTimeInMillis() < 1000);
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getDateCalendar(), "yyyy/MM/dd"),
				DateFormatUtils.format(dateCalendar, "yyyy/MM/dd"));
		Assert.assertTrue(
				returnEntity.getTimestampCalendar().getTimeInMillis() - timestampCalendar.getTimeInMillis() < 1000);
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getSqlDate(), "yyyy/MM/dd"),
				DateFormatUtils.format(sqlDate.getTime(), "yyyy/MM/dd"));
		Assert.assertEquals(DateFormatUtils.format(returnEntity.getSqlTime(), "hh:mm:ss"),
				DateFormatUtils.format(sqlTime, "hh:mm:ss"));
		Assert.assertTrue(returnEntity.getSqlTimestamp().getTime() - sqlTimestamp.getTime() < 1000);
		Assert.assertEquals(service.findClobAsString(id), convertToString(clob));
		Assert.assertEquals(service.findBlobAsString(id), convertToString(blob));
		Assert.assertEquals(returnEntity.getByteArray(), byteArray);
		Assert.assertEquals(returnEntity.getLobByteArray(), lobByteArray);
		Assert.assertEquals(returnEntity.getCharArray(), charArray);
		Assert.assertEquals(returnEntity.getLobCharArray(), lobCharArray);
		Assert.assertEquals(returnEntity.getStringEnumeration(), stringEnumeration);
		Assert.assertEquals(returnEntity.getOrdinalEnumeration(), ordinalEnumeration);
		Assert.assertEquals(returnEntity.getUrl(), url);
		Assert.assertEquals(returnEntity.getUri(), uri);
		Assert.assertEquals(returnEntity.getEnglishString_0(), englishString_0);
		Assert.assertEquals(returnEntity.getEnglishString_1(), englishString_1);
		Assert.assertTrue(returnEntity.getYearResolutionDate().getTime() - yearResolutionDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getMonthResolutionDate().getTime() - monthResolutionDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getDayResolutionDate().getTime() - dayResolutionDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getHourResolutionDate().getTime() - hourResolutionDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getMinuteResolutionDate().getTime() - minuteResolutionDate.getTime() < 1000);
		Assert.assertTrue(returnEntity.getSecondResolutionDate().getTime() - secondResolutionDate.getTime() < 1000);
		Assert.assertTrue(
				returnEntity.getMillisecondResolutionDate().getTime() - millisecondResolutionDate.getTime() < 1000);
		Assert.assertEquals(returnEntity.getNoAnalyzeString(), noAnalyzeString);
		Assert.assertEquals(returnEntity.getMultiAnalyzeString(), multiAnalyzeString);
	}

	private RamDirectoryProviderEntity generateRamDirectoryProviderEntity() {
		RamDirectoryProviderEntity entity = new RamDirectoryProviderEntity();
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
		entity.setUrl(url);
		entity.setUri(uri);
		entity.setEnglishString_0(englishString_0);
		entity.setEnglishString_1(englishString_1);
		entity.setYearResolutionDate(yearResolutionDate);
		entity.setMonthResolutionDate(monthResolutionDate);
		entity.setDayResolutionDate(dayResolutionDate);
		entity.setHourResolutionDate(hourResolutionDate);
		entity.setMinuteResolutionDate(minuteResolutionDate);
		entity.setSecondResolutionDate(secondResolutionDate);
		entity.setMillisecondResolutionDate(millisecondResolutionDate);
		entity.setNoAnalyzeString(noAnalyzeString);
		entity.setMultiAnalyzeString(multiAnalyzeString);
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

	private URL getUrl() {
		try {
			return new URL("http://www.google.com/");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private URI getUri() {
		try {
			return new URI("http://www.google.com/");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
