package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.CascadeEntity;
import idv.hsiehpinghan.hibernateassistant.entity.CascadeMergeEntity;
import idv.hsiehpinghan.hibernateassistant.entity.CascadePersistEntity;
import idv.hsiehpinghan.hibernateassistant.service.CascadeService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CascadeTest {
	private final int SIZE = 3;
	private long idBase = System.nanoTime();
	private final String STRING = "string";
	private CascadeService service;
	private CascadeEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(CascadeService.class);
	}

	@Test
	public void persist() {
		entity = generateCascadeEntity();
		service.persist(entity);
		CascadeEntity returnEntity = service.get(entity.getId());
		Assert.assertEquals(returnEntity.getCascadePersistEntities().size(),
				SIZE);
		Assert.assertEquals(returnEntity.getCascadeMergeEntities().size(), SIZE);
	}

	@Test(dependsOnMethods = { "persist" })
	public void merge() {
		entity.setCascadeMergeEntities(generateCascadeMergeEntities(10));
		service.merge(entity);
		CascadeEntity returnEntity = service.get(entity.getId());
		Assert.assertEquals(returnEntity.getCascadePersistEntities().size(),
				SIZE);
		Assert.assertEquals(returnEntity.getCascadeMergeEntities().size(),
				SIZE + 10);
	}

	private CascadeEntity generateCascadeEntity() {
		CascadeEntity entity = new CascadeEntity();
		entity.setId(idBase);
		entity.setCascadePersistEntities(generateCascadePersistEntities());
		entity.setCascadeMergeEntities(generateCascadeMergeEntities(0));
		return entity;
	}

	private Collection<CascadePersistEntity> generateCascadePersistEntities() {
		Collection<CascadePersistEntity> cascadePersistEntities = new HashSet<CascadePersistEntity>(
				SIZE);
		for (long i = 0; i < SIZE; ++i) {
			cascadePersistEntities.add(generateCascadePersistEntity(i));
		}
		return cascadePersistEntities;
	}

	private Collection<CascadeMergeEntity> generateCascadeMergeEntities(int base) {
		Collection<CascadeMergeEntity> cascadeMergeEntities = new HashSet<CascadeMergeEntity>(
				SIZE);
		for (long i = 0, size = SIZE + base; i < size; ++i) {
			cascadeMergeEntities.add(generateCascadeMergeEntity(i));
		}
		return cascadeMergeEntities;
	}

	private CascadePersistEntity generateCascadePersistEntity(long i) {
		CascadePersistEntity cascadePersistEntity = new CascadePersistEntity();
		cascadePersistEntity.setId(idBase + i);
		cascadePersistEntity.setString(STRING + i);
		return cascadePersistEntity;
	}

	private CascadeMergeEntity generateCascadeMergeEntity(long i) {
		CascadeMergeEntity cascadeMergeEntity = new CascadeMergeEntity();
		cascadeMergeEntity.setId(idBase + i);
		cascadeMergeEntity.setString(STRING + i);
		return cascadeMergeEntity;
	}
}
