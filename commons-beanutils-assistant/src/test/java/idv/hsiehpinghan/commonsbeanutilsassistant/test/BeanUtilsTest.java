package idv.hsiehpinghan.commonsbeanutilsassistant.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.commonsbeanutilsassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.commonsbeanutilsassistant.enumeration.Enumeration;
import idv.hsiehpinghan.commonsbeanutilsassistant.vo.BasicTypeExtensionVo;
import idv.hsiehpinghan.commonsbeanutilsassistant.vo.BasicTypeVo;
import idv.hsiehpinghan.commonsbeanutilsassistant.vo.BasicTypeVo.SubBasicType;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BeanUtilsTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 3;
	private final boolean PRIMATIVE_BOOLEAN = true;
	private final Boolean WRAPPED_BOOLEAN = true;
	private final byte PRIMATIVE_BYTE = 0x1;
	private final Byte WRAPPED_BYTE = 0x1;
	private final char PRIMATIVE_CHAR = 'a';
	private final Character WRAPPED_CHAR = 'a';
	private final double PRIMATIVE_DOUBLE = 1.1;
	private final Double WRAPPED_DOUBLE = 1.1;
	private final float PRIMATIVE_FLOAT = 1.1f;
	private final Float WRAPPED_FLOAT = 1.1f;
	private final int PRIMATIVE_INT = 1;
	private final Integer WRAPPED_INT = 1;
	private final long PRIMATIVE_LONG = 1L;
	private final Long WRAPPED_LONG = 1L;
	private final short PRIMATIVE_SHORT = 1;
	private final Short WRAPPED_SHORT = 1;
	private final String STRING = "string";
	private final BigInteger BIG_INTEGER = BigInteger.ONE;
	private final BigDecimal BIG_DECIMAL = BigDecimal.ONE;
	private final Locale LOCALE = Locale.CHINESE;
	private final TimeZone TIME_ZONE = TimeZone.getDefault();
	private final Currency CURRENCY = Currency.getInstance(Locale.US);
	private final Class<BasicTypeVo> CLAZZ = BasicTypeVo.class;
	private final Serializable SERIALIZABLE = new BasicTypeVo();
	private final Date DATE = Calendar.getInstance().getTime();
	private final Calendar CALENDAR = Calendar.getInstance();
	private final java.sql.Date SQL_DATE = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	private final java.sql.Time SQL_TIME = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
	private final java.sql.Timestamp SQL_TIMESTAMP = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	private final byte[] BYTE_ARRAY = getByteArray();
	private final char[] CHAR_ARRAY = getCharArray();
	private final Enumeration ENUMERATION = Enumeration.ENUM_2;
	private final List<String> STRING_LIST = Arrays.asList("string_0", "string_1", "string_2");
	private final Set<String> STRING_SET = getStringSet();
	private final Map<String, String> STRING_MAP = generateStringMap();
	private final SubBasicType SUB_BASIC_TYPE = generateSubBasicType();
	private final List<SubBasicType> SUB_BASIC_TYPE_LIST = generateSubBasicTypeList();
	private final Set<SubBasicType> SUB_BASIC_TYPE_SET = generateSubBasicTypeSet();
	private final Map<SubBasicType, SubBasicType> SUB_BASIC_TYPE_MAP = generateSubBasicTypeMap();
	private final BasicTypeVo BASIC_TYPE_VO = generateBasicTypeVo();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void copyProperties() throws Exception {
		BasicTypeExtensionVo vo = new BasicTypeExtensionVo();
		BeanUtils.copyProperties(vo, BASIC_TYPE_VO);
		assertBasicTypeExtensionVo(vo);
		// System.err.println(objectMapper.writeValueAsString(vo));
	}

	private void assertBasicTypeExtensionVo(BasicTypeExtensionVo vo) {
		Assert.assertEquals(vo.isPrimativeBoolean(), PRIMATIVE_BOOLEAN);
		Assert.assertEquals(vo.getWrappedBoolean(), WRAPPED_BOOLEAN);
		Assert.assertEquals(vo.getPrimativeByte(), PRIMATIVE_BYTE);
		Assert.assertEquals(vo.getWrappedByte(), WRAPPED_BYTE);
		Assert.assertEquals(vo.getPrimativeChar(), PRIMATIVE_CHAR);
		Assert.assertEquals(vo.getWrappedChar(), WRAPPED_CHAR);
		Assert.assertEquals(vo.getPrimativeDouble(), PRIMATIVE_DOUBLE);
		Assert.assertEquals(vo.getWrappedDouble(), WRAPPED_DOUBLE);
		Assert.assertEquals(vo.getPrimativeFloat(), PRIMATIVE_FLOAT);
		Assert.assertEquals(vo.getWrappedFloat(), WRAPPED_FLOAT);
		Assert.assertEquals(vo.getPrimativeInt(), PRIMATIVE_INT);
		Assert.assertEquals(vo.getWrappedInt(), WRAPPED_INT);
		Assert.assertEquals(vo.getPrimativeLong(), PRIMATIVE_LONG);
		Assert.assertEquals(vo.getWrappedLong(), WRAPPED_LONG);
		Assert.assertEquals(vo.getPrimativeShort(), PRIMATIVE_SHORT);
		Assert.assertEquals(vo.getWrappedShort(), WRAPPED_SHORT);
		Assert.assertEquals(vo.getString(), STRING);
		Assert.assertEquals(vo.getBigInteger(), BIG_INTEGER);
		Assert.assertEquals(vo.getBigDecimal(), BIG_DECIMAL);
		Assert.assertEquals(vo.getLocale(), LOCALE);
		Assert.assertEquals(vo.getTimeZone(), TIME_ZONE);
		Assert.assertEquals(vo.getCurrency(), CURRENCY);
		Assert.assertEquals(vo.getClazz(), CLAZZ);
		Assert.assertEquals(vo.getSerializable(), SERIALIZABLE);
		Assert.assertEquals(vo.getDate(), DATE);
		Assert.assertEquals(vo.getCalendar().getTimeInMillis(), CALENDAR.getTimeInMillis());
		Assert.assertEquals(vo.getSqlDate(), SQL_DATE);
		Assert.assertEquals(vo.getSqlTime(), SQL_TIME);
		Assert.assertEquals(vo.getSqlTimestamp(), SQL_TIMESTAMP);
		Assert.assertEquals(vo.getByteArray(), BYTE_ARRAY);
		Assert.assertEquals(vo.getCharArray(), CHAR_ARRAY);
		Assert.assertEquals(vo.getEnumeration(), ENUMERATION);
		assertSubBasicType(vo.getSubBasicType(), 0);
		assertSubBasicTypeList(vo.getSubBasicTypeList());
		assertSubBasicTypeSet(vo.getSubBasicTypeSet());
		assertSubBasicTypeMap(vo.getSubBasicTypeMap());
	}

	private void assertSubBasicTypeList(List<SubBasicType> subBasicTypes) {
		Assert.assertEquals(subBasicTypes.size(), SIZE);
		for (int i = 0; i < SIZE; ++i) {
			SubBasicType subBasicType = subBasicTypes.get(i);
			assertSubBasicType(subBasicType, i);
		}
	}

	private void assertSubBasicTypeSet(Set<SubBasicType> subBasicTypes) {
		Assert.assertEquals(subBasicTypes.size(), SIZE);
		SubBasicType[] subBasicTypeArr = subBasicTypes.toArray(new SubBasicType[0]);
		for (int i = 0; i < SIZE; ++i) {
			SubBasicType subBasicType = subBasicTypeArr[i];
			assertSubBasicType(subBasicType, i);
		}
	}

	private void assertSubBasicTypeMap(Map<SubBasicType, SubBasicType> subBasicTypeMap) {
		Assert.assertEquals(subBasicTypeMap.size(), SIZE);
		int i = 0;
		for (Map.Entry<SubBasicType, SubBasicType> ent : subBasicTypeMap.entrySet()) {
			assertSubBasicType(ent.getKey(), i);
			assertSubBasicType(ent.getValue(), i);
			++i;
		}
	}

	private void assertSubBasicType(SubBasicType subBasicType, int i) {
		Assert.assertEquals(subBasicType.isPrimativeBoolean(), PRIMATIVE_BOOLEAN);
		Assert.assertEquals(subBasicType.getWrappedBoolean(), WRAPPED_BOOLEAN);
		Assert.assertEquals(subBasicType.getPrimativeByte(), PRIMATIVE_BYTE);
		Assert.assertEquals(subBasicType.getWrappedByte(), WRAPPED_BYTE);
		Assert.assertEquals(subBasicType.getPrimativeChar(), PRIMATIVE_CHAR);
		Assert.assertEquals(subBasicType.getWrappedChar(), WRAPPED_CHAR);
		Assert.assertEquals(subBasicType.getPrimativeDouble(), PRIMATIVE_DOUBLE);
		Assert.assertEquals(subBasicType.getWrappedDouble(), WRAPPED_DOUBLE);
		Assert.assertEquals(subBasicType.getPrimativeFloat(), PRIMATIVE_FLOAT);
		Assert.assertEquals(subBasicType.getWrappedFloat(), WRAPPED_FLOAT);
		Assert.assertEquals(subBasicType.getPrimativeInt(), PRIMATIVE_INT + i);
		Assert.assertEquals(subBasicType.getWrappedInt(), WRAPPED_INT);
		Assert.assertEquals(subBasicType.getPrimativeLong(), PRIMATIVE_LONG);
		Assert.assertEquals(subBasicType.getWrappedLong(), WRAPPED_LONG);
		Assert.assertEquals(subBasicType.getPrimativeShort(), PRIMATIVE_SHORT);
		Assert.assertEquals(subBasicType.getWrappedShort(), WRAPPED_SHORT);
		Assert.assertEquals(subBasicType.getString(), STRING);
		Assert.assertEquals(subBasicType.getBigInteger(), BIG_INTEGER);
		Assert.assertEquals(subBasicType.getBigDecimal(), BIG_DECIMAL);
		Assert.assertEquals(subBasicType.getLocale(), LOCALE);
		Assert.assertEquals(subBasicType.getTimeZone(), TIME_ZONE);
		Assert.assertEquals(subBasicType.getCurrency(), CURRENCY);
		Assert.assertEquals(subBasicType.getClazz(), CLAZZ);
		Assert.assertEquals(subBasicType.getSerializable(), SERIALIZABLE);
		Assert.assertEquals(subBasicType.getDate(), DATE);
		Assert.assertEquals(subBasicType.getCalendar(), CALENDAR);
		Assert.assertEquals(subBasicType.getSqlDate(), SQL_DATE);
		Assert.assertEquals(subBasicType.getSqlTime(), SQL_TIME);
		Assert.assertEquals(subBasicType.getSqlTimestamp(), SQL_TIMESTAMP);
		Assert.assertEquals(subBasicType.getByteArray(), BYTE_ARRAY);
		Assert.assertEquals(subBasicType.getCharArray(), CHAR_ARRAY);
		Assert.assertEquals(subBasicType.getEnumeration(), ENUMERATION);
		Assert.assertEquals(subBasicType.getStringList().toString(), STRING_LIST.toString());
		Assert.assertEquals(subBasicType.getStringSet().toString(), STRING_SET.toString());
		Assert.assertEquals(subBasicType.getStringMap().toString(), STRING_MAP.toString());
	}

	private BasicTypeVo generateBasicTypeVo() {
		return new BasicTypeVo(PRIMATIVE_BOOLEAN, WRAPPED_BOOLEAN, PRIMATIVE_BYTE, WRAPPED_BYTE, PRIMATIVE_CHAR,
				WRAPPED_CHAR, PRIMATIVE_DOUBLE, WRAPPED_DOUBLE, PRIMATIVE_FLOAT, WRAPPED_FLOAT, PRIMATIVE_INT,
				WRAPPED_INT, PRIMATIVE_LONG, WRAPPED_LONG, PRIMATIVE_SHORT, WRAPPED_SHORT, STRING, BIG_INTEGER,
				BIG_DECIMAL, LOCALE, TIME_ZONE, CURRENCY, CLAZZ, SERIALIZABLE, DATE, CALENDAR, SQL_DATE, SQL_TIME,
				SQL_TIMESTAMP, BYTE_ARRAY, CHAR_ARRAY, ENUMERATION, SUB_BASIC_TYPE, SUB_BASIC_TYPE_LIST,
				SUB_BASIC_TYPE_SET, SUB_BASIC_TYPE_MAP);
	}

	private SubBasicType generateSubBasicType() {
		return new SubBasicType(PRIMATIVE_BOOLEAN, WRAPPED_BOOLEAN, PRIMATIVE_BYTE, WRAPPED_BYTE, PRIMATIVE_CHAR,
				WRAPPED_CHAR, PRIMATIVE_DOUBLE, WRAPPED_DOUBLE, PRIMATIVE_FLOAT, WRAPPED_FLOAT, PRIMATIVE_INT,
				WRAPPED_INT, PRIMATIVE_LONG, WRAPPED_LONG, PRIMATIVE_SHORT, WRAPPED_SHORT, STRING, BIG_INTEGER,
				BIG_DECIMAL, LOCALE, TIME_ZONE, CURRENCY, CLAZZ, SERIALIZABLE, DATE, CALENDAR, SQL_DATE, SQL_TIME,
				SQL_TIMESTAMP, BYTE_ARRAY, CHAR_ARRAY, ENUMERATION, STRING_LIST, STRING_SET, STRING_MAP);
	}

	private char[] getCharArray() {
		return new char[] { 'a', 'b', 'c' };
	}

	private byte[] getByteArray() {
		return new byte[] { 0x78, 0x79, 0x7a };
	}

	private Set<String> getStringSet() {
		Set<String> stringSet = new HashSet<>();
		stringSet.add("string_0");
		stringSet.add("string_1");
		stringSet.add("string_2");
		return stringSet;
	}

	private Map<String, String> generateStringMap() {
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key_0", "value_0");
		stringMap.put("key_1", "value_1");
		stringMap.put("key_2", "value_2");
		return stringMap;
	}

	private List<SubBasicType> generateSubBasicTypeList() {
		List<SubBasicType> subBasicTypeList = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			int primativeInt = PRIMATIVE_INT + i;
			SubBasicType subBasicType = new SubBasicType(PRIMATIVE_BOOLEAN, WRAPPED_BOOLEAN, PRIMATIVE_BYTE,
					WRAPPED_BYTE, PRIMATIVE_CHAR, WRAPPED_CHAR, PRIMATIVE_DOUBLE, WRAPPED_DOUBLE, PRIMATIVE_FLOAT,
					WRAPPED_FLOAT, primativeInt, WRAPPED_INT, PRIMATIVE_LONG, WRAPPED_LONG, PRIMATIVE_SHORT,
					WRAPPED_SHORT, STRING, BIG_INTEGER, BIG_DECIMAL, LOCALE, TIME_ZONE, CURRENCY, CLAZZ, SERIALIZABLE,
					DATE, CALENDAR, SQL_DATE, SQL_TIME, SQL_TIMESTAMP, BYTE_ARRAY, CHAR_ARRAY, ENUMERATION, STRING_LIST,
					STRING_SET, STRING_MAP);
			subBasicTypeList.add(subBasicType);
		}
		return subBasicTypeList;
	}

	private Set<SubBasicType> generateSubBasicTypeSet() {
		Set<SubBasicType> subBasicTypeSet = new TreeSet<>();
		for (int i = 0; i < SIZE; ++i) {
			int primativeInt = PRIMATIVE_INT + i;
			SubBasicType subBasicType = new SubBasicType(PRIMATIVE_BOOLEAN, WRAPPED_BOOLEAN, PRIMATIVE_BYTE,
					WRAPPED_BYTE, PRIMATIVE_CHAR, WRAPPED_CHAR, PRIMATIVE_DOUBLE, WRAPPED_DOUBLE, PRIMATIVE_FLOAT,
					WRAPPED_FLOAT, primativeInt, WRAPPED_INT, PRIMATIVE_LONG, WRAPPED_LONG, PRIMATIVE_SHORT,
					WRAPPED_SHORT, STRING, BIG_INTEGER, BIG_DECIMAL, LOCALE, TIME_ZONE, CURRENCY, CLAZZ, SERIALIZABLE,
					DATE, CALENDAR, SQL_DATE, SQL_TIME, SQL_TIMESTAMP, BYTE_ARRAY, CHAR_ARRAY, ENUMERATION, STRING_LIST,
					STRING_SET, STRING_MAP);
			subBasicTypeSet.add(subBasicType);
		}
		return subBasicTypeSet;
	}

	private Map<SubBasicType, SubBasicType> generateSubBasicTypeMap() {
		Map<SubBasicType, SubBasicType> subBasicTypeMap = new LinkedHashMap<>();
		for (int i = 0; i < SIZE; ++i) {
			int primativeInt = PRIMATIVE_INT + i;
			SubBasicType subBasicType = new SubBasicType(PRIMATIVE_BOOLEAN, WRAPPED_BOOLEAN, PRIMATIVE_BYTE,
					WRAPPED_BYTE, PRIMATIVE_CHAR, WRAPPED_CHAR, PRIMATIVE_DOUBLE, WRAPPED_DOUBLE, PRIMATIVE_FLOAT,
					WRAPPED_FLOAT, primativeInt, WRAPPED_INT, PRIMATIVE_LONG, WRAPPED_LONG, PRIMATIVE_SHORT,
					WRAPPED_SHORT, STRING, BIG_INTEGER, BIG_DECIMAL, LOCALE, TIME_ZONE, CURRENCY, CLAZZ, SERIALIZABLE,
					DATE, CALENDAR, SQL_DATE, SQL_TIME, SQL_TIMESTAMP, BYTE_ARRAY, CHAR_ARRAY, ENUMERATION, STRING_LIST,
					STRING_SET, STRING_MAP);
			subBasicTypeMap.put(subBasicType, subBasicType);
		}
		return subBasicTypeMap;
	}
}
