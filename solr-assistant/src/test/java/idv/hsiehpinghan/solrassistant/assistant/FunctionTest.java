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
public class FunctionTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void dataTransformationTest() throws Exception {
		defTest();
		fieldTest();
		mapTest_0();
		mapTest_1();
		msTest();
		ordTest();
		rordTest();
		scaleTest();
	}

	@Test
	public void relevancyTest() throws Exception {
		docfreqTest();
		idfTest();
		maxdocTest();
		normTest();
		numdocsTest();
		sumtotaltermfreqTest();
		termfreqTest();
		tfTest();
		totaltermfreqTest();
	}

	/**
	 * Returns the value of x if x exists, otherwise returns the value of y.
	 * 
	 * @throws SolrServerException
	 */
	private void defTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "defTest:def(popularity,id)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("defTest"), "GB18030TEST");
		}
	}

	/**
	 * Returns the field value of an indexed field with a maximum of one value
	 * per document for the field.
	 * 
	 * @throws SolrServerException
	 */
	private void fieldTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "fieldTest:field(id)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("fieldTest"), "GB18030TEST");
		}
	}

	/**
	 * Returns target if x falls between min and max exclusive.
	 * 
	 * @throws SolrServerException
	 */
	private void mapTest_0() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "mapTest_0:map(price, -10, 10, 100)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("mapTest_0").toString(), "100.0");
		}
	}

	/**
	 * Returns target if x falls between min and max exclusive. If specified
	 * (optional), else will be returned otherwise.
	 * 
	 * @throws SolrServerException
	 */
	private void mapTest_1() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "mapTest_1:map(price, -10, -5, 100, -100)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("mapTest_1").toString(), "-100.0");
		}
	}

	/**
	 * Returns time2 - time1.
	 * 
	 * @throws SolrServerException
	 */
	private void msTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "msTest:ms(manufacturedate_dt, 2006-02-13T15:26:36Z)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("msTest").toString(), "1000.0");
		}
	}

	/**
	 * Returns the position of the term in the search index (one term per field
	 * per document), from 1 to the number of unique terms in the index.
	 * 
	 * @throws SolrServerException
	 */
	private void ordTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "ordTest:ord(name)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("ordTest"), 23);
		}
	}

	/**
	 * Returns the reverse ordering of the ord function.
	 * 
	 * @throws SolrServerException
	 */
	private void rordTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "rordTest:rord(name)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("rordTest"), 9);
		}
	}

	/**
	 * Scales the values of x in each document between number1 and number2 based
	 * upon the min and max value of x across all documents.
	 * 
	 * @throws SolrServerException
	 */
	private void scaleTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "scaleTest:scale(price, 0, 100)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("scaleTest").toString(), "4.1837196");
		}
	}

	/**
	 * The number of documents containing the term in the field named fieldName.
	 * 
	 * @throws SolrServerException
	 */
	private void docfreqTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "docfreqTest:docfreq(name, 'iPod')");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("docfreqTest : " + doc.getFieldValue("docfreqTest"));
		}
	}

	/**
	 * The idf calculation for the value in the field named fieldName.
	 * 
	 * @throws SolrServerException
	 */
	private void idfTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "idfTest:idf(name, 'iPod')");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("idfTest : " + doc.getFieldValue("idfTest"));
		}
	}

	/**
	 * The number of documents in the index, including deleted documents not yet
	 * purged.
	 * 
	 * @throws SolrServerException
	 */
	private void maxdocTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "maxdocTest:maxdoc()");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("maxdocTest : " + doc.getFieldValue("maxdocTest"));
		}
	}

	/**
	 * The norm stored in the index for the field named fieldName.
	 * 
	 * @throws SolrServerException
	 */
	private void normTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "normTest:norm(name)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("normTest : " + doc.getFieldValue("normTest"));
		}
	}

	/**
	 * The number of documents in the index, excluding deleted documents not yet
	 * purged.
	 * 
	 * @throws SolrServerException
	 */
	private void numdocsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "numdocsTest:numdocs()");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("numdocsTest : " + doc.getFieldValue("numdocsTest"));
		}
	}

	/**
	 * The number of indexed tokens in the field across the index.
	 * 
	 * @throws SolrServerException
	 */
	private void sumtotaltermfreqTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "sumtotaltermfreqTest:sumtotaltermfreq(name)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("sumtotaltermfreqTest : " + doc.getFieldValue("sumtotaltermfreqTest"));
		}
	}

	/**
	 * The number of times the term appears in the field in the document.
	 * 
	 * @throws SolrServerException
	 */
	private void termfreqTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "termfreqTest:termfreq(name, 'drive')");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("termfreqTest : " + doc.getFieldValue("termfreqTest"));
		}
	}

	/**
	 * The tf factor for the term in the field using that field’s similarity.
	 * 
	 * @throws SolrServerException
	 */
	private void tfTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "tfTest:tf(name, 'drive')");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("tfTest : " + doc.getFieldValue("tfTest"));
		}
	}

	/**
	 * The number of times the term appears in the entire index.
	 * 
	 * @throws SolrServerException
	 */
	private void totaltermfreqTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "totaltermfreqTest:totaltermfreq(name, 'drive')");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println("totaltermfreqTest : " + doc.getFieldValue("totaltermfreqTest"));
		}
	}
}
