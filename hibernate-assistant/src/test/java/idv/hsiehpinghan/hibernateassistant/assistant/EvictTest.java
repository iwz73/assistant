package idv.hsiehpinghan.hibernateassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.EvictEntity;
import idv.hsiehpinghan.hibernateassistant.service.EvictService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class EvictTest extends AbstractTestNGSpringContextTests {
	private final String STRING = "string";
	@Autowired
	private EvictService service;
	private EvictEntity entity;

	@BeforeClass
	public void beforeClass() {
		entity = generateEvictEntity();
		service.save(entity);
	}

	@Test
	public void evictAndGet() {
		service.evictAndGet(entity.getId());
		Assert.assertEquals(service.getStatistics().getEntityUpdateCount(), 0);
		Assert.assertEquals(service.getStatistics().getEntityLoadCount(), 2);
	}

	private EvictEntity generateEvictEntity() {
		EvictEntity entity = new EvictEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		return entity;
	}

}
