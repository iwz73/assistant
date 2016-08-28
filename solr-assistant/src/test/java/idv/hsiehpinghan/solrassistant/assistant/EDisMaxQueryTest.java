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
public class EDisMaxQueryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void fieldedTermSearchTest() throws Exception {
		queryFieldsTest();
		boostsQueryFieldsTest();
	}
	
	@Test
	public void boostQueryTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax bq=\"manufacturedate_dt:[2005-01-01T00:00:00Z TO 2006-01-01T00:00:00Z]\"}\"electronics\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.get(0).getFieldValue("id"), "F8V7067-APL-KIT");
	}

	@Test
	public void fieldAliasingTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax f.alias_0.qf=\"name features^10\"}alias_0:\"ipod\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 3);
	}
	
	@Test
	public void userAccessibleFieldTest() throws Exception {
		restrictAvailableFieldsTest();
		disableFieldsTest();
	}
		
	
	
	private void restrictAvailableFieldsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax uf=\"cat manu\"}name:ipod");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 0);
	}
	
	private void disableFieldsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax uf=\"-name -cat\"}name:ipod");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 0);
	}
	
	private void queryFieldsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax qf=\"name manu\"}\"with\" OR \"here\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 3);
	}
	
	private void boostsQueryFieldsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!edismax qf=\"name cat includes^10 features\"}\"card\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.get(0).getFieldValue("id"), "9885A004");
	}
	
}
