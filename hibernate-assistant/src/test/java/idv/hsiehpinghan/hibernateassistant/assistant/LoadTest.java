package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.LoadManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.LoadOneEntity;
import idv.hsiehpinghan.hibernateassistant.service.LoadService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoadTest {
	private final int SIZE = 3;
	private final String STRING = "string";
	private LoadService service;
	private LoadOneEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(LoadService.class);
		entity = generateLoadOneEntity();
		service.save(entity);
	}

	@Test
	public void findLoadManyEntities() {
		Collection<LoadManyEntity> loadManyEntities = service
				.findLoadManyEntities(entity.getId());
		Assert.assertEquals(loadManyEntities.size(), SIZE);
	}

	private LoadOneEntity generateLoadOneEntity() {
		LoadOneEntity entity = new LoadOneEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		Collection<LoadManyEntity> many = new HashSet<LoadManyEntity>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			many.add(generateLoadManyEntity(nanoTime, i));
		}
		entity.setMany(many);
		return entity;
	}

	private LoadManyEntity generateLoadManyEntity(long nanoTime, int i) {
		LoadManyEntity entity = new LoadManyEntity();
		entity.setId(nanoTime + i);
		entity.setString(STRING + "_" + i);
		return entity;
	}
}
