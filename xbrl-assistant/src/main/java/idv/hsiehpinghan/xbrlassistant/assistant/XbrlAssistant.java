package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.exception.SaxParserBreakException;
import idv.hsiehpinghan.xbrlassistant.handler.SchemaReferenceHandler;
import idv.hsiehpinghan.xbrlassistant.xbrl.Instance;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.data.XbrlElement;
import jcx.xbrl.data.XbrlPeriod;
import jcx.xbrl.data.XbrlTreeNode;
import jcx.xbrl.taxonomy.XbrlPresentationTree;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class XbrlAssistant {
	public static final String EN = "en";
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private XbrlTaxonomy tifrsCiCr20130331;

	/**
	 * Get presentation report as json format. (PresentIds can reference Presentation.Id....) 
	 * @param instanceFile
	 * @param presentIds
	 * @return
	 * @throws Exception
	 */
	public ObjectNode getPresentationJson(File instanceFile,
			List<String> presentIds) throws Exception {
		XbrlTaxonomy taxonomy = getXbrlTaxonomy(instanceFile);
		XbrlDocument document = loadXbrlDocument(instanceFile);

		@SuppressWarnings("unchecked")
		Vector<String> presents = taxonomy.getPresentationList();
		ObjectNode objNode = objectMapper.createObjectNode();
		for (int i = 0, size = presents.size(); i < size; ++i) {
			// Get presentation node roleURI. (ex :
			// http://www.xbrl.org/tifrs/fr/role/BalanceSheet)
			String presentId = presents.get(i);
			if (presentIds.contains(presentId) == false) {
				continue;
			}
			ObjectNode presentNod = objectMapper.createObjectNode();
			objNode.set(presentId, presentNod);
			XbrlPresentationTree[] presentTrees = taxonomy
					.getPresentationTree(presentId);
			for (XbrlPresentationTree tree : presentTrees) {
				// Get root node. (ex :
				// ifrs_StatementOfComprehensiveIncomeAbstract)
				XbrlTreeNode rootNode = tree.getRootNode();
				generateJsonObjectContent(presentNod, rootNode, taxonomy,
						document);
			}
		}

		return objNode;
	}

	XbrlTaxonomy getXbrlTaxonomy(File instanceFile)
			throws ParserConfigurationException, SAXException, IOException {
		XbrlTaxonomyVersion version = getXbrlTaxonomyVersion(instanceFile);
		switch (version) {
		case TIFRS_CI_CR_2013_03_31:
			return tifrsCiCr20130331;
		}
		throw new RuntimeException("XbrlTaxonomy version undefined");
	}

	XbrlDocument loadXbrlDocument(File instanceFile) throws Exception {
		XbrlDocument xDoc = new XbrlDocument();
		xDoc.load(getXbrlTaxonomy(instanceFile), new FileInputStream(
				instanceFile));
		return xDoc;
	}

	XbrlTaxonomyVersion getXbrlTaxonomyVersion(File instanceFile)
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
	
	private void generateJsonObjectContent(ObjectNode parentObjNode,
			XbrlTreeNode treeNode, XbrlTaxonomy taxonomy, XbrlDocument document)
			throws Exception {
		String elementId = treeNode.getID();
		ObjectNode objNode = objectMapper.createObjectNode();
		parentObjNode.set(elementId, objNode);
		objNode.put(Presentation.Attribute.CHINESE_LABEL,
				taxonomy.getLabelByID(elementId));
		objNode.put(Presentation.Attribute.ENGLISH_LABEL,
				taxonomy.getLabelByID(elementId, EN));
		objNode.put(Presentation.Attribute.ORDER, treeNode.getOrder());
		generateJsonValueContent(document, elementId, objNode);
		if (treeNode.hasChild()) {
			XbrlTreeNode childTreeNode = treeNode.getFirstChild();
			do {
				ObjectNode childObjNode = objectMapper.createObjectNode();
				String childElementId = childTreeNode.getID();
				objNode.set(childElementId, childObjNode);
				generateJsonObjectContent(childObjNode, childTreeNode,
						taxonomy, document);
				childTreeNode = childTreeNode.getNextSibling();
			} while (childTreeNode != null);
		}
	}

	private void generateJsonValueContent(XbrlDocument document,
			String elementId, ObjectNode objNode) {
		ObjectNode valuesObjNode = objectMapper.createObjectNode();
		XbrlElement[] eles = document.getAllItems(elementId);
		if (eles.length <= 0) {
			return;
		}
		for (XbrlElement ele : eles) {
			ObjectNode valueObjNode = objectMapper.createObjectNode();
			valueObjNode.put(Instance.Attribute.VALUE, ele.getValue());
			valueObjNode.put(Instance.Attribute.UNIT, ele.getUnit());
			setPeriodValue(ele, valueObjNode);
			valuesObjNode.set(ele.getContext().getID(), valueObjNode);
		}
		objNode.set(Instance.Customization.VALUES, valuesObjNode);
	}

	private void setPeriodValue(XbrlElement element, ObjectNode valueObjNode) {
		XbrlPeriod period = element.getContext().getPeriod();
		String periodType = period.getPeriodType();
		valueObjNode.put(Instance.Attribute.PERIOD_TYPE, periodType);
		if (Instance.Attribute.INSTANT.equals(periodType)) {
			valueObjNode.put(Instance.Attribute.INSTANT,
					period.getInstantDateString());
		} else if (Instance.Attribute.DURATION.equals(periodType)) {
			valueObjNode.put(Instance.Attribute.START_DATE,
					period.getStartDateString());
			valueObjNode.put(Instance.Attribute.END_DATE,
					period.getEndDateString());
		} else {
			throw new RuntimeException("Unknown period type !!!");
		}
	}

}
