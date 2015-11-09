package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.EvictEntity;
import idv.hsiehpinghan.hibernateassistant.service.EvictService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EvictTest {
	private final String STRING = "string";
	private EvictService service;
	private EvictEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(EvictService.class);
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
