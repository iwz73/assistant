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
public class LuceneQueryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void fieldedTermSearchesTest() throws Exception {
		termSearchTest();
		multipleTermSearchTest();
		appearTogetherTermSearchTest();

	}

	@Test
	public void requiredTermTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		// solrQuery.setQuery("+name:ipod +id:F8V7067-APL-KIT");
		// solrQuery.setQuery("name:ipod && id:F8V7067-APL-KIT");
		solrQuery.setQuery("name:ipod AND id:F8V7067-APL-KIT");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void excludingTermsTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		// solrQuery.setQuery("-name:ipod -id:F8V7067-APL-KIT");
		solrQuery.setQuery("name:ipod AND NOT id:F8V7067-APL-KIT");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}

	@Test
	public void optionalTermTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		// solrQuery.setQuery("name:ipod || id:F8V7067-APL-KIT");
		solrQuery.setQuery("name:ipod OR id:F8V7067-APL-KIT");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 3);
	}

	@Test
	public void groupedExpressionTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("(name:ipod AND id:(IW-02 OR F8V7067-APL-KIT)) AND weight:4");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void termProximityTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:\"iPod Cord Dock\"~3");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void characterProximityTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:Mobi~2");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}

	@Test
	public void rangeSearchTest() throws Exception {
		numberRangeSearchesTest();
		dateRangeSearchesTest();
		stringRangeSearchesTest();
	}

	@Test
	public void wildcardSearchTest() throws Exception {
		asteriskWildcardSearchTest();
		questionMarkWildcardSearchTest();
	}

	@Test
	public void boostingExpressionsTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod^10 OR manu:Samsung^0.5");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 4);
	}

	@Test
	public void wtTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod");
		solrQuery.set("wt", "javabin");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 3);
	}

	@Test
	public void flTest() throws Exception {
		basicFlTest();
		specialFlTest();
		returnFieldAliasFlTest();
	}

	@Test
	public void pagingTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.set("start", "0");
		solrQuery.set("rows", "5");	
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 5);
	}
	
	@Test
	public void sortTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.set("sort", "price desc, includes asc");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.get(0).getFieldValue("id"), "9784828985906");
	}
	
	private void basicFlTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod");
		solrQuery.set("fl", "id,name");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertNotNull(doc.getFieldValue("id"));
			Assert.assertNotNull(doc.getFieldValue("name"));
			Assert.assertNull(doc.getFieldValue("manu"));
		}
	}

	private void specialFlTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod");
		solrQuery.set("fl", "score,[docid],[explain],[shard]");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertNotNull(doc.getFieldValue("score"));
			Assert.assertNotNull(doc.getFieldValue("[docid]"));
			Assert.assertNotNull(doc.getFieldValue("[explain]"));
			Assert.assertNotNull(doc.getFieldValue("[shard]"));
		}
	}

	private void returnFieldAliasFlTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod");
		solrQuery.set("fl", "id,test_name:name");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertNotNull(doc.getFieldValue("id"));
			Assert.assertNotNull(doc.getFieldValue("test_name"));
		}
	}

	private void asteriskWildcardSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:c*");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 9);
	}

	private void questionMarkWildcardSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:m?ni");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	private void stringRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("manu:[Belkin TO C}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 2);
	}

	private void dateRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("manufacturedate_dt:[2005-08-01T16:30:25Z TO NOW-1DAY}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 10);
	}

	private void numberRangeSearchesTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("weight:[2 TO 4}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	private void termSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:ipod AND id:F8V7067-APL-KIT");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	private void multipleTermSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:(ipod GB18030)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 4);
	}

	private void appearTogetherTermSearchTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:\"for iPod\"");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

}
