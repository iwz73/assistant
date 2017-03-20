package idv.hsiehpinghan.hibernatesearchormassistant.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedEntity.EmbeddableCollection;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedEntity.EmbeddableObject;
import idv.hsiehpinghan.hibernatesearchormassistant.service.IndexedEmbeddedService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class IndexedEmbeddedEntityTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 3;
	private final String KEY = "key";
	private final String NAME = "name" + System.currentTimeMillis();
	private final Long AGE = System.currentTimeMillis();

	@Autowired
	private IndexedEmbeddedService service;

	@Test
	public void save() throws Exception {
		IndexedEmbeddedEntity entity = generateIndexedEmbeddedEntity();
		service.save(entity);
		Integer id = entity.getId();
		IndexedEmbeddedEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
	}

	@Test(dependsOnMethods = { "save" })
	public void luceneQuery() throws Exception {
		assertEmbeddableObject();
		assertEmbeddableCollections();
		assertMap();
	}
	
	private void assertMap() throws ParseException {
		for (int i = 0; i < SIZE; ++i) {
			String queryString = String.format("+integerStringMap:%s%d", KEY, i);
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser queryParser = new QueryParser(null, analyzer);
			org.apache.lucene.search.Query query = queryParser.parse(queryString);
			List<IndexedEmbeddedEntity> entities = service.luceneQuery(query);
			Assert.assertTrue(entities.size() > 0);
		}
	}

	private void assertEmbeddableCollections() throws ParseException {
		for (int i = 0; i < SIZE; ++i) {
			String queryString = String.format("+embeddableCollections.name:%s%d", NAME, i);
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser queryParser = new QueryParser(null, analyzer);
			org.apache.lucene.search.Query query = queryParser.parse(queryString);
			List<IndexedEmbeddedEntity> entities = service.luceneQuery(query);
			Assert.assertTrue(entities.size() > 0);
		}
	}

	private void assertEmbeddableObject() throws ParseException {
		String queryString = String.format("+embeddableObject.name:%s", NAME);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(null, analyzer);
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<IndexedEmbeddedEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private IndexedEmbeddedEntity generateIndexedEmbeddedEntity() {
		IndexedEmbeddedEntity entity = new IndexedEmbeddedEntity();
		entity.setEmbeddableObject(generateEmbeddableObject());
		entity.setEmbeddableCollections(generateEmbeddableCollections());
		entity.setIntegerStringMap(setIntegerStringMap());
		return entity;
	}

	private Map<Integer, String> setIntegerStringMap() {
		Map<Integer, String> map = new HashMap<>();
		for(int i = 0; i < SIZE; ++i) {
			map.put(i, KEY + i);
		}
		return map;
	}
	private EmbeddableObject generateEmbeddableObject() {
		EmbeddableObject embeddableObject = new EmbeddableObject();
		embeddableObject.setName(NAME);
		embeddableObject.setAge(AGE);
		return embeddableObject;
	}

	private Collection<EmbeddableCollection> generateEmbeddableCollections() {
		Collection<EmbeddableCollection> embeddableCollections = new ArrayList<>();
		for (int i = 0; i < SIZE; ++i) {
			embeddableCollections.add(generateEmbeddableCollection(i));
		}
		return embeddableCollections;
	}

	private EmbeddableCollection generateEmbeddableCollection(int i) {
		EmbeddableCollection embeddableCollection = new EmbeddableCollection();
		embeddableCollection.setName(NAME + i);
		embeddableCollection.setAge(AGE + i);
		return embeddableCollection;
	}
}
