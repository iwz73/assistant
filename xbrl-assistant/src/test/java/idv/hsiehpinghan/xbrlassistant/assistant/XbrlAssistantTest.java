package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.xbrlassistant.xbrl.Calculation;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.taxonomy.XbrlTaxonomy;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class XbrlAssistantTest {
	private ApplicationContext applicationContext;
	private TaxonomyAssistant taxonomyAssistant;
	private XbrlAssistant xbrlAssistant;
	private File tifrs_fr0_m1_ci_cr_1101_2013Q1File;
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		applicationContext = TestngSuitSetting.getApplicationContext();
		taxonomyAssistant = applicationContext.getBean(TaxonomyAssistant.class);

		xbrlAssistant = applicationContext.getBean(XbrlAssistant.class);
		objectMapper = applicationContext.getBean(ObjectMapper.class);
	}

	@Test
	public void loadXbrlDocument() throws Exception {
		String tifrs_fr0_m1_ci_cr_1101_2013Q1 = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		tifrs_fr0_m1_ci_cr_1101_2013Q1File = ResourceUtility
				.getFileResource(tifrs_fr0_m1_ci_cr_1101_2013Q1);
		XbrlTaxonomy tifrsCiCr20130331Taxonomy = taxonomyAssistant
				.getXbrlTaxonomy(tifrs_fr0_m1_ci_cr_1101_2013Q1File);
		XbrlDocument tifrs_fr0_m1_ci_cr_1101_2013Q1Document = xbrlAssistant
				.loadXbrlDocument(tifrs_fr0_m1_ci_cr_1101_2013Q1File,
						tifrsCiCr20130331Taxonomy);
		Assert.assertTrue(tifrs_fr0_m1_ci_cr_1101_2013Q1Document
				.getAllContext().size() == 89);
	}

	@Test(dependsOnMethods = { "loadXbrlDocument" })
	public void getPresentationJson() throws Exception {
		List<String> presentationIds = new ArrayList<String>(4);
		presentationIds.add(Presentation.Id.BalanceSheet);
		presentationIds.add(Presentation.Id.StatementOfComprehensiveIncome);
		presentationIds.add(Presentation.Id.StatementOfCashFlows);
		presentationIds.add(Presentation.Id.StatementOfChangesInEquity);
		ObjectNode objNode = xbrlAssistant.getPresentationJson(
				tifrs_fr0_m1_ci_cr_1101_2013Q1File, presentationIds);
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

	@Test(dependsOnMethods = { "loadXbrlDocument" })
	public void getCalculationJson() throws Exception {
		List<String> calIds = new ArrayList<String>(4);
		calIds.add(Calculation.Id.BalanceSheet);
		calIds.add(Calculation.Id.StatementOfComprehensiveIncome);
		calIds.add(Calculation.Id.StatementOfCashFlows);
		calIds.add(Calculation.Id.StatementOfChangesInEquity);
		ObjectNode objNode = xbrlAssistant.getCalculationJson(
				tifrs_fr0_m1_ci_cr_1101_2013Q1File, calIds);

		// Balance sheet test
		JsonNode balanceSheetNode = objNode.get(Calculation.Id.BalanceSheet);
		JsonNode blanceSheetSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/calculation/tifrs-fr0-m1-ci-cr-1101-2013Q1_BalanceSheet.json"));
		Assert.assertEquals(balanceSheetNode.toString(),
				blanceSheetSample.toString());

		// Statement of comprehensive income test
		JsonNode incomeNode = objNode
				.get(Presentation.Id.StatementOfComprehensiveIncome);
		JsonNode incomeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/calculation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfComprehensiveIncome.json"));
		Assert.assertEquals(incomeNode.toString(), incomeSample.toString());

		// Statement of cash flows test
		JsonNode cashFlowNode = objNode
				.get(Presentation.Id.StatementOfCashFlows);
		JsonNode cashFlowSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/calculation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfCashFlows.json"));
		Assert.assertEquals(cashFlowNode.toString(), cashFlowSample.toString());

		// Statement of changes in equity test
		JsonNode equityChangeNode = objNode
				.get(Presentation.Id.StatementOfChangesInEquity);
		JsonNode equityChangeSample = objectMapper
				.readTree(ResourceUtility
						.getFileResource("sample/calculation/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfChangesInEquity.json"));
		Assert.assertEquals(equityChangeNode.toString(),
				equityChangeSample.toString());
	}
}
