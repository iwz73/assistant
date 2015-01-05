package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import jcx.xbrl.taxonomy.XbrlTaxonomy;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TaxonomyAssistantTest {
	private TaxonomyAssistant taxonomyAssistant;
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		taxonomyAssistant = applicationContext.getBean(TaxonomyAssistant.class);
		objectMapper = applicationContext.getBean(ObjectMapper.class);
	}

	@Test
	public void getPresentationJson() throws Exception {
		List<String> ids = new ArrayList<String>(4);
		ids.add(Presentation.Id.BalanceSheet);
		ids.add(Presentation.Id.StatementOfComprehensiveIncome);
		ids.add(Presentation.Id.StatementOfCashFlows);
		ids.add(Presentation.Id.StatementOfChangesInEquity);
		ObjectNode objNode = taxonomyAssistant.getPresentationJson(
				XbrlTaxonomyVersion.TIFRS_CI_CR_2014_03_31, ids);
		// Balance sheet test
		JsonNode balanceSheetNode = objNode.get(Presentation.Id.BalanceSheet);
		JsonNode blanceSheetSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/presentation/tifrs-fr0-m1-ci-cr-1101-2013Q1_BalanceSheet.json"));
		Assert.assertEquals(balanceSheetNode.toString(),
				blanceSheetSample.toString());

		// Statement of comprehensive income test
		JsonNode incomeNode = objNode
				.get(Presentation.Id.StatementOfComprehensiveIncome);
		JsonNode incomeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/presentation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfComprehensiveIncome.json"));
		Assert.assertEquals(incomeNode.toString(), incomeSample.toString());

		// Statement of cash flows test
		JsonNode cashFlowNode = objNode
				.get(Presentation.Id.StatementOfCashFlows);
		JsonNode cashFlowSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/presentation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfCashFlows.json"));
		Assert.assertEquals(cashFlowNode.toString(), cashFlowSample.toString());

		// Statement of changes in equity test
		JsonNode equityChangeNode = objNode
				.get(Presentation.Id.StatementOfChangesInEquity);
		JsonNode equityChangeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/presentation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfChangesInEquity.json"));
		Assert.assertEquals(equityChangeNode.toString(),
				equityChangeSample.toString());
	}

	@Test
	public void getXbrlTaxonomy() throws Exception {
		String instancePath = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		File instanceFile = ResourceUtility.getFileResource(instancePath);
		XbrlTaxonomy taxonomy = taxonomyAssistant.getXbrlTaxonomy(instanceFile);
		Assert.assertNotNull(taxonomy);
	}

	@Test
	public void getXbrlTaxonomyVersion() throws ParserConfigurationException,
			SAXException, IOException {
		String instancePath = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		File instanceFile = ResourceUtility.getFileResource(instancePath);
		XbrlTaxonomyVersion version = taxonomyAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		Assert.assertEquals(XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31, version);
	}
	
	@Test
	public void getPresentationElementIds() throws Exception {
		List<String> ids = new ArrayList<String>(4);
		ids.add(Presentation.Id.BalanceSheet);
		ids.add(Presentation.Id.StatementOfComprehensiveIncome);
		ids.add(Presentation.Id.StatementOfCashFlows);
		ids.add(Presentation.Id.StatementOfChangesInEquity);
		Set<String> actual = taxonomyAssistant.getPresentationElementIds(XbrlTaxonomyVersion.TIFRS_CI_CR_2014_03_31, ids);
		Assert.assertEquals(1488, actual.size());
	}
}
