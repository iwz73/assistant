package idv.hsiehpinghan.hibernateassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.ReadOnlyEntity;
import idv.hsiehpinghan.hibernateassistant.service.ReadOnlyService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ReadOnlyTest extends AbstractTestNGSpringContextTests {
	private final String STRING = "string";
	@Autowired
	private ReadOnlyService service;
	private ReadOnlyEntity entity;

	@BeforeClass
	public void beforeClass() {
		entity = generateReadOnlyEntity();
		service.save(entity);
	}

	@Test
	public void clearAndGet() {
		service.setReadOnlyAndGet(entity.getId());
		Assert.assertEquals(service.getStatistics().getEntityUpdateCount(), 0);
		Assert.assertEquals(service.getStatistics().getEntityLoadCount(), 1);
	}

	private ReadOnlyEntity generateReadOnlyEntity() {
		ReadOnlyEntity entity = new ReadOnlyEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		return entity;
	}

}
