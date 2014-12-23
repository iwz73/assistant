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

import org.apache.log4j.Logger;
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

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
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
	
//	@Test
	public void getXbrlTaxonomyVersion() throws ParserConfigurationException, SAXException, IOException {
		XbrlTaxonomyVersion version = xbrlAssistant.getXbrlTaxonomyVersion(instanceFile);
		Assert.assertEquals(XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31, version);
	}
	
//	@Test
	public void getXbrlTaxonomy() throws ParserConfigurationException, SAXException, IOException {
		XbrlTaxonomy taxonomy = xbrlAssistant.getXbrlTaxonomy(instanceFile);
		Assert.assertSame(taxonomy, tifrsCiCr20130331);
	}

//	@Test
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
		ObjectNode objNode = xbrlAssistant.getPresentationJson(instanceFile, presentationIds);
		// Balance sheet test
		JsonNode balanceSheetNode = objNode.get(Presentation.Id.BalanceSheet);
		JsonNode blanceSheetSample = objectMapper.readTree(ResourceUtility.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_BalanceSheet.json"));
		Assert.assertEquals(balanceSheetNode, blanceSheetSample);	
		// Statement of comprehensive income test
		JsonNode incomeNode = objNode.get(Presentation.Id.StatementOfComprehensiveIncome);
		JsonNode incomeSample = objectMapper.readTree(ResourceUtility.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfComprehensiveIncome.json"));
		Assert.assertEquals(incomeNode, incomeSample);
		// Statement of cash flows test
		JsonNode cashFlowNode = objNode.get(Presentation.Id.StatementOfCashFlows);
		JsonNode cashFlowSample = objectMapper.readTree(ResourceUtility.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfCashFlows.json"));
		Assert.assertEquals(cashFlowNode, cashFlowSample);
		// Statement of changes in equity test
		JsonNode equityChangeNode = objNode.get(Presentation.Id.StatementOfChangesInEquity);
		JsonNode equityChangeSample = objectMapper.readTree(ResourceUtility.getFileResource("sample/tifrs-fr0-m1-ci-cr-1101-2013Q1_StatementOfChangesInEquity.json"));
		Assert.assertEquals(equityChangeNode, equityChangeSample);
	}
//	@Test
//	public void getXbrlTaxonomy() throws Exception {
//		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
//		taxonomy = xbrlAssistant.getXbrlTaxonomy(taxonomyPath);
//		@SuppressWarnings("unchecked")
//		Vector<String> schemaList = taxonomy.getSchemaList();
//		// print element id.(ex : tifrs-bsci-ci_LongtermNotesPayable)
//		// print(schemaList);
//		Assert.assertTrue(schemaList.size() == 3926);
//	}
//
//
//	@Test(dependsOnMethods = { "loadXbrlDocument" })
//	public void getBalanceSheet() throws a {
//		@SuppressWarnings("unchecked")
//		Vector<String> presents = taxonomy.getPresentationList();
//		for (int i = 0, size = presents.size(); i < size; ++i) {
//			// Get presentation node roleURI. (ex :
//			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
//			String presentNode = presents.get(i);
//			XbrlPresentationTree[] presentTrees = taxonomy
//					.getPresentationTree(presentNode);
//			for (XbrlPresentationTree tree : presentTrees) {
//				// Get root node. (ex :
//				// ifrs_StatementOfComprehensiveIncomeAbstract)
//				XbrlTreeNode rootNode = tree.getRootNode();
////				getSubNodes(rootNode, "");
//			}
//
//		}
//		
//		
////		ObjectMapper mapper = new ObjectMapper();
////		ObjectNode rootNode = mapper.createObjectNode();
////		return rootNode;
//	}
//	
////	@Test(dependsOnMethods = { "getXbrlTaxonomy" })
//	public void test() throws Exception {
//
//		// XbrlElement ele
//		// =document.getElementByID("ifrs_NoncontrollingInterests",
//		// "AsOf20130331");
//		// System.err.println(ele.getValue());
//
//		// @SuppressWarnings("unchecked")
//		// Hashtable<String, XbrlElementPool> hashTable =
//		// document.getElements();
//		// print(hashTable);
//
//		@SuppressWarnings("unchecked")
//		Vector<String> presents = taxonomy.getPresentationList();
//		for (int i = 0, size = presents.size(); i < size; ++i) {
//			// Get presentation node roleURI. (ex :
//			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
//			String presentNode = presents.get(i);
//			XbrlPresentationTree[] presentTrees = taxonomy
//					.getPresentationTree(presentNode);
//			for (XbrlPresentationTree tree : presentTrees) {
//				// Get root node. (ex :
//				// ifrs_StatementOfComprehensiveIncomeAbstract)
//				XbrlTreeNode rootNode = tree.getRootNode();
//				getSubNodes(rootNode, "");
//			}
//
//		}
//	}
//
//	private void getSubNodes(XbrlTreeNode node, String s) throws Exception {
//		System.err.println(node.getID() + " / " + s + taxonomy.getLabelByID(node.getID()));
////		if (node.hasChild()) {
////			XbrlTreeNode child = node.getFirstChild();
////			do {
////				getSubNodes(child, s + " ");
////				child = child.getNextSibling();
////			} while (child != null);
////		}
//	}

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
		for(int i = 0, size = elements.length; i < size; ++i) {
			XbrlElement ele = elements[i];
			System.err.println(ele.getID() + " / " + ele.getContext().getID() + " / " + ele.getValue());
		}
	}
//	
//	public static void main(String[] args) throws Exception {
//
//		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
//		File file = ResourceUtility.getFileResource("xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml");
//		saxParser.parse(file, new SchemaReferenceHandler());
////	    SAXReader reader = new SAXReader();
////	    File file = ResourceUtility.getFileResource("xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml");
////	    
////
////        System.err.println(file.exists());
////        
////        Document doc = reader.read(file);
////        Element ele = doc.getRootElement();
////        
////        List list = doc.selectNodes("context");
//
//
////        System.err.println(list.size());
//        
////		XbrlTaxonomy taxonomy = xbrlAssistant.getXbrlTaxonomy("xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd");
////		XbrlDocument document = xbrlAssistant.loadXbrlDocument(taxonomy, "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml") ;
////		ObjectMapper mapper = new ObjectMapper();
////		ObjectNode objNode = xbrlAssistant.getPresentationMap(taxonomy, mapper);
//		
//
////		System.err.println(objNode);
//		
//
////		XbrlUtility.test(taxonomy);
//		
////		Vector<XbrlContext> contexts = document.getAllContext();
////        for(int j = 0, sz = contexts.size(); j < sz; ++j) {
////        	System.out.println(contexts.get(j).getDimensionID());
////        	System.out.println(contexts.get(j).getDimensionMember());
////        	System.out.println(contexts.get(j).getID());
////        	System.out.println(contexts.get(j).getPeriodType());
////        	System.out.println(contexts.get(j).hasDimension());
//////        	System.out.println(contexts.get(j).);
////        }
//	}
}
