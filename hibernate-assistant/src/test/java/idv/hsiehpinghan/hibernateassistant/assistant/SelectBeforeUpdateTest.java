package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.SelectBeforeUpdateEntity;
import idv.hsiehpinghan.hibernateassistant.service.SelectBeforeUpdateService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.hibernate.stat.Statistics;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectBeforeUpdateTest {
	private final Integer INTEGER = Integer.valueOf(0);
	private final String STRING = "string";
	private SelectBeforeUpdateService service;
	private SelectBeforeUpdateEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(SelectBeforeUpdateService.class);
		entity = generateSelectBeforeUpdateEntity();
		service.save(entity);
	}

	@Test
	public void selectBeforeUpdateNoChange() {
		service.selectBeforeUpdateNoChange(entity);
		Assert.assertEquals(service.getStatistics().getEntityUpdateCount(), 0);
	}

	@Test(dependsOnMethods = { "selectBeforeUpdateNoChange" })
	public void selectBeforeUpdateWithChange() {
		entity.setString("selectBeforeUpdateWithChange");
		service.selectBeforeUpdateWithChange(entity);
		Statistics statistics = service.getStatistics();
		Assert.assertEquals(statistics.getEntityUpdateCount(), 1);
	}

	private SelectBeforeUpdateEntity generateSelectBeforeUpdateEntity() {
		SelectBeforeUpdateEntity entity = new SelectBeforeUpdateEntity();
		entity.setId(System.nanoTime());
		entity.setInteger(INTEGER);
		entity.setString(STRING);
		return entity;
	}

}
