package idv.hsiehpinghan.hibernatesearchormassistant.test;

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
import idv.hsiehpinghan.hibernatesearchormassistant.entity.AnalyzerEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.service.AnalyzerService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class AnalyzerTest extends AbstractTestNGSpringContextTests {
	private final String ENGLISH_STRING = "this is a ENGLISH string";

	@Autowired
	private AnalyzerService service;

	@Test
	public void save() throws Exception {
		AnalyzerEntity entity = generateAnalyzerEntity();
		service.save(entity);
		Integer id = entity.getId();
		AnalyzerEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
	}

	@Test(dependsOnMethods = { "save" })
	public void luceneQuery() throws Exception {
		String queryString = String.format("+englishString:%s", "english");
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(null, analyzer);
		org.apache.lucene.search.Query query = queryParser.parse(queryString);
		List<AnalyzerEntity> entities = service.luceneQuery(query);
		Assert.assertTrue(entities.size() > 0);
	}

	private AnalyzerEntity generateAnalyzerEntity() {
		AnalyzerEntity entity = new AnalyzerEntity();
		entity.setEnglishString(ENGLISH_STRING);
		return entity;
	}

}
