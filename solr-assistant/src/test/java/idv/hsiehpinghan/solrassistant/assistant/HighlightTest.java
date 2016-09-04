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
public class HighlightTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void hightlightTest() throws Exception {
		highlightFieldTest();
	}

	private void highlightFieldTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("features:GPU");
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<em>");
		solrQuery.setHighlightSimplePost("</em>");
		solrQuery.addHighlightField("features");
		solrQuery.setHighlightSnippets(1);
		QueryResponse response = solrAssistant.query(solrQuery);
		Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
		System.err.println("<< highlightFieldTest >>");
		for(Map.Entry<String, Map<String, List<String>>> ent : highlights.entrySet()) {
			for(Map.Entry<String, List<String>> en : ent.getValue().entrySet()) {
				System.err.println(ent.getKey() + " / " + en.getKey() + " / " + en.getValue());
			}
		}
//		Assert.assertTrue(facetFields.size() > 0);
	}

}
