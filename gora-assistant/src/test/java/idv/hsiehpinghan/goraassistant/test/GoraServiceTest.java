package idv.hsiehpinghan.goraassistant.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.util.Utf8;
import org.apache.gora.filter.FilterOp;
import org.apache.gora.filter.SingleFieldValueFilter;
import org.apache.gora.util.GoraException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.goraassistant.entity.ArrayItem;
import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.entity.NestedRecord;
import idv.hsiehpinghan.goraassistant.enumeration.Enumeration;
import idv.hsiehpinghan.goraassistant.service.GoraService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.goraassistant.vo.ArrayItemVo;
import idv.hsiehpinghan.goraassistant.vo.GoraVo;

public class GoraServiceTest {
	private final Utf8 TEST = new Utf8("test");
	private final String KEY = String.valueOf(Calendar.getInstance().getTimeInMillis());
	private ByteBuffer _bytes = ByteBuffer.wrap(new byte[] { 'a', 'b', 'c' });
	private boolean _boolean = true;
	private int _int = 1;
	private long _long = 2;
	private float _float = 1.1f;
	private double _double = 2.2;
	private CharSequence _string = new Utf8("string");
	private boolean _record_boolean = true;
	private int _record_int = 1;
	private Enumeration _enum = Enumeration.ENUM_1;
	private List<CharSequence> _strings = generate_Strings();
	private List<ArrayItem> _array = generate_Array();
	private Map<CharSequence, CharSequence> _map = generate_Map();
	private ApplicationContext applicationContext;
	private GoraService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(GoraService.class);
	}

	@AfterClass
	public void afterClass() {
		((AnnotationConfigApplicationContext)applicationContext).close();
	}
	
	@Test
	public void put() throws GoraException {
		service.put(KEY, generateGora());
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws GoraException {
		GoraVo returnGora = service.get(KEY);
		assertReturnGora(returnGora);
	}

	@Test(dependsOnMethods = { "put" })
	public void exists() throws IOException, Exception {
		Assert.assertFalse(service.exist("NotExists"));
		Assert.assertTrue(service.exist(KEY));
	}

	/**
	 * test query(Long key)
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods = { "get" })
	public void query() throws Exception {
		Map<String, GoraVo> result = service.query(KEY);
		for (Map.Entry<String, GoraVo> ent : result.entrySet()) {
			GoraVo returnGora = ent.getValue();
			assertReturnGora(returnGora);
		}
	}

	/**
	 * test query(Long key, String... fields)
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods = { "query" })
	public void queryWithFields() throws Exception {
		Gora gora = generateGora();
		gora.setString$1(TEST);
		service.put(KEY, gora);
		testStringField(TEST);
		testNoStringField(TEST);
	}

	/**
	 * test query(Long key, long limit)
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods = { "query" })
	public void queryWithLimit() throws Exception {
		final long SIZE = 3;
		String lastValue = null;
		for (long i = 0; i < SIZE; ++i) {
			lastValue = String.valueOf(Long.MAX_VALUE - i);
			service.put(lastValue, generateGora());
		}
		int amt = 0;
		Map<String, GoraVo> result = service.query(lastValue, Long.MAX_VALUE);
		for (Map.Entry<String, GoraVo> ent : result.entrySet()) {
			GoraVo returnGora = ent.getValue();
			assertReturnGora(returnGora);
			service.delete(ent.getKey());
			++amt;
		}
		Assert.assertEquals(amt, SIZE);
	}

	@Test(dependsOnMethods = { "queryWithLimit" })
	public void queryWithFilter() throws Exception {
		SingleFieldValueFilter<String, Gora> filter = new SingleFieldValueFilter<>();
		filter.setFieldName(Gora.Field._STRING.toString());
		filter.setFilterOp(FilterOp.EQUALS);
		filter.setOperands(Arrays.asList(TEST));
		Map<String, GoraVo> result = service.query(filter);
		Assert.assertTrue(result.size() > 0);
	}

	@Test(dependsOnMethods = { "queryWithFilter" })
	public void putWithOnlyOneField() throws GoraException {
		final String STRING = "new_string";
		service.put(KEY, generateGora1(STRING));
		GoraVo returnGora = service.get(KEY);
		assertReturnGora1(returnGora, STRING);
	}

	@Test(dependsOnMethods = { "putWithOnlyOneField" })
	public void delete() throws GoraException {
		Assert.assertTrue(service.delete(KEY));
		Assert.assertNull(service.get(KEY));
	}

	private void assertReturnGora(GoraVo returnGora) {
		Assert.assertEquals(_bytes, returnGora.getBytes$1());
		Assert.assertEquals(Boolean.valueOf(_boolean), returnGora.getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_int), returnGora.getInt$1());
		Assert.assertEquals(Long.valueOf(_long), returnGora.getLong$1());
		Assert.assertEquals(Float.valueOf(_float), returnGora.getFloat$1());
		Assert.assertEquals(Double.valueOf(_double), returnGora.getDouble$1());
		Assert.assertEquals(_string, returnGora.getString$1());
		Assert.assertEquals(Boolean.valueOf(_record_boolean), returnGora.getRecord$1().getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_record_int), returnGora.getRecord$1().getInt$1());
		Assert.assertEquals(_enum, returnGora.getEnum$1());
		assertStrings(returnGora.getStrings$1());
		assertArrayItem(returnGora.getArray$1());
		assertMap(returnGora.getMap$1());
	}

	private void assertStrings(List<CharSequence> strings) {
		for (int i = 0, size = strings.size(); i < size; ++i) {
			CharSequence string = strings.get(i);
			Assert.assertEquals(String.valueOf(string), "string_" + i);
		}
	}

	private void assertArrayItem(List<ArrayItemVo> arrayItems) {
		for (int i = 0, size = arrayItems.size(); i < size; ++i) {
			ArrayItemVo arrayItem = arrayItems.get(i);
			Assert.assertEquals(arrayItem.getId().longValue(), i);
			Assert.assertEquals(String.valueOf(arrayItem.getName()), "name_" + i);
		}
	}

	private void assertMap(Map<CharSequence, CharSequence> map) {
		Map<String, String> strMap = convertCharSequenceMapToStringMap(map);
		Map<String, String> m = convertCharSequenceMapToStringMap(_map);
		boolean tested = false;
		for (Map.Entry<String, String> ent : strMap.entrySet()) {
			String key = ent.getKey();
			String value = ent.getValue();
			Assert.assertEquals(value, m.get(key));
			tested = true;
		}
		Assert.assertTrue(tested);
	}

	private Map<String, String> convertCharSequenceMapToStringMap(Map<CharSequence, CharSequence> map) {
		Map<String, String> strMap = new HashMap<>();
		for (Map.Entry<CharSequence, CharSequence> ent : map.entrySet()) {
			String key = String.valueOf(ent.getKey());
			String value = String.valueOf(ent.getValue());
			strMap.put(key, value);
		}
		return strMap;
	}

	private void assertReturnGora1(GoraVo returnGora, String string) {
		Assert.assertEquals(_bytes, returnGora.getBytes$1());
		Assert.assertEquals(Boolean.valueOf(_boolean), returnGora.getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_int), returnGora.getInt$1());
		Assert.assertEquals(Long.valueOf(_long), returnGora.getLong$1());
		Assert.assertEquals(Float.valueOf(_float), returnGora.getFloat$1());
		Assert.assertEquals(Double.valueOf(_double), returnGora.getDouble$1());
		Assert.assertEquals(string, String.valueOf(returnGora.getString$1()));
		Assert.assertEquals(Boolean.valueOf(_record_boolean), returnGora.getRecord$1().getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_record_int), returnGora.getRecord$1().getInt$1());
		Assert.assertEquals(_enum, returnGora.getEnum$1());
		assertArrayItem(returnGora.getArray$1());
		assertMap(returnGora.getMap$1());
	}

	private Gora generateGora() {
		Gora entity = Gora.newBuilder().build();
		entity.setBytes$1(_bytes);
		entity.setBoolean$1(_boolean);
		entity.setInt$1(_int);
		entity.setLong$1(_long);
		entity.setFloat$1(_float);
		entity.setDouble$1(_double);
		entity.setString$1(_string);
		entity.setRecord$1(generateNestedRecord());
		entity.setEnum$1(_enum);
		entity.setStrings$1(_strings);
		entity.setArray$1(_array);
		entity.setMap$1(_map);
		return entity;
	}

	private Gora generateGora1(String string) {
		Gora entity = new Gora();
		entity.setString$1(string);
		return entity;
	}

	private NestedRecord generateNestedRecord() {
		NestedRecord entity = new NestedRecord();
		entity.setBoolean$1(_record_boolean);
		entity.setInt$1(_record_int);
		return entity;
	}

	private void testStringField(CharSequence testStr) throws Exception {
		String[] fields = new String[] { Gora.Field._STRING.getName() };
		int amt = 0;
		Map<String, GoraVo> result = service.query(KEY, fields);
		for (Map.Entry<String, GoraVo> ent : result.entrySet()) {
			GoraVo returnGora = ent.getValue();
			Assert.assertEquals(returnGora.getString$1(), testStr);
			++amt;
		}
		Assert.assertEquals(amt, 1);
	}

	private void testNoStringField(CharSequence testStr) throws Exception {
		String[] fields = new String[] { Gora.Field._BOOLEAN.getName() };
		int amt = 0;
		Map<String, GoraVo> result = service.query(KEY, fields);
		for (Map.Entry<String, GoraVo> ent : result.entrySet()) {
			GoraVo returnGora = ent.getValue();
			Assert.assertNotEquals(returnGora.getString$1(), testStr);
			++amt;
		}
		Assert.assertEquals(amt, 1);
	}

	private List<ArrayItem> generate_Array() {
		List<ArrayItem> arrayItems = new ArrayList<ArrayItem>();
		for (long i = 0; i < 3; ++i) {
			arrayItems.add(generateArrayItem(i));
		}
		return arrayItems;
	}

	private List<CharSequence> generate_Strings() {
		List<CharSequence> strings = new ArrayList<>();
		for (long i = 0; i < 3; ++i) {
			strings.add("string_" + i);
		}
		return strings;
	}

	private Map<CharSequence, CharSequence> generate_Map() {
		Map<CharSequence, CharSequence> map = new HashMap<CharSequence, CharSequence>();
		for (int i = 0; i < 3; ++i) {
			map.put("key_" + i, "value_" + i);
		}
		return map;
	}

	private ArrayItem generateArrayItem(Long id) {
		ArrayItem arrayItem = new ArrayItem();
		arrayItem.setId(id);
		arrayItem.setName("name_" + id);
		return arrayItem;
	}

}
