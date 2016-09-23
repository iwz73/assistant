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
	public void distanceTest() throws Exception {
		distTest();
		sqedistTest();
		hsinTest();
		strdistTest_0();
		strdistTest_1();
		geodistTest();
	}

	@Test
	public void mathTest() throws Exception {
		absTest();
		acosTest();
		asinTest();
		atanTest();
		atan2Test();
		cbrtTest();
		ceilTest();
		cosTest();
		coshTest();
		degTest();
		divTest();
		eTest();
		expTest();
		floorTest();
		linearTest();
		lnTest();
		logTest();
		piTest();
		powTest();
		productTest();
		radTest();
		recipTest();
		rintTest();
		sinTest();
		sinhTest();
		sqrtTest();
		subTest();
		sumTest();
		tanTest();
		tanhTest();
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

	@Test
	public void booleanTest() throws Exception {
		andTest();
		existsTest();
		ifTest();
		notTest();
		orTest();
		xorTest();
	}

	@Test
	public void customFunctionTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "customFunctionTest:concatenate(id,id,\"---\")");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("customFunctionTest"), "GB18030TEST---GB18030TEST");
		}
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

	/**
	 * Absolute value of x.
	 * 
	 * @throws SolrServerException
	 */
	private void absTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "absTest:abs(-1)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("absTest").toString(), "1.0");
		}
	}

	/**
	 * Arc cosine of x.
	 * 
	 * @throws SolrServerException
	 */
	private void acosTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "acosTest:acos(1)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("acosTest").toString(), "0.0");
		}
	}

	/**
	 * Arc sine of x.
	 * 
	 * @throws SolrServerException
	 */
	private void asinTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "asinTest:asin(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("asinTest").toString(), "0.0");
		}
	}

	/**
	 * Arc tangent of x.
	 * 
	 * @throws SolrServerException
	 */
	private void atanTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "atanTest:atan(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("atanTest").toString(), "0.0");
		}
	}

	/**
	 * Returns the angle resulting from the conversion of the rectangular
	 * coordinates x,y to polar coordinates.
	 * 
	 * @throws SolrServerException
	 */
	private void atan2Test() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "atan2Test:atan2(3,4)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("atan2Test").toString(), "0.6435011087932844");
		}
	}

	/**
	 * Cubed root of x.
	 * 
	 * @throws SolrServerException
	 */
	private void cbrtTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "cbrtTest:cbrt(27)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("cbrtTest").toString(), "3.0");
		}
	}

	/**
	 * Rounds the number x up to the next integer.
	 * 
	 * @throws SolrServerException
	 */
	private void ceilTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "ceilTest:ceil(3.3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("ceilTest").toString(), "4.0");
		}
	}

	/**
	 * Cosine of angle x.
	 * 
	 * @throws SolrServerException
	 */
	private void cosTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "cosTest:cos(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("cosTest").toString(), "1.0");
		}
	}

	/**
	 * Hyperbolic cosine of x.
	 * 
	 * @throws SolrServerException
	 */
	private void coshTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "coshTest:cosh(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("coshTest").toString(), "1.0");
		}
	}

	/**
	 * Converts x radians to degrees.
	 * 
	 * @throws SolrServerException
	 */
	private void degTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "degTest:deg(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("degTest").toString(), "0.0");
		}
	}

	/**
	 * Divides x by y.
	 * 
	 * @throws SolrServerException
	 */
	private void divTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "divTest:div(3,5)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("divTest").toString(), "0.6");
		}
	}

	/**
	 * Returns an approximation to Euler’s number, the base of the natural
	 * logarithm.
	 * 
	 * @throws SolrServerException
	 */
	private void eTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "eTest:e()");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("eTest").toString(), "2.718281828459045");
		}
	}

	/**
	 * Euler's number raised to the power of x.
	 * 
	 * @throws SolrServerException
	 */
	private void expTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "expTest:exp(1)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("expTest").toString(), "2.718281828459045");
		}
	}

	/**
	 * Rounds the number x down to the last integer.
	 * 
	 * @throws SolrServerException
	 */
	private void floorTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "floorTest:floor(3.3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("floorTest").toString(), "3.0");
		}
	}

	/**
	 * Returns the value of a linear function in the form f(x) = m*x + b.
	 * 
	 * @throws SolrServerException
	 */
	private void linearTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "linearTest:linear(3,4,5)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("linearTest").toString(), "17.0");
		}
	}

	/**
	 * Natural log of x.
	 * 
	 * @throws SolrServerException
	 */
	private void lnTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "lnTest:ln(1)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("lnTest").toString(), "0.0");
		}
	}

	/**
	 * base10 log of x.
	 * 
	 * @throws SolrServerException
	 */
	private void logTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "logTest:log(10)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("logTest").toString(), "1.0");
		}
	}

	/**
	 * Returns an approximation of pi, the ratio of the circumference of a
	 * circle to its diameter.
	 * 
	 * @throws SolrServerException
	 */
	private void piTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "piTest:pi()");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("piTest").toString(), "3.141592653589793");
		}
	}

	/**
	 * Raises x to the power of y.
	 * 
	 * @throws SolrServerException
	 */
	private void powTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "powTest:pow(3,2)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("powTest").toString(), "9.0");
		}
	}

	/**
	 * Multiplies all inputs together.
	 * 
	 * @throws SolrServerException
	 */
	private void productTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "productTest:product(2,3,4)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("productTest").toString(), "24.0");
		}
	}

	/**
	 * Converts x degrees to radians.
	 * 
	 * @throws SolrServerException
	 */
	private void radTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "radTest:rad(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("radTest").toString(), "0.0");
		}
	}

	/**
	 * A reciprocal function implementing a/(m*x+b).
	 * 
	 * @throws SolrServerException
	 */
	private void recipTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "recipTest:recip(2,3,5,4)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("recipTest").toString(), "0.5");
		}
	}

	/**
	 * Rounds the number x to the nearest integer.
	 * 
	 * @throws SolrServerException
	 */
	private void rintTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "rintTest:rint(3.3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("rintTest").toString(), "3.0");
		}
	}

	/**
	 * Trigonometric sine of angle x in radians.
	 * 
	 * @throws SolrServerException
	 */
	private void sinTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "sinTest:sin(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("sinTest").toString(), "0.0");
		}
	}

	/**
	 * Hyperbolic sine of x.
	 * 
	 * @throws SolrServerException
	 */
	private void sinhTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "sinhTest:sinh(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("sinhTest").toString(), "0.0");
		}
	}

	/**
	 * Square root of x.
	 * 
	 * @throws SolrServerException
	 */
	private void sqrtTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "sqrtTest:sqrt(9)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("sqrtTest").toString(), "3.0");
		}
	}

	/**
	 * Subtracts y from x.
	 * 
	 * @throws SolrServerException
	 */
	private void subTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "subTest:sub(9,3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("subTest").toString(), "6.0");
		}
	}

	/**
	 * Adds all inputs together.
	 * 
	 * @throws SolrServerException
	 */
	private void sumTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "sumTest:sum(1,2,3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("sumTest").toString(), "6.0");
		}
	}

	/**
	 * Tangent of angle x.
	 * 
	 * @throws SolrServerException
	 */
	private void tanTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "tanTest:tan(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("tanTest").toString(), "0.0");
		}
	}

	/**
	 * Hyperbolic tangent of x.
	 * 
	 * @throws SolrServerException
	 */
	private void tanhTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "tanhTest:tanh(0)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("tanhTest").toString(), "0.0");
		}
	}

	/**
	 * Calculates the distance between 2 vectors/points in n-dimensional space
	 * based upon the specified distance measure defined by power. The most
	 * common values for power are: 0—Sparseness calculation 1—Manhattan
	 * (taxicab) distance 2—Euclidean distance Infinity—Infinite norm (maximum
	 * value in the vector)
	 * 
	 * @throws SolrServerException
	 */
	private void distTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "distTest:dist(1,2,2,3,3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("distTest").toString(), "2.0");
		}
	}

	/**
	 * Returns the square of the Euclidean distance from the dist(2, ...)
	 * function, which is more efficient (it eliminates a square root
	 * calculation) if you only need relative ordering of values (for sorting or
	 * relevancy boosting) and not exact distance calculations.
	 * 
	 * @throws SolrServerException
	 */
	private void sqedistTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "sqedistTest:sqedist(1,1,3,3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("sqedistTest").toString(), "8.0");
		}
	}

	/**
	 * Calculates the Haversine, or greater circle, distance, the distance
	 * between two points when traveling along a sphere.
	 * 
	 * @throws SolrServerException
	 */
	private void hsinTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "hsinTest:hsin(2,true,1,1,0,0) ");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("hsinTest").toString(), "0.04936411278353287");
		}
	}

	/**
	 * Calculates the similarity, or distance apart, of characters within two
	 * strings ranging between 0 (not similar) and 1 (exactly the same). The
	 * valid values for distType are jw—Jaro-Winkler edit—The edit distance
	 * (Levenshtein distance) ngram—The NGramDistance Additionally, the fully
	 * qualified name of any class which implements the StringDistance interface
	 * can be specified(useful if you write your own plugin).
	 * 
	 * @throws SolrServerException
	 */
	private void strdistTest_0() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "strdistTest_0:strdist(\"This is for test\",\"this is for test\", \"edit\")");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("strdistTest_0").toString(), "0.9375");
		}
	}

	/**
	 * Calculates the similarity, or distance apart, of characters within two
	 * strings ranging between 0 (not similar) and 1 (exactly the same). The
	 * valid values for distType are jw—Jaro-Winkler edit—The edit distance
	 * (Levenshtein distance) ngram—The NGramDistance Additionally, the fully
	 * qualified name of any class which implements the StringDistance interface
	 * can be specified(useful if you write your own plugin).
	 * 
	 * @throws SolrServerException
	 */
	private void strdistTest_1() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "strdistTest_1:strdist(\"This is for test\",\"this is for test\", \"ngram\", 3)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("strdistTest_1").toString(), "0.8854167");
		}
	}

	/**
	 * Returns the distance between two points on Earth, one specified by a
	 * spatial field (sfield), and the other by coordinate.
	 * 
	 * @throws SolrServerException
	 */
	private void geodistTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "geodistTest:geodist(store,37.7,-122.4)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("geodistTest").toString(), "2282.411882354996");
		}
	}

	/**
	 * Returns true if both x and y are true.
	 * 
	 * @throws SolrServerException
	 */
	private void andTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:SP2514N");
		solrQuery.add("fl", "andTest:and(true, true)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("andTest"), true);
		}
	}

	/**
	 * Returns true if a value exists for x.
	 * 
	 * @throws SolrServerException
	 */
	private void existsTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "existsTest:exists(manufacturedate_dt)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("existsTest"), false);
		}
	}

	/**
	 * Returns trueValue if x is true and falseValue if x is false.
	 * 
	 * @throws SolrServerException
	 */
	private void ifTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "ifTest:if(false,\"Y\",\"N\")");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("ifTest"), "N");
		}
	}

	/**
	 * Returns false if x is true. Returns true if x is false.
	 * 
	 * @throws SolrServerException
	 */
	private void notTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "notTest:not(true)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("notTest"), false);
		}
	}

	/**
	 * Returns true if x is true, if y is true, or if both x and y are true.
	 * Returns false otherwise.
	 * 
	 * @throws SolrServerException
	 */
	private void orTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "orTest:or(false,true)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("orTest"), true);
		}
	}

	/**
	 * Returns true if either x or y is true, but false if both x and y are
	 * true.
	 * 
	 * @throws SolrServerException
	 */
	private void xorTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:GB18030TEST");
		solrQuery.add("fl", "xorTest:or(false,true)");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(doc.getFieldValue("xorTest"), true);
		}
	}

}
