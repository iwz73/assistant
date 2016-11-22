package idv.hsiehpinghan.hibernateassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.ClearEntity;
import idv.hsiehpinghan.hibernateassistant.service.ClearService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ClearTest extends AbstractTestNGSpringContextTests {
	private final String STRING = "string";
	@Autowired
	private ClearService service;
	private ClearEntity entity;

	@BeforeClass
	public void beforeClass() {
		entity = generateClearEntity();
		service.save(entity);
	}

	@Test
	public void clearAndGet() {
		service.clearAndGet(entity.getId());
		Assert.assertEquals(service.getStatistics().getEntityUpdateCount(), 0);
		Assert.assertEquals(service.getStatistics().getEntityLoadCount(), 2);
	}

	private ClearEntity generateClearEntity() {
		ClearEntity entity = new ClearEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		return entity;
	}

}
