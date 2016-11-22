package idv.hsiehpinghan.hibernateassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.FlushModeEntity;
import idv.hsiehpinghan.hibernateassistant.service.FlushModeService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class FlushModeTest extends AbstractTestNGSpringContextTests {
	private final String STRING = "string";
	@Autowired
	private FlushModeService service;
	private FlushModeEntity entity;

	@BeforeClass
	public void beforeClass() {
		entity = generateFlushModeEntity();
		service.save(entity);
	}

	@Test
	public void flushModeAuto() {
		service.flushModeAuto(entity.getId());
		Assert.assertEquals(service.getStatistics().getFlushCount(), 2);
	}

	@Test
	public void flushModeManual() {
		service.flushModeManual(entity.getId());
		Assert.assertEquals(service.getStatistics().getFlushCount(), 1);
	}

	private FlushModeEntity generateFlushModeEntity() {
		FlushModeEntity entity = new FlushModeEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		return entity;
	}

}
