package idv.hsiehpinghan.goraassistant.test;

import java.util.Calendar;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.service.GoraService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;

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
		return entity;
	}
}
