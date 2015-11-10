package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.LockManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.LockOneEntity;
import idv.hsiehpinghan.hibernateassistant.service.LockService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LockTest {
	private final String STRING = "string";
	private LockService service;
	private LockOneEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(LockService.class);
		entity = generateLockOneEntity();
	}

	@Test
	public void save() throws Exception {
		service.save(entity);
	}

	@Test(dependsOnMethods = { "save" })
	public void lockModeNone() throws Exception {
		LockOneEntity returnEntity = service.findOne(entity.getId());
		returnEntity.setString("lockModeNone");
		service.lockModeNone(returnEntity);
		Assert.assertEquals(service.getStatistics().getEntityUpdateCount(), 0);
	}

	@Test(dependsOnMethods = { "lockModeNone" }, expectedExceptions = { HibernateOptimisticLockingFailureException.class })
	public void lockModeRead() throws Exception {
		LockOneEntity returnEntity = service.findOne(entity.getId());
		returnEntity.setVersion(returnEntity.getVersion() - 1);
		service.lockModeRead(returnEntity);
	}

	private LockOneEntity generateLockOneEntity() {
		final int SIZE = 3;
		LockOneEntity entity = new LockOneEntity();
		long nanoTime = System.nanoTime();
		entity.setId(nanoTime);
		entity.setString(STRING);
		Collection<LockManyEntity> many = new HashSet<LockManyEntity>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			many.add(generateLockManyEntity(nanoTime, i));
		}
		entity.setMany(many);
		return entity;
	}

	private LockManyEntity generateLockManyEntity(long nanoTime, int i) {
		LockManyEntity entity = new LockManyEntity();
		entity.setId(nanoTime + i);
		entity.setString(STRING + "_" + i);
		return entity;
	}
}
