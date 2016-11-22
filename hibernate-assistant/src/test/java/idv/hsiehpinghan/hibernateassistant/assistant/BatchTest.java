package idv.hsiehpinghan.hibernateassistant.assistant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.BatchEntity;
import idv.hsiehpinghan.hibernateassistant.service.BatchService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BatchTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 10;
	private final int BATCH_SIZE = 5;
	private final String STRING = "string";
	private final String UPDATE_STRING = "batchUpdate";
	@Autowired
	private BatchService service;

	@BeforeClass
	public void beforeClass() {
		service.deleteAll();
	}

	@Test
	public void batchSave() {
		int savedAmt = service.batchSave(generateBatchEntities(), BATCH_SIZE);
		Assert.assertEquals(savedAmt, SIZE);
	}

	@Test(dependsOnMethods = { "batchSave" })
	public void batchUpdate() {
		int updatedAmt = service.batchUpdate(UPDATE_STRING, BATCH_SIZE);
		Assert.assertEquals(updatedAmt, SIZE);
	}

	@Test(dependsOnMethods = { "batchUpdate" })
	public void findAll() {
		List<BatchEntity> entities = service.findAll();
		Assert.assertEquals(entities.size(), SIZE);
		entities.forEach((t) -> {
			Assert.assertEquals(t.getString(), UPDATE_STRING);
		});
	}

	private Collection<BatchEntity> generateBatchEntities() {
		Collection<BatchEntity> entities = new ArrayList<BatchEntity>(SIZE);
		long idBase = System.nanoTime();
		for (int i = 0; i < SIZE; ++i) {
			entities.add(generateBatchEntity(idBase, i));
		}
		return entities;
	}

	private BatchEntity generateBatchEntity(long idBase, int i) {
		BatchEntity entity = new BatchEntity();
		entity.setId(idBase + i);
		entity.setString(STRING);
		return entity;
	}

}
