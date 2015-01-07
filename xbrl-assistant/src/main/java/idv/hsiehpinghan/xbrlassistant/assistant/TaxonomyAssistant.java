package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.compressutility.utility.CompressUtility;
import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.cache.TaxonomyCache;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.exception.SaxParserBreakException;
import idv.hsiehpinghan.xbrlassistant.handler.SchemaReferenceHandler;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class TaxonomyAssistant implements InitializingBean {
	private final String EN = "en";
	private Logger logger = Logger.getLogger(this.getClass().getName());

	private File taxonomyDir;

	@Autowired
	private InstanceAssistant instanceAssistant;
	@Autowired
	private TaxonomyCache cache;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		String dStr = "xbrl-assistant.taxonomy_dir";
		String dProp = environment.getProperty(dStr);
		if (dProp == null) {
			throw new RuntimeException(dStr + " not set !!!");
		}
		taxonomyDir = new File(dProp);
		exportTaxonomys();
	}

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

	XbrlTaxonomy getTaxonomy(File instanceFile) throws Exception {
		XbrlTaxonomyVersion version = getXbrlTaxonomyVersion(instanceFile);
		return cache.getTaxonomy(taxonomyDir, version);
	}

	void exportTaxonomys() {
		exportTaxonomy("xbrl-taxonomy/tifrs-20130331.zip");
		exportTaxonomy("xbrl-taxonomy/tifrs-20140331.zip");
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

	private XbrlTaxonomy getTaxonomy(XbrlTaxonomyVersion version)
			throws Exception {
		return cache.getTaxonomy(taxonomyDir, version);
	}

	private void exportTaxonomy(String resourcePath) {
		InputStream in = null;
		File zip = null;
		File dir = null;
		try {
			in = ResourceUtility.getResourceAsStream(resourcePath);
			String resName = getResourceName(resourcePath);
			zip = new File(FileUtils.getTempDirectory(), resName);
			if (zip.exists() == false) {
				FileUtils.copyInputStreamToFile(in, zip);
			}
			dir = CompressUtility.unzip(zip, taxonomyDir, false);
			logger.info("Taxonomy(" + resName + ") export to "
					+ dir.getAbsolutePath() + " success.");
		} catch (Exception e) {
			FileUtils.deleteQuietly(dir);
			FileUtils.deleteQuietly(zip);
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	private String getResourceName(String resourcePath) {
		int idx = resourcePath.lastIndexOf("/");
		return resourcePath.substring(idx + 1);
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
