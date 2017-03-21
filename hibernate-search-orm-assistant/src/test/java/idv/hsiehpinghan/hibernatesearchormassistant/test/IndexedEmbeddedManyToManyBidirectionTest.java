package idv.hsiehpinghan.hibernatesearchormassistant.test;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.service.IndexedEmbeddedManyToManyBidirectionService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class IndexedEmbeddedManyToManyBidirectionTest extends AbstractTestNGSpringContextTests {
	private final String STRING_VALUE = "stringValue";

	@Autowired
	private IndexedEmbeddedManyToManyBidirectionService service;

	@Test
	public void save() throws Exception {
		Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> entities = generateIndexedEmbeddedManyToManyBidirectionFromEntities();
		for (IndexedEmbeddedManyToManyBidirectionFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (IndexedEmbeddedManyToManyBidirectionFromEntity entity : entities) {
			int id = entity.getId();
			IndexedEmbeddedManyToManyBidirectionFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (IndexedEmbeddedManyToManyBidirectionToEntity to : fromEntity.getTos()) {
				Assert.assertEquals(to.getFroms().size(), 3);
			}
		}
	}

	private Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> generateIndexedEmbeddedManyToManyBidirectionFromEntities() {
		Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> entities = new ArrayList<IndexedEmbeddedManyToManyBidirectionFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateIndexedEmbeddedManyToManyBidirectionFromEntity(i));
		}
		return entities;
	}

	private IndexedEmbeddedManyToManyBidirectionFromEntity generateIndexedEmbeddedManyToManyBidirectionFromEntity(
			int i) {
		IndexedEmbeddedManyToManyBidirectionFromEntity from = new IndexedEmbeddedManyToManyBidirectionFromEntity();
		from.setId(i);
		from.setStringValue(STRING_VALUE + i);
		from.setTos(generateIndexedEmbeddedManyToManyBidirectionToEntities(from));
		return from;
	}

	private Collection<IndexedEmbeddedManyToManyBidirectionToEntity> generateIndexedEmbeddedManyToManyBidirectionToEntities(
			IndexedEmbeddedManyToManyBidirectionFromEntity from) {
		Collection<IndexedEmbeddedManyToManyBidirectionToEntity> entities = new ArrayList<IndexedEmbeddedManyToManyBidirectionToEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateIndexedEmbeddedManyToManyBidirectionToEntity(from, i));
		}
		return entities;
	}

	private IndexedEmbeddedManyToManyBidirectionToEntity generateIndexedEmbeddedManyToManyBidirectionToEntity(
			IndexedEmbeddedManyToManyBidirectionFromEntity from, int i) {
		IndexedEmbeddedManyToManyBidirectionToEntity to = new IndexedEmbeddedManyToManyBidirectionToEntity();
		to.setId(i);
		from.setStringValue(STRING_VALUE + i);
		to.addFrom(from);
		return to;
	}
}
