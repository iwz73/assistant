package idv.hsiehpinghan.hibernateassistant.assistant;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.SelectBeforeUpdateEntity;
import idv.hsiehpinghan.hibernateassistant.service.SelectBeforeUpdateService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SelectBeforeUpdateTest extends AbstractTestNGSpringContextTests {
	private final Integer INTEGER = Integer.valueOf(0);
	private final String STRING = "string";
	@Autowired
	private SelectBeforeUpdateService service;
	private SelectBeforeUpdateEntity entity;

	@BeforeClass
	public void beforeClass() {
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
