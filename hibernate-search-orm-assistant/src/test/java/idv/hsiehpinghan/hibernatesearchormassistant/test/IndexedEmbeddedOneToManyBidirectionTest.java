package idv.hsiehpinghan.hibernatesearchormassistant.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedOneToManyBidirectionManyEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedOneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.service.IndexedEmbeddedOneToManyBidirectionService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class IndexedEmbeddedOneToManyBidirectionTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 3;
	private final String STRING_VALUE = "stringValue";

	@Autowired
	private IndexedEmbeddedOneToManyBidirectionService service;

	@Test
	public void save() throws Exception {
		IndexedEmbeddedOneToManyBidirectionOneEntity entity = generateIndexedEmbeddedOneToManyBidirectionOneEntity();
		service.save(entity);
		int id = entity.getId();
		IndexedEmbeddedOneToManyBidirectionOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), SIZE);
	}

	@Test(dependsOnMethods = { "save" })
	public void luceneQuery() throws Exception {
		for (int i = 0; i < SIZE; ++i) {
			String queryString = String.format("+many.stringValue:%s", STRING_VALUE + i);
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser queryParser = new QueryParser(null, analyzer);
			org.apache.lucene.search.Query query = queryParser.parse(queryString);
			List<IndexedEmbeddedOneToManyBidirectionOneEntity> entities = service.luceneQuery(query);
			Assert.assertTrue(entities.size() > 0);
		}
	}

	private IndexedEmbeddedOneToManyBidirectionOneEntity generateIndexedEmbeddedOneToManyBidirectionOneEntity() {
		IndexedEmbeddedOneToManyBidirectionOneEntity one = new IndexedEmbeddedOneToManyBidirectionOneEntity();
		one.setMany(generateIndexedEmbeddedOneToManyBidirectionManyEntities(one));
		one.setStringValue(STRING_VALUE);
		return one;
	}

	private Collection<IndexedEmbeddedOneToManyBidirectionManyEntity> generateIndexedEmbeddedOneToManyBidirectionManyEntities(
			IndexedEmbeddedOneToManyBidirectionOneEntity one) {
		Collection<IndexedEmbeddedOneToManyBidirectionManyEntity> entities = new ArrayList<IndexedEmbeddedOneToManyBidirectionManyEntity>();
		for (int i = 0; i < SIZE; ++i) {
			entities.add(generateIndexedEmbeddedOneToManyBidirectionManyEntity(one, i));
		}
		return entities;
	}

	private IndexedEmbeddedOneToManyBidirectionManyEntity generateIndexedEmbeddedOneToManyBidirectionManyEntity(
			IndexedEmbeddedOneToManyBidirectionOneEntity one, int i) {
		IndexedEmbeddedOneToManyBidirectionManyEntity many = new IndexedEmbeddedOneToManyBidirectionManyEntity();
		many.setOne(one);
		many.setStringValue(STRING_VALUE + i);
		return many;
	}
}
