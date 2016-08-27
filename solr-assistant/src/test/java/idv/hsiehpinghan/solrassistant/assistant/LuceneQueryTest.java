package idv.hsiehpinghan.solrassistant.assistant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.solrassistant.document.DefaultDocument;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class LuceneQueryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void fieldedTermSearchesTest() throws Exception {
		termSearchTest();
		multipleTermSearchTest();
		appearTogetherTermSearchTest();
		
	}
	
	@Test
	public void requiredTermTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("+name:\"ipod\" +id:\"F8V7067-APL-KIT\"");
//		solrQuery.setQuery("name:\"ipod\" && id:\"F8V7067-APL-KIT\"");
		solrQuery.setQuery("name:\"ipod\" AND id:\"F8V7067-APL-KIT\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}
	
	@Test
	public void excludingTermsTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("-name:\"ipod\" -id:\"F8V7067-APL-KIT\"");
		solrQuery.setQuery("name:\"ipod\" AND NOT id:\"F8V7067-APL-KIT\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}
	
	@Test
	public void optionalTermTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("name:\"ipod\" || id:\"F8V7067-APL-KIT\"");
		solrQuery.setQuery("name:\"ipod\" OR id:\"F8V7067-APL-KIT\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 3);
	}
	
	@Test
	public void groupedExpressionTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("(name:\"ipod\" AND id:(\"IW-02\" OR \"F8V7067-APL-KIT\")) AND weight:4");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}
	
	@Test
	public void termProximityTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:\"iPod Cord Dock\"~3");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}
	
	@Test
	public void characterProximityTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:Mobi~2");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}
	
	@Test
	public void rangeSearchesTest() throws Exception {
		numberRangeSearchesTest();
		dateRangeSearchesTest();
		stringRangeSearchesTest();
	}
	
	private void stringRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("manu:[\"Belkin\" TO \"C\"]");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}

	private void dateRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("manufacturedate_dt:[2005-08-01T16:30:25Z TO NOW-1DAY]");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 10);
	}
	
	private void numberRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("weight:[2 TO 4]");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}
	
	private void termSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:\"ipod\" AND id:\"F8V7067-APL-KIT\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	private void multipleTermSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:(\"ipod\" \"GB18030\")");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 4);
	}
	
	private void appearTogetherTermSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:\"for iPod\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}
	
}
