package idv.hsiehpinghan.hibernateassistant.assistant;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;
import idv.hsiehpinghan.hibernateassistant.service.LifeCycleService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class LifeCycleTest extends AbstractTestNGSpringContextTests {
	private final Integer INTEGER = Integer.valueOf(0);
	private final String STRING = "string";
	@Autowired
	private LifeCycleService service;
	private LifeCycleEntity entity;

	@BeforeClass
	public void beforeClass() {
		entity = generateLifeCycleEntity();
	}

	@Test
	public void save() throws Exception {
		service.save(entity);
	}

	@Test(dependsOnMethods = { "save" })
	public void getTwice() throws Exception {
		service.getTwice(entity.getId());
	}

	@Test(dependsOnMethods = { "save" })
	public void update() throws Exception {
		entity.setString("update");
		service.update(entity);
	}

	@Test(dependsOnMethods = { "save" })
	public void updateAndSelect() {
		entity.setString("updateAndSelect");
		service.updateAndSelect(entity);
	}

	@Test(dependsOnMethods = { "updateAndSelect" }, expectedExceptions = { DuplicateKeyException.class })
	public void nonUniqueObjectUpdate() {
		entity.setString("nonUniqueObjectUpdate");
		service.nonUniqueObjectUpdate(entity);
	}

	@Test(dependsOnMethods = { "nonUniqueObjectUpdate" })
	public void merge() {
		entity.setString("merge");
		LifeCycleEntity mergeEntity = service.merge(entity);
		Assert.assertTrue(entity.getString().equals(mergeEntity.getString()));
		Assert.assertFalse(entity == mergeEntity);
	}

	@Test(dependsOnMethods = { "merge" }, expectedExceptions = { LazyInitializationException.class })
	public void load() {
		LifeCycleEntity loadEntity = service.load(entity.getId());
		Assert.assertNotNull(loadEntity);
		loadEntity.getString();
	}

	private LifeCycleEntity generateLifeCycleEntity() {
		LifeCycleEntity entity = new LifeCycleEntity();
		entity.setId(System.nanoTime());
		entity.setInteger(INTEGER);
		entity.setString(STRING);
		return entity;
	}

}
