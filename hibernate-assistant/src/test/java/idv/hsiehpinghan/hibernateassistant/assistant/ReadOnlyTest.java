package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.ReadOnlyEntity;
import idv.hsiehpinghan.hibernateassistant.service.ReadOnlyService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReadOnlyTest {
	private final String STRING = "string";
	private ReadOnlyService service;
	private ReadOnlyEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(ReadOnlyService.class);
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
