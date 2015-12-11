package idv.hsiehpinghan.solrassistant.assistant;

import idv.hsiehpinghan.solrassistant.document.BasicDocument;
import idv.hsiehpinghan.solrassistant.suit.TestngSuitSetting;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SolrAssistantTest {
	private static final String ID = "id";
	private static final String NAME_S = "name_s";
	private SolrAssistant solrAssistant;
	private SolrInputDocument solrInputDocument = generateSolrInputDocument();
	private SolrQuery solrQuery = generateSolrQuery();
	private BasicDocument basicDocument = generateBasicDocument();

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		solrAssistant = applicationContext.getBean(SolrAssistant.class);
	}

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
			Assert.assertEquals(doc.getFieldValue(NAME_S),
					solrInputDocument.getFieldValue(NAME_S));
		}
	}

	@Test(dependsOnMethods = { "query" })
	private void delete() throws Exception {
		String id = String.valueOf(solrInputDocument.getFieldValue(ID));
		UpdateResponse deleteResponse = solrAssistant.deleteById(id);
		Assert.assertEquals(deleteResponse.getStatus(), 0);
		QueryResponse queryResponse = solrAssistant.query(solrQuery);
		Assert.assertEquals(queryResponse.getResults().size(), 0);
	}

	@Test
	public void addBean() throws Exception {
		UpdateResponse response = solrAssistant.addBean(basicDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	private SolrQuery generateSolrQuery() {
		SolrQuery query = new SolrQuery();
		query.setQuery(ID + ":" + solrInputDocument.getFieldValue(ID));
		return query;
	}

	private SolrInputDocument generateSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		long now = System.nanoTime();
		doc.setField(ID, now);
		doc.setField(NAME_S, "name_" + now);
		return doc;
	}

	private BasicDocument generateBasicDocument() {
		BasicDocument doc = new BasicDocument();
		long now = System.nanoTime();
		doc.setId(String.valueOf(now));
		doc.setName("name_" + now);
		return doc;
	}
}
