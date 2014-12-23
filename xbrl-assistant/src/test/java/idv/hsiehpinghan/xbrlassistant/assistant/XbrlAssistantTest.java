package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.data.XbrlElement;
import jcx.xbrl.taxonomy.XbrlTaxonomy;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class XbrlAssistantTest {
	private XbrlAssistant xbrlAssistant;
	private File instanceFile;
	private XbrlTaxonomy tifrsCiCr20130331;
	private XbrlDocument document;
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		xbrlAssistant = applicationContext.getBean(XbrlAssistant.class);
		tifrsCiCr20130331 = applicationContext.getBean(XbrlTaxonomy.class);
		objectMapper = applicationContext.getBean(ObjectMapper.class);

		String instancePath = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		instanceFile = ResourceUtility.getFileResource(instancePath);
	}

	 @Test
	public void getXbrlTaxonomyVersion() throws ParserConfigurationException,
			SAXException, IOException {
		XbrlTaxonomyVersion version = xbrlAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		Assert.assertEquals(XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31, version);
	}

	 @Test
	public void getXbrlTaxonomy() throws ParserConfigurationException,
			SAXException, IOException {
		XbrlTaxonomy taxonomy = xbrlAssistant.getXbrlTaxonomy(instanceFile);
		Assert.assertSame(taxonomy, tifrsCiCr20130331);
	}

	 @Test
	public void loadXbrlDocument() throws Exception {
		document = xbrlAssistant.loadXbrlDocument(instanceFile);
		Assert.assertTrue(document.getAllContext().size() == 89);
	}

	@Test
	public void getPresentationJson() throws Exception {
		List<String> presentationIds = new ArrayList<String>(4);
		presentationIds.add(Presentation.Id.BalanceSheet);
		presentationIds.add(Presentation.Id.StatementOfComprehensiveIncome);
		presentationIds.add(Presentation.Id.StatementOfCashFlows);
		presentationIds.add(Presentation.Id.StatementOfChangesInEquity);
		ObjectNode objNode = xbrlAssistant.getPresentationJson(instanceFile,
				presentationIds);
		// Balance sheet test
		JsonNode balanceSheetNode = objNode.get(Presentation.Id.BalanceSheet);
		JsonNode blanceSheetSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_BalanceSheet.json"));
		Assert.assertEquals(balanceSheetNode, blanceSheetSample);
		// Statement of comprehensive income test
		JsonNode incomeNode = objNode
				.get(Presentation.Id.StatementOfComprehensiveIncome);
		JsonNode incomeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfComprehensiveIncome.json"));
		Assert.assertEquals(incomeNode, incomeSample);
		// Statement of cash flows test
		JsonNode cashFlowNode = objNode
				.get(Presentation.Id.StatementOfCashFlows);
		JsonNode cashFlowSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfCashFlows.json"));
		Assert.assertEquals(cashFlowNode, cashFlowSample);
		// Statement of changes in equity test
		JsonNode equityChangeNode = objNode
				.get(Presentation.Id.StatementOfChangesInEquity);
		JsonNode equityChangeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfChangesInEquity.json"));
		Assert.assertEquals(equityChangeNode, equityChangeSample);
	}

	@SuppressWarnings("unused")
	private void print(Vector<?> vec) {
		for (int i = 0, size = vec.size(); i < size; ++i) {
			System.err.println(vec.get(i));
		}
	}

	@SuppressWarnings("unused")
	private void print(Hashtable<?, ?> hashTable) {
		Enumeration<?> en = hashTable.keys();
		while (en.hasMoreElements()) {
			Object key = en.nextElement();
			Object value = hashTable.get(key);
			System.err.println(key + " / " + value);
		}
	}

	@SuppressWarnings("unused")
	private void print(XbrlElement[] elements) {
		for (int i = 0, size = elements.length; i < size; ++i) {
			XbrlElement ele = elements[i];
			System.err.println(ele.getID() + " / " + ele.getContext().getID()
					+ " / " + ele.getValue());
		}
	}

}
