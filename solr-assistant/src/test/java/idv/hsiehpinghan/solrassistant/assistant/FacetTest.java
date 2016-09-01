package idv.hsiehpinghan.solrassistant.assistant;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class FacetTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void fieldFacetingTest() throws Exception {
		facetFieldsTest();
		fqTest();
	}

	@Test
	public void FacetQueryTest() throws Exception {
		facetSortTest();
		facetLimitTest();
		facetMinCountTest();
		facetPrefixTest();
		facetQueryTest();
		multiFacetQueryTest();
		renameFacetTest();
	}

	private void renameFacetTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetQuery("{!key=\"range_0\"}manufacturedate_dt:[2005-01-01T00:00:00Z TO 2006-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("{!key=\"range_1\"}manufacturedate_dt:[2006-01-01T00:00:00Z TO 2007-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("{!key=\"range_2\"}manufacturedate_dt:[2007-01-01T00:00:00Z TO *]");
		solrQuery.setFilterQueries("name:ipod");
		QueryResponse response = solrAssistant.query(solrQuery);
		Map<String, Integer> facetQueryMap = response.getFacetQuery();
		System.err.println("<< renameFacetTest >>");
		for (Map.Entry<String, Integer> ent : facetQueryMap.entrySet()) {
			System.err.println(ent.getKey() + " : " + ent.getValue());
		}
		Assert.assertTrue(facetQueryMap.size() > 0);
	}
	
	private void fqTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetQuery("manufacturedate_dt:[2005-01-01T00:00:00Z TO 2006-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("manufacturedate_dt:[2006-01-01T00:00:00Z TO 2007-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("manufacturedate_dt:[2007-01-01T00:00:00Z TO *]");
		solrQuery.setFilterQueries("name:ipod");
		QueryResponse response = solrAssistant.query(solrQuery);
		Map<String, Integer> facetQueryMap = response.getFacetQuery();
		System.err.println("<< fqTest >>");
		for (Map.Entry<String, Integer> ent : facetQueryMap.entrySet()) {
			System.err.println(ent.getKey() + " : " + ent.getValue());
		}
		Assert.assertTrue(facetQueryMap.size() > 0);
	}
	
	private void multiFacetQueryTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetQuery("manufacturedate_dt:[2005-01-01T00:00:00Z TO 2006-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("manufacturedate_dt:[2006-01-01T00:00:00Z TO 2007-01-01T00:00:00Z}");
		solrQuery.addFacetQuery("manufacturedate_dt:[2007-01-01T00:00:00Z TO *]");
		solrQuery.addFacetQuery("popularity:[0 TO 5}");
		solrQuery.addFacetQuery("popularity:[5 TO 10}");
		solrQuery.addFacetQuery("popularity:[10 TO *]");
		QueryResponse response = solrAssistant.query(solrQuery);
		Map<String, Integer> facetQueryMap = response.getFacetQuery();
		System.err.println("<< multiFacetQueryTest >>");
		for (Map.Entry<String, Integer> ent : facetQueryMap.entrySet()) {
			System.err.println(ent.getKey() + " : " + ent.getValue());
		}
		Assert.assertTrue(facetQueryMap.size() > 0);
	}

	private void facetQueryTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.addFacetQuery("name:ipod AND manu:Belkin");
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetPrefixTest >>");
		for (FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
				Assert.assertEquals(count.getCount(), 2);
			}
		}
	}

	private void facetPrefixTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);
		solrQuery.setFacetPrefix("ipo");
		solrQuery.addFacetField("name");
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetPrefixTest >>");
		for (FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
				Assert.assertEquals(count.getCount(), 3);
			}
		}
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
		for (FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
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
		for (FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
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
		solrQuery.setFacetSort("count"); // possible value : count or index
		QueryResponse response = solrAssistant.query(solrQuery);
		List<FacetField> facetFields = response.getFacetFields();
		System.err.println("<< facetSortTest >>");
		for (FacetField facetField : facetFields) {
			long oldCount = Long.MAX_VALUE;
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
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
		for (FacetField facetField : facetFields) {
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
				System.err.println(facetField.getName() + " - " + count.getName() + " : " + count.getCount());
			}
		}
		Assert.assertTrue(facetFields.size() > 0);
	}

}
