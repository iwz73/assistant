package idv.hsiehpinghan.solrassistant.assistant;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SpellcheckTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void spellCheckTest() throws Exception {
		spellCheckResponseTest();
	}

	private void spellCheckResponseTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("ipad");
		solrQuery.setParam("spellcheck", "true");
		QueryResponse response = solrAssistant.query(solrQuery);
		SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
		Map<String, Suggestion> suggestionMap = spellCheckResponse.getSuggestionMap();
		System.err.println("<< spellCheckResponseTest >>");
		for(Map.Entry<String, Suggestion> ent : suggestionMap.entrySet()) {
			Suggestion suggestion = ent.getValue();
			System.err.println(ent.getKey() + " / " + suggestion.getNumFound() + " / " + suggestion.getAlternatives());
		}
		Assert.assertTrue(suggestionMap.size() > 0);
	}

}
