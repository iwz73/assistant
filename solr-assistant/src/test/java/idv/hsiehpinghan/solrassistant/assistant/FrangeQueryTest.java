package idv.hsiehpinghan.solrassistant.assistant;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class FrangeQueryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void wtTest() throws Exception {
		productTest();
	}

	private void productTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.add("fq", "{!frange l=100 u=1000}product(price, popularity)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Float result = ((Float) doc.getFieldValue("price")) * ((Integer) doc.getFieldValue("popularity"));
			Assert.assertTrue(100 <= result);
			Assert.assertTrue(result <= 1000);
		}
	}
}
