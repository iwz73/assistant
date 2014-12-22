package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Vector;

import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.data.XbrlTreeNode;
import jcx.xbrl.taxonomy.XbrlPresentationTree;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class XbrlAssistant {
	// Presentation id
//	public static final String BALANCESHEET_PRESENTATION_ID = "http://www.xbrl.org/tifrs/fr/role/BalanceSheet";
	
//	public static final String ELEMENT_ID = "elementId";
	public static final String ORDER = "order";
	public static final String LABEL = "label";
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Get taxonomy.
	 * @param taxonomyPath
	 * @return
	 * @throws Exception
	 */
	public static XbrlTaxonomy getXbrlTaxonomy(String taxonomyPath) throws Exception {
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}
	
	/**
	 * Load instance.
	 * @param taxonomy
	 * @param instancePath
	 * @return
	 * @throws Exception
	 */
	public static XbrlDocument loadXbrlDocument(XbrlTaxonomy taxonomy, String instancePath) throws Exception {
		File instance = ResourceUtility.getFileResource(instancePath);
		XbrlDocument xDoc = new XbrlDocument();
		InputStream is = new FileInputStream(instance.getAbsolutePath());
		xDoc.load(taxonomy, is);
		return xDoc;
	}
	
//	public static Map<String, ObjectNode> getPresentationMap(XbrlTaxonomy taxonomy) {
	public static ObjectNode getPresentationMap(XbrlTaxonomy taxonomy, ObjectMapper mapper) throws Exception {
		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		ObjectNode objNode = mapper.createObjectNode();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			XbrlPresentationTree[] presentTrees = taxonomy
					.getPresentationTree(presentId);
			for (XbrlPresentationTree tree : presentTrees) {
				// Get root node. (ex :
				// ifrs_StatementOfComprehensiveIncomeAbstract)
				XbrlTreeNode rootNode = tree.getRootNode();
				storeNodes(objNode, rootNode, taxonomy, mapper);
			}
		}

		return objNode;
	}
	
	private static void storeNodes(ObjectNode objNode, XbrlTreeNode node, XbrlTaxonomy taxonomy, ObjectMapper mapper) throws Exception {
		String id = node.getID();
		ObjectNode oNode = mapper.createObjectNode();
		oNode.put(XbrlAssistant.LABEL, taxonomy.getLabelByID(node.getID()));
		oNode.put(XbrlAssistant.ORDER, node.getOrder());
		objNode.set(id, oNode);
		
		if (node.hasChild()) {
			XbrlTreeNode child = node.getFirstChild();
			do {
//				storeNodes(objNode, child, s + " ");
				child = child.getNextSibling();
			} while (child != null);
		}
	}

	
	public static void test(XbrlTaxonomy taxonomy) throws Exception {
		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentNode = presents.get(i);
			XbrlPresentationTree[] presentTrees = taxonomy
					.getPresentationTree(presentNode);
			for (XbrlPresentationTree tree : presentTrees) {
				// Get root node. (ex :
				// ifrs_StatementOfComprehensiveIncomeAbstract)
				XbrlTreeNode rootNode = tree.getRootNode();
				getSubNodes(rootNode, taxonomy, "");
			}

		}
	}
	
	private static void getSubNodes(XbrlTreeNode node, XbrlTaxonomy taxonomy, String s) throws Exception {
		System.err.println(node.getID() + " / " + s + taxonomy.getLabelByID(node.getID()));
		if (node.hasChild()) {
			XbrlTreeNode child = node.getFirstChild();
			do {
				getSubNodes(child, taxonomy, s + " ");
				child = child.getNextSibling();
			} while (child != null);
		}
	}
}
