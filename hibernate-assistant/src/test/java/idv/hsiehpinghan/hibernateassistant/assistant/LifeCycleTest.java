package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;
import idv.hsiehpinghan.hibernateassistant.service.LifeCycleService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LifeCycleTest {
	private final Integer INTEGER = Integer.valueOf(0);
	private final String STRING = "string";
	private LifeCycleService service;
	private LifeCycleEntity entity;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(LifeCycleService.class);
		entity = generateLifeCycleEntity();
	}

	@Test
	public void save() throws Exception {
		service.save(entity);
	}

	@Test(dependsOnMethods = { "save" })
	public void getTwice() throws Exception {
		service.getTwice(entity.getId());
	}

	@Test(dependsOnMethods = { "save" })
	public void update() throws Exception {
		entity.setString("update");
		service.update(entity);
	}

	@Test(dependsOnMethods = { "save" })
	public void updateAndSelect() throws Exception {
		entity.setString("updateAndSelect");
		service.updateAndSelect(entity);
	}
	
	private LifeCycleEntity generateLifeCycleEntity() {
		LifeCycleEntity entity = new LifeCycleEntity();
		entity.setId(System.nanoTime());
		entity.setInteger(INTEGER);
		entity.setString(STRING);
		return entity;
	}

}
