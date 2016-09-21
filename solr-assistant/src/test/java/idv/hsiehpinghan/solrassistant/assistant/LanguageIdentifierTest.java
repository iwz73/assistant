package idv.hsiehpinghan.solrassistant.assistant;

import java.io.IOException;

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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class LanguageIdentifierTest extends AbstractTestNGSpringContextTests {
	private static final String LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_EN = "This is the first time an individual protein from a tardigrade has been shown to be active in radiation protection.";
	private static final String LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_TW = "現在美國有個網站標榜「快樂新聞」，從正面積極的角度來編輯新聞，他認為，多看正面的新聞，心情就會變好，打打殺殺的衝突場面和八卦緋聞，在這個網站內都看不到。";
	private static final String LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_CN = "今年大陆已出现单宗超过10亿元的地块303宗，其中溢价率超过100%的地块达到153宗。";
	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void testEn() throws Exception {
		String id = String.valueOf(System.nanoTime());
		SolrInputDocument solrInputDocument = generateSolrInputDocument(id,
				LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_EN);
		SolrQuery solrQuery = generateSolrQuery(solrInputDocument);
		add(solrInputDocument);
		query(solrInputDocument, solrQuery, "en");
		deleteBean(solrInputDocument, solrQuery);
	}

	@Test
	public void testZhTw() throws Exception {
		String id = String.valueOf(System.nanoTime());
		SolrInputDocument solrInputDocument = generateSolrInputDocument(id,
				LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_TW);
		SolrQuery solrQuery = generateSolrQuery(solrInputDocument);
		add(solrInputDocument);
		query(solrInputDocument, solrQuery, "zh-tw");
		deleteBean(solrInputDocument, solrQuery);
	}

	@Test
	public void testZhCn() throws Exception {
		String id = String.valueOf(System.nanoTime());
		SolrInputDocument solrInputDocument = generateSolrInputDocument(id,
				LANG_DETECT_LANGUAGE_IDENTIFIER_UPDATE_PROCESSOR_FACTORY_ZH_CN);
		SolrQuery solrQuery = generateSolrQuery(solrInputDocument);
		add(solrInputDocument);
		query(solrInputDocument, solrQuery, "zh-cn");
		deleteBean(solrInputDocument, solrQuery);
	}

	private void add(SolrInputDocument solrInputDocument) throws SolrServerException, IOException {
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	private void query(SolrInputDocument solrInputDocument, SolrQuery solrQuery, String language) throws Exception {
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(String.valueOf(doc.getFieldValue("id")),
					String.valueOf(solrInputDocument.getFieldValue("id")));
			Assert.assertEquals(
					doc.getFieldValue("lang_detect_language_identifier_update_processor_factory_" + language),
					solrInputDocument.getFieldValue("lang_detect_language_identifier_update_processor_factory"));
			Assert.assertEquals(doc.getFieldValue("language_s"), language);
			Assert.assertEquals(doc.getFieldValue("language_ss").toString(), "[" + language + "]");
		}
	}

	private void deleteBean(SolrInputDocument solrInputDocument, SolrQuery solrQuery) throws Exception {
//		String id = String.valueOf(solrInputDocument.getFieldValue("id"));
//		UpdateResponse deleteResponse = solrAssistant.deleteById(id);
//		Assert.assertEquals(deleteResponse.getStatus(), 0);
//		QueryResponse queryResponse = solrAssistant.query(solrQuery);
//		Assert.assertEquals(queryResponse.getResults().size(), 0);
	}

	private SolrQuery generateSolrQuery(SolrInputDocument solrInputDocument) {
		SolrQuery query = new SolrQuery();
		query.setQuery("id :" + solrInputDocument.getFieldValue("id"));
		return query;
	}

	private SolrInputDocument generateSolrInputDocument(String id, String text) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", id);
		doc.setField("lang_detect_language_identifier_update_processor_factory", text);
		return doc;
	}

}
