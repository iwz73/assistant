package idv.hsiehpinghan.solrassistant.assistant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
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
		facetFieldsTest();
		
	}

	@Test
	public void FacetQueryTest() throws Exception {
		facetSortTest();
		facetLimitTest();
		facetMinCountTest();
	}
	
	private void facetMinCountTest() throws SolrServerException {
		final int MIN_COUNT = 5;
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetField("manu");
		solrQuery.setFacetMinCount(MIN_COUNT);
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetMinCountTest >>");
		for(FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for(Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
				Assert.assertTrue(count.getCount() >= MIN_COUNT);
			}
		}
	}
	
	private void facetLimitTest() throws SolrServerException {
		final int LIMIT = 3;
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetField("manu");
		solrQuery.setFacetLimit(LIMIT);
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetLimitTest >>");
		for(FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for(Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
			}
			Assert.assertEquals(counts.size(), LIMIT);
		}
	}
	
	private void facetSortTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetField("manu");
		solrQuery.setFacetSort("count");	// possible value : count or index
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetSortTest >>");
		for(FacetField facetField : facetFields) {
			long oldCount = Long.MAX_VALUE;
			List<Count> counts = facetField.getValues();
			for(Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
				long newCount = count.getCount();
				Assert.assertTrue(oldCount >= newCount);
				oldCount = newCount;
			}
		}
		Assert.assertTrue(facetFields.size() > 0);		
	}
	
	private void facetFieldsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetField("manu");
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetFieldsTest >>");
		for(FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for(Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
			}
		}
		Assert.assertTrue(facetFields.size() > 0);		
	}
	
}
