package idv.hsiehpinghan.solrassistant.assistant;

import org.apache.solr.client.solrj.SolrQuery;
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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class LanguageIdentifierTest extends AbstractTestNGSpringContextTests {
	private static final String ID = String.valueOf(System.nanoTime());
	private static final String LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_TW = "現在美國有個網站標榜「快樂新聞」，從正面積極的角度來編輯新聞，他認為，多看正面的新聞，心情就會變好，打打殺殺的衝突場面和八卦緋聞，在這個網站內都看不到。";
	private SolrInputDocument solrInputDocument = generateSolrInputDocument();
	private SolrQuery solrQuery = generateSolrQuery();

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void add() throws Exception {
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	@Test(dependsOnMethods = { "add" })
	public void query() throws Exception {
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(String.valueOf(doc.getFieldValue("id")),
					String.valueOf(solrInputDocument.getFieldValue("id")));
			Assert.assertEquals(doc.getFieldValue("lang_detect_language_identifier_update_processor_factory_zh-tw"),
					solrInputDocument.getFieldValue("lang_detect_language_identifier_update_processor_factory"));
			Assert.assertEquals(doc.getFieldValue("language_s"), "zh-tw");
			Assert.assertEquals(doc.getFieldValue("language_ss").toString(), "[zh-tw]");
		}
	}

//	@Test(dependsOnMethods = { "query" })
	public void deleteBean() throws Exception {
		String id = String.valueOf(solrInputDocument.getFieldValue("id"));
		UpdateResponse deleteResponse = solrAssistant.deleteById(id);
		Assert.assertEquals(deleteResponse.getStatus(), 0);
		QueryResponse queryResponse = solrAssistant.query(solrQuery);
		Assert.assertEquals(queryResponse.getResults().size(), 0);
	}

	private SolrQuery generateSolrQuery() {
		SolrQuery query = new SolrQuery();
		query.setQuery("id :" + solrInputDocument.getFieldValue("id"));
		return query;
	}

	private SolrInputDocument generateSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", ID);
		doc.setField("lang_detect_language_identifier_update_processor_factory",
				LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_TW);
		return doc;
	}

}
