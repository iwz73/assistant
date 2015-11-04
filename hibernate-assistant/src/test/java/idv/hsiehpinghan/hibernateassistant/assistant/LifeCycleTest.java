package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;
import idv.hsiehpinghan.hibernateassistant.service.LifeCycleService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LifeCycleTest {
	private final String STRING = "string";
	private LifeCycleService service;
	private LifeCycleEntity entity;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(LifeCycleService.class);
		entity = generateLifeCycleEntity();
		service.save(entity);
	}

	@Test
	public void getTwice() throws Exception {
		service.getTwice(entity.getId());
	}

	private LifeCycleEntity generateLifeCycleEntity() {
		LifeCycleEntity entity = new LifeCycleEntity();
		entity.setId(System.nanoTime());
		entity.setString(STRING);
		return entity;
	}

}
