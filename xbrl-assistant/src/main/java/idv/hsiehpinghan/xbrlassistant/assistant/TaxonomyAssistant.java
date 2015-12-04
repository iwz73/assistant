package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.cache.TaxonomyCache;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.exception.SaxParserBreakException;
import idv.hsiehpinghan.xbrlassistant.handler.SchemaReferenceHandler;
import idv.hsiehpinghan.xbrlassistant.property.XbrlAssistantProperty;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class TaxonomyAssistant implements InitializingBean {
	// private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String EN = "en";
	private File taxonomyDir;

	@Autowired
	private InstanceAssistant instanceAssistant;
	@Autowired
	private TaxonomyCache cache;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private XbrlAssistantProperty xbrlAssistantProperty;

	@Override
	public void afterPropertiesSet() throws Exception {
		taxonomyDir = new File(xbrlAssistantProperty.getTaxonomyDir());
		File tifrs20130331Dir = new File(taxonomyDir, "tifrs-20130331");
		if (tifrs20130331Dir.exists() == false) {
			throw new RuntimeException(tifrs20130331Dir + " not exists !!!");
		}
		File tifrs20140331Dir = new File(taxonomyDir, "tifrs-20140331");
		if (tifrs20140331Dir.exists() == false) {
			throw new RuntimeException(tifrs20140331Dir + " not exists !!!");
		}
		File tifrs20150331Dir = new File(taxonomyDir, "tifrs-20150331");
		if (tifrs20150331Dir.exists() == false) {
			throw new RuntimeException(tifrs20150331Dir + " not exists !!!");
		}
	}

	/**
	 * Get all element id of presentationIds.
	 * 
	 * @param taxonomyVersion
	 * @param presentationIds
	 * @return
	 * @throws Exception
	 */
	public Set<String> getPresentationElementIds(
			XbrlTaxonomyVersion taxonomyVersion, List<String> presentationIds)
			throws Exception {
		XbrlTaxonomy taxonomy = getTaxonomy(taxonomyVersion);

		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		Set<String> resultSet = new HashSet<String>();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			if (presentationIds.contains(presentId) == false) {
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

	/**
	 * Get json format presentation report. (presentationId can reference
	 * Presentation.Id....
	 * 
	 * @param taxonomyVersion
	 * @param presentationId
	 * @return
	 * @throws Exception
	 */
	public ObjectNode getPresentationJson(XbrlTaxonomyVersion taxonomyVersion,
			String presentationId) throws Exception {
		List<String> presentationIds = new ArrayList<String>(1);
		presentationIds.add(presentationId);
		return getPresentationJson(taxonomyVersion, presentationIds);
	}

	/**
	 * Get json format presentation report. (presentationIds can reference
	 * Presentation.Id....
	 * 
	 * @param taxonomyVersion
	 * @param presentationIds
	 * @return
	 * @throws Exception
	 */
	public ObjectNode getPresentationJson(XbrlTaxonomyVersion taxonomyVersion,
			List<String> presentationIds) throws Exception {
		XbrlTaxonomy taxonomy = getTaxonomy(taxonomyVersion);

		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		ObjectNode resultNode = objectMapper.createObjectNode();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			if (presentationIds.contains(presentId) == false) {
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

	XbrlTaxonomy getTaxonomy(File instanceFile) throws Exception {
		XbrlTaxonomyVersion version = getXbrlTaxonomyVersion(instanceFile);
		return cache.getTaxonomy(taxonomyDir, version);
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
				generatePresentationJsonObjectContent(objNode,
						childPresentNode, taxonomy);
				childPresentNode = (XbrlPresentationTreeNode) childPresentNode
						.getNextSibling();
			} while (childPresentNode != null);
		}
	}

	private XbrlTaxonomy getTaxonomy(XbrlTaxonomyVersion version)
			throws Exception {
		return cache.getTaxonomy(taxonomyDir, version);
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

}
