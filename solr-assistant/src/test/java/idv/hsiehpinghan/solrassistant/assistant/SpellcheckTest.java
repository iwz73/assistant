package idv.hsiehpinghan.solrassistant.assistant;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;
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
		spellcheckDictionaryTest();
	}

	@Test
	public void autosuggestTest() throws Exception {
		suggestTest();
	}

	private void suggestTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setRequestHandler("/suggest");
		solrQuery.setParam("spellcheck", "true");
		solrQuery.setParam("suggest.build", "true");
		solrQuery.setParam("suggest.dictionary", "mySuggester");
		solrQuery.setParam("suggest.q", "electr");
		QueryResponse response = solrAssistant.query(solrQuery);
		@SuppressWarnings("unchecked")
		Map<String, SimpleOrderedMap<NamedList<Integer>>> suggesters = (Map<String, SimpleOrderedMap<NamedList<Integer>>>) response
				.getResponse().get("suggest");
		System.err.println("<< suggestTest >>");
		for (Map.Entry<String, SimpleOrderedMap<NamedList<Integer>>> ent : suggesters.entrySet()) {
			SimpleOrderedMap<NamedList<Integer>> simpleOrderedMap = ent.getValue();
			for (int i = 0, size = simpleOrderedMap.size(); i < size; ++i) {
				String name = simpleOrderedMap.getName(i);
				NamedList<Integer> val = simpleOrderedMap.getVal(i);
				for (int j = 0, sz = val.size(); j < sz; ++j) {
					String n = val.getName(j);
					Object v = val.getVal(j);
					if (v instanceof Integer) {
						System.err.println(ent.getKey() + " / " + name + " / " + n + " / " + v);
					} else if (v instanceof List) {
						@SuppressWarnings({ "unchecked" })
						List<SimpleOrderedMap<Object>> list = (List<SimpleOrderedMap<Object>>) v;
						for (SimpleOrderedMap<Object> m : list) {
							for (int k = 0, s = m.size(); k < s; ++k) {
								String nm = m.getName(k);
								Object vl = m.getVal(k);
								if (vl instanceof String) {
									System.err
											.println(ent.getKey() + " / " + name + " / " + n + " / " + nm + " / " + vl);
								} else if (vl instanceof Long) {
									System.err
											.println(ent.getKey() + " / " + name + " / " + n + " / " + nm + " / " + vl);
								} else {
									throw new RuntimeException("class(" + vl.getClass() + ") not implements !!!");
								}
							}
						}
					} else {
						throw new RuntimeException("class(" + v.getClass() + ") not implements !!!");
					}
				}
			}
		}
	}

	private void spellcheckDictionaryTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("harddrive");
		solrQuery.setParam("spellcheck", "true");
		solrQuery.setParam("spellcheck.dictionary", "default");
		solrQuery.setParam("spellcheck.dictionary", "wordbreak");
		QueryResponse response = solrAssistant.query(solrQuery);
		SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
		Map<String, Suggestion> suggestionMap = spellCheckResponse.getSuggestionMap();
		System.err.println("<< spellcheckDictionaryTest >>");
		for (Map.Entry<String, Suggestion> ent : suggestionMap.entrySet()) {
			Suggestion suggestion = ent.getValue();
			System.err.println(ent.getKey() + " / " + suggestion.getNumFound() + " / " + suggestion.getAlternatives());
		}
		Assert.assertTrue(suggestionMap.size() > 0);
	}

	private void spellCheckResponseTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("ipad");
		solrQuery.setParam("spellcheck", "true");
		QueryResponse response = solrAssistant.query(solrQuery);
		SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
		Map<String, Suggestion> suggestionMap = spellCheckResponse.getSuggestionMap();
		System.err.println("<< spellCheckResponseTest >>");
		for (Map.Entry<String, Suggestion> ent : suggestionMap.entrySet()) {
			Suggestion suggestion = ent.getValue();
			System.err.println(ent.getKey() + " / " + suggestion.getNumFound() + " / " + suggestion.getAlternatives());
		}
		Assert.assertTrue(suggestionMap.size() > 0);
	}
}
