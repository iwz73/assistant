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
public class FacetTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void fieldFacetingTest() throws Exception {
//		SolrQuery solrQuery = new SolrQuery();
//		solrQuery.setQuery("*:*");
//		solrQuery.setFacet(true);
//		solrQuery.addfa
//		QueryResponse response = solrAssistant.query(solrQuery);
//		SolrDocumentList solrDocumentList = response.getResults();
//		Assert.assertEquals(solrDocumentList.get(0).getFieldValue("id"), "F8V7067-APL-KIT");
	}

}
