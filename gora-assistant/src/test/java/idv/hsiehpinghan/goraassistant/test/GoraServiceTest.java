package idv.hsiehpinghan.goraassistant.test;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.entity.NestedRecord;
import idv.hsiehpinghan.goraassistant.enumeration.Enumeration;
import idv.hsiehpinghan.goraassistant.service.GoraService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;

import java.util.Calendar;

import org.apache.avro.util.Utf8;
import org.apache.gora.query.Result;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoraServiceTest {
	private final long KEY = Calendar.getInstance().getTimeInMillis();
	private boolean _boolean = true;
	private int _int = 1;
	private long _long = 2;
	private float _float = 1.1f;
	private double _double = 2.2;
	private CharSequence _string = new Utf8("string");
	private boolean _record_boolean = true;
	private int _record_int = 1;
	private Enumeration _enum = Enumeration.ENUM_1;
	private ApplicationContext applicationContext;
	private GoraService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(GoraService.class);
	}

	@Test
	public void put() {
		service.put(KEY, generateGora());
	}

	@Test(dependsOnMethods = { "put" })
	public void get() {
		Gora returnGora = service.get(KEY);
		assertReturnGora(returnGora);
	}

	/**
	 * test query(Long key)
	 * @throws Exception
	 */
	@Test(dependsOnMethods = { "get" })
	public void query() throws Exception {
		Result<Long, Gora> result = service.query(KEY);
		while (result.next()) {
			Gora returnGora = result.get();
			assertReturnGora(returnGora);
		}
	}

	/**
	 * test query(Long key, long limit)
	 * @throws Exception
	 */
	@Test(dependsOnMethods = { "query" })
	public void queryWithLimit() throws Exception {
		final long SIZE = 3;
		Long lastValue = null;
		for(long i = 0; i < SIZE; ++i) {
			lastValue = Long.MAX_VALUE - i;
			service.put(lastValue , generateGora());			
		}
		Result<Long, Gora> result = service.query(lastValue, Long.MAX_VALUE);
		int amt = 0;
		while (result.next()) {
			++amt;
			Gora returnGora = result.get();
			assertReturnGora(returnGora);
			service.delete(result.getKey());
		}
		Assert.assertEquals(amt, SIZE);
		
	}
	
	@Test(dependsOnMethods = { "query" })
	public void delete() {
		Assert.assertTrue(service.delete(KEY));
		Assert.assertNull(service.get(KEY));
	}

	private void assertReturnGora(Gora returnGora) {
		Assert.assertEquals(Boolean.valueOf(_boolean),
				returnGora.getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_int), returnGora.getInt$1());
		Assert.assertEquals(Long.valueOf(_long), returnGora.getLong$1());
		Assert.assertEquals(Float.valueOf(_float), returnGora.getFloat$1());
		Assert.assertEquals(Double.valueOf(_double), returnGora.getDouble$1());
		Assert.assertEquals(_string, returnGora.getString$1());
		Assert.assertEquals(Boolean.valueOf(_record_boolean), returnGora
				.getRecord$1().getBoolean$1());
		Assert.assertEquals(Integer.valueOf(_record_int), returnGora
				.getRecord$1().getInt$1());
		Assert.assertEquals(_enum, returnGora.getEnum$1());
	}

	private Gora generateGora() {
		Gora entity = new Gora();
		entity.setBoolean$1(_boolean);
		entity.setInt$1(_int);
		entity.setLong$1(_long);
		entity.setFloat$1(_float);
		entity.setDouble$1(_double);
		entity.setString$1(_string);
		entity.setRecord$1(generateNestedRecord());
		entity.setEnum$1(_enum);
		return entity;
	}

	private NestedRecord generateNestedRecord() {
		NestedRecord entity = new NestedRecord();
		entity.setBoolean$1(_record_boolean);
		entity.setInt$1(_record_int);
		return entity;
	}

}
