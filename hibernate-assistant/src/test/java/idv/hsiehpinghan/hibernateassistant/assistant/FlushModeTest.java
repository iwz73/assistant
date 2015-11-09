package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.FlushModeEntity;
import idv.hsiehpinghan.hibernateassistant.service.FlushModeService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlushModeTest {
	private final String STRING = "string";
	private FlushModeService service;
	private FlushModeEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(FlushModeService.class);
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
