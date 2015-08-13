package idv.hsiehpinghan.goraassistant.test;

import idv.hsiehpinghan.goraassistant.entity.ArrayItem;
import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.entity.MapValue;
import idv.hsiehpinghan.goraassistant.entity.NestedRecord;
import idv.hsiehpinghan.goraassistant.enumeration.Enumeration;
import idv.hsiehpinghan.goraassistant.service.GoraService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoraServiceTest {
	private final long KEY = Calendar.getInstance().getTimeInMillis();
	private boolean _boolean = true;
	private int _int = 1;
	private long _long = 2;
	private float _float = 1.1f;
	private double _double = 2.2;
	private CharSequence _string = "string";
	private boolean _record_boolean = true;
	private int _record_int = 1;
	private String _arrayItem_name = "arrayItemName";
	private int mapValue_age = 3;
	private Enumeration _enum = Enumeration.ENUM_1;
	private String _unions = "unions";
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
		Assert.assertNotNull(returnGora);
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
		entity.setArray$1(generateArrayItems());
		entity.setMap$1(generateMap());
		entity.setUnions$1(_unions);
		return entity;
	}
	
	private Map<CharSequence, MapValue> generateMap() {
		Map<CharSequence, MapValue> map = new HashMap<CharSequence, MapValue>();
		String[] keys = {"a", "b", "c"};
		for(String key : keys) {
			map.put(key, generateMapValue(key));
		}
		return map;
	}
	
	private MapValue generateMapValue(String name) {
		MapValue entity = new MapValue();
		entity.setName(name);
		entity.setAge(mapValue_age);
		return entity;
	}
	
	private NestedRecord generateNestedRecord() {
		NestedRecord entity = new NestedRecord();
		entity.setBoolean$1(_record_boolean);
		entity.setInt$1(_record_int);
		return entity;
	}
	
	private List<ArrayItem> generateArrayItems() {
		List<ArrayItem> entities = new ArrayList<ArrayItem>();
		for(long i = 0; i < 3; ++i) {
			entities.add(generateArrayItem(i));
		}
		return entities;
	}
	
	private ArrayItem generateArrayItem(long i) {
		ArrayItem entity = new ArrayItem();
		entity.setId(i);
		entity.setName(_arrayItem_name);
		return entity;
	}
}
