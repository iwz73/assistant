package idv.hsiehpinghan.solrassistant.assistant;

import org.apache.solr.client.solrj.SolrQuery;
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
public class GeofiltQueryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void basicTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("{!geofilt sfield=store pt=35.0752,-97.032 d=1}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("id"), "SP2514N");
		}
	}

}
