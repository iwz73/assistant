package idv.hsiehpinghan.goraassistant.test;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.service.GoraService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoraServiceTest {
	private ApplicationContext applicationContext;
	private GoraService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(GoraService.class);
	}

	@Test
	public void put() {
		service.put(0L, generateGora());
	}

	private Gora generateGora() {
		Gora entity = new Gora();
		entity.setIp("this is id");
		return entity;
	}
}
