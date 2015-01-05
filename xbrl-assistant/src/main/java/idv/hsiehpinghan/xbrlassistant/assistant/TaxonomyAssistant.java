package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.cache.TaxonomyCache;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.exception.SaxParserBreakException;
import idv.hsiehpinghan.xbrlassistant.handler.SchemaReferenceHandler;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jcx.xbrl.taxonomy.XbrlPresentationTree;
import jcx.xbrl.taxonomy.XbrlPresentationTreeNode;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class TaxonomyAssistant {
	private final String EN = "en";
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private InstanceAssistant instanceAssistant;
	@Autowired
	private TaxonomyCache cache;
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Get all element id of presentIds.
	 * 
	 * @param taxonomyVersion
	 * @param presentIds
	 * @return
	 * @throws Exception
	 */
	public Set<String> getPresentationElementIds(
			XbrlTaxonomyVersion taxonomyVersion, List<String> presentIds)
			throws Exception {
		XbrlTaxonomy taxonomy = getTaxonomy(taxonomyVersion);

		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		Set<String> resultSet = new HashSet<String>();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			if (presentIds.contains(presentId) == false) {
				continue;
			}
			XbrlPresentationTree[] presentTrees = taxonomy
					.getPresentationTree(presentId);
			for (XbrlPresentationTree pTree : presentTrees) {
				// Get root node. (ex :
				// ifrs_StatementOfComprehensiveIncomeAbstract)
				XbrlPresentationTreeNode pRootNode = (XbrlPresentationTreeNode) pTree
						.getRootNode();

				addChildNodeId(pRootNode, resultSet);
			}
		}

		return resultSet;
	}

	private void addChildNodeId(XbrlPresentationTreeNode presentationNode,
			Set<String> resultSet) {
		if (presentationNode.hasChild()) {
			XbrlPresentationTreeNode childPresentNode = (XbrlPresentationTreeNode) presentationNode
					.getFirstChild();
			do {
				resultSet.add(childPresentNode.getID());
				addChildNodeId(childPresentNode, resultSet);
				childPresentNode = (XbrlPresentationTreeNode) childPresentNode
						.getNextSibling();
			} while (childPresentNode != null);
		}
	}

	/**
	 * Get json format presentation report. (PresentId can reference
	 * Presentation.Id....
	 * 
	 * @param taxonomyVersion
	 * @param presentIds
	 * @return
	 * @throws Exception
	 */
	public ObjectNode getPresentationJson(XbrlTaxonomyVersion taxonomyVersion,
			List<String> presentIds) throws Exception {
		XbrlTaxonomy taxonomy = getTaxonomy(taxonomyVersion);

		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		ObjectNode resultNode = objectMapper.createObjectNode();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			if (presentIds.contains(presentId) == false) {
				continue;
			}
			ObjectNode rNode = objectMapper.createObjectNode();
			resultNode.set(presentId, rNode);
			XbrlPresentationTree[] presentTrees = taxonomy
					.getPresentationTree(presentId);
			for (XbrlPresentationTree pTree : presentTrees) {
				// Get root node. (ex :
				// ifrs_StatementOfComprehensiveIncomeAbstract)
				XbrlPresentationTreeNode pRootNode = (XbrlPresentationTreeNode) pTree
						.getRootNode();
				generatePresentationJsonObjectContent(rNode, pRootNode,
						taxonomy);
			}
		}

		return resultNode;
	}

	/**
	 * Get xbrl taxonomy version.
	 * 
	 * @param instanceFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public XbrlTaxonomyVersion getXbrlTaxonomyVersion(File instanceFile)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParser saxParser;

		saxParser = SAXParserFactory.newInstance().newSAXParser();
		SchemaReferenceHandler handler = null;
		try {
			handler = new SchemaReferenceHandler();
			saxParser.parse(instanceFile, handler);
		} catch (SaxParserBreakException e) {
			return handler.getXbrlTaxonomyVersion();
		}
		throw new RuntimeException("Xbrl taxonomy version not found !!!");
	}

	XbrlTaxonomy getXbrlTaxonomy(File instanceFile) throws Exception {
		XbrlTaxonomyVersion version = getXbrlTaxonomyVersion(instanceFile);
		return cache.getTaxonomy(version, getTaxonomyPath(version));
	}

	private void generatePresentationJsonObjectContent(ObjectNode resultNode,
			XbrlPresentationTreeNode presentationNode, XbrlTaxonomy taxonomy)
			throws Exception {
		String elementId = presentationNode.getID();
		ObjectNode objNode = objectMapper.createObjectNode();
		resultNode.set(elementId, objNode);
		objNode.put(Presentation.Attribute.CHINESE_LABEL,
				taxonomy.getLabelByID(elementId));
		objNode.put(Presentation.Attribute.ENGLISH_LABEL,
				taxonomy.getLabelByID(elementId, EN));
		objNode.put(Presentation.Attribute.ORDER, presentationNode.getOrder());
		if (presentationNode.hasChild()) {
			XbrlPresentationTreeNode childPresentNode = (XbrlPresentationTreeNode) presentationNode
					.getFirstChild();
			do {
				// ObjectNode childObjNode = objectMapper.createObjectNode();
				// String childElementId = childTreeNode.getID();
				// objNode.set(childElementId, childObjNode);
				generatePresentationJsonObjectContent(objNode,
						childPresentNode, taxonomy);
				childPresentNode = (XbrlPresentationTreeNode) childPresentNode
						.getNextSibling();
			} while (childPresentNode != null);
		}
	}

	private XbrlTaxonomy getTaxonomy(XbrlTaxonomyVersion taxonomyVersion)
			throws Exception {
		return cache.getTaxonomy(taxonomyVersion,
				getTaxonomyPath(taxonomyVersion));
	}

	private String getTaxonomyPath(XbrlTaxonomyVersion version) {
		switch (version) {
		case TIFRS_BASI_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2013-03-31.xsd";
		case TIFRS_BASI_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2013-03-31.xsd";
		case TIFRS_BD_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2013-03-31.xsd";
		case TIFRS_BD_ER_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2013-03-31.xsd";
		case TIFRS_BD_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2013-03-31.xsd";
		case TIFRS_CI_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
		case TIFRS_CI_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2013-03-31.xsd";
		case TIFRS_FH_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/FH/tifrs-fh-2013-03-31.xsd";
		case TIFRS_INS_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2013-03-31.xsd";
		case TIFRS_INS_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2013-03-31.xsd";
		case TIFRS_MIM_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2013-03-31.xsd";
		case TIFRS_BASI_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2014-03-31.xsd";
		case TIFRS_BASI_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2014-03-31.xsd";
		case TIFRS_BD_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2014-03-31.xsd";
		case TIFRS_BD_ER_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2014-03-31.xsd";
		case TIFRS_BD_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2014-03-31.xsd";
		case TIFRS_CI_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2014-03-31.xsd";
		case TIFRS_CI_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2014-03-31.xsd";
		case TIFRS_FH_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/FH/tifrs-fh-2014-03-31.xsd";
		case TIFRS_INS_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2014-03-31.xsd";
		case TIFRS_INS_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2014-03-31.xsd";
		case TIFRS_MIM_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2014-03-31.xsd";
		}
		throw new RuntimeException("XbrlTaxonomy version undefined");
	}
}
