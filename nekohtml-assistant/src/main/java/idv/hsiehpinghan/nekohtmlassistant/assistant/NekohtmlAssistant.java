package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cyberneko.html.parsers.DOMParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Component
public class NekohtmlAssistant {
	private final String BODY = "BODY";
	private final String SEPERATOR = "_";
	private final String THANK_ID = "thankId";

	public Map<String, Integer> getNodeNameCountMap(Document doc) {
		Map<String, Integer> map = new HashMap<>();
		Node bodyNode = getBodyNode(doc);
		addNodeNameCount(map, bodyNode);
		return map;
	}

	public String getAttribute(Node node, String attributeName) {
		NamedNodeMap map = node.getAttributes();
		for (int i = 0, size = map.getLength(); i < size; ++i) {
			Node attrNode = map.item(i);
			if (attrNode.getNodeName().equals(attributeName)) {
				return attrNode.getNodeValue();
			}
		}
		return null;
	}

	public Document getDocument(InputSource inputSource) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(inputSource);
		return parser.getDocument();
	}

	public Element getElementById(Document document, String elementId) {
		return document.getElementById(elementId);
	}

	public NodeList getElementsByTagName(Document document, String tagName) {
		return document.getElementsByTagName(tagName);
	}

	public String getHtmlString(Node node) {
		StringBuilder sb = new StringBuilder();
		appendSubHtmlString(sb, node);
		return sb.toString();
	}

	public String getHtmlStructureId(Document doc) {
		Node bodyNode = getBodyNode(doc);
		StringBuilder sb = new StringBuilder();
		appendSubHtmlStructureId(sb, bodyNode, 0);
		return sb.toString();
	}

	public String getElementStructureId(Node node) {
		StringBuilder sb = new StringBuilder();
		prependAncestorIndex(sb, node);
		return sb.toString();
	}

	public Node getNodeByElementStructureId(Document doc, String elementStructureId) {
		Node tempNode = getBodyNode(doc);
		String[] indexes = elementStructureId.split(SEPERATOR);
		for (String str : indexes) {
			int index = Integer.valueOf(str);
			tempNode = getChildNode(tempNode, index);
		}
		return tempNode;
	}

	public String getChildTextNodesText(Node node) {
		StringBuilder sb = new StringBuilder();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			Node subNode = nodeList.item(i);
			if (Node.TEXT_NODE == subNode.getNodeType()) {
				sb.append(subNode.getTextContent().trim());
			}
		}
		return sb.toString();
	}

	public Document addAllElementWithStructureId(Document doc) {
		Node bodyNode = getBodyNode(doc);
		addSubElementsWithStructureId(bodyNode);
		return doc;
	}

	private void addNodeNameCount(Map<String, Integer> map, Node node) {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			Node subNode = nodeList.item(i);
			String nodeName = subNode.getNodeName();
			if (isIgnore(nodeName)) {
				continue;
			}
			Integer count = map.get(nodeName);
			if (count == null) {
				map.put(nodeName, 1);
			} else {
				map.replace(nodeName, count + 1);
			}
			addNodeNameCount(map, subNode);
		}
	}

	private void addSubElementsWithStructureId(Node node) {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			Node subNode = nodeList.item(i);
			if (isIgnore(subNode.getNodeName())) {
				continue;
			}
			Element ele = (Element) subNode;
			ele.setAttribute(THANK_ID, getElementStructureId(ele));
			addSubElementsWithStructureId(subNode);
		}
	}

	private Node getChildNode(Node node, int index) {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, j = 0, size = nodeList.getLength(); i < size; ++i) {
			Node subNode = nodeList.item(i);
			if (isIgnore(subNode.getNodeName())) {
				continue;
			}
			if (index == j) {
				return subNode;
			}
			++j;
		}
		return null;
	}

	private void prependAncestorIndex(StringBuilder sb, Node node) {
		if (node.getNodeName().equals(BODY)) {
			return;
		}
		Node parentNode = node.getParentNode();
		prependAncestorIndex(sb, parentNode);
		int index = getNodeSiblingIndex(node);
		if (sb.length() > 0) {
			sb.append(SEPERATOR);
		}
		sb.append(index);
	}

	private boolean appendSubHtmlStructureId(StringBuilder sb, Node node, int index) {
		String nodeName = node.getNodeName();
		if (isIgnore(nodeName)) {
			return false;
		}
		sb.append(index);
		sb.append(nodeName);
		boolean isSuccess = false;
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, idx = 0, size = nodeList.getLength(); i < size; ++i) {
			isSuccess = appendSubHtmlStructureId(sb, nodeList.item(i), idx);
			if (isSuccess) {
				++idx;
			}
		}
		return true;
	}

	protected boolean isIgnore(String nodeName) {
		switch (nodeName) {
		case "#comment":
		case "#text":
		case "ABBR":
		case "ACRONYM":
		case "APPLET":
		case "AUDIO":
		case "B":
		case "BDI":
		case "BDO":
		case "BIG":
		case "BR":
		case "BUTTON":
		case "CANVAS":
		case "CITE":
		case "DEL":
		case "DFN":
		case "EM":
		case "EMBED":
		case "FONT":
		case "FORM":
		case "FRAME":
		case "FRAMESET":
		case "HR":
		case "I":
		case "IFRAME":
		case "INPUT":
		case "INS":
		case "KBD":
		case "KEYGEN":
		case "MAP":
		case "MARK":
		case "METER":
		case "NOFRAMES":
		case "NOSCRIPT":
		case "OBJECT":
		case "OUTPUT":
		case "PARAM":
		case "PROGRESS":
		case "Q":
		case "RP":
		case "RT":
		case "RUBY":
		case "S":
		case "SAMP":
		case "SCRIPT":
		case "SELECT":
		case "SMALL":
		case "SOURCE":
		case "STRIKE":
		case "STRONG":
		case "STYLE":
		case "SUB":
		case "SUP":
		case "TRACK":
		case "TT":
		case "U":
		case "VAR":
		case "VIDEO":
		case "WBR":
			return true;
		default:
			return false;
		}
	}

	protected Node getBodyNode(Node node) {
		if (node.getNodeName().equalsIgnoreCase(BODY)) {
			return node;
		} else {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
				Node bodyNode = getBodyNode(nodeList.item(i));
				if (bodyNode != null) {
					return bodyNode;
				}
			}
			return null;
		}
	}

	private void appendSubHtmlString(StringBuilder sb, Node node) {
		short nodeType = node.getNodeType();
		NodeList nodeList = null;
		switch (nodeType) {
		case Node.DOCUMENT_NODE:
			nodeList = node.getChildNodes();
			for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
				appendSubHtmlString(sb, nodeList.item(i));
			}
			break;
		case Node.COMMENT_NODE:
			sb.append("<!--");
			sb.append(node.getTextContent());
			sb.append("-->");
			break;
		case Node.ELEMENT_NODE:
			sb.append("<");
			sb.append(node.getNodeName());
			NamedNodeMap map = node.getAttributes();
			for (int i = 0, size = map.getLength(); i < size; ++i) {
				Node attrNode = map.item(i);
				sb.append(" ");
				sb.append(attrNode.getNodeName());
				sb.append("=\"");
				sb.append(attrNode.getNodeValue());
				sb.append("\"");
			}
			sb.append(">");
			nodeList = node.getChildNodes();
			for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
				appendSubHtmlString(sb, nodeList.item(i));
			}
			sb.append("</");
			sb.append(node.getNodeName());
			sb.append(">");
			break;
		case Node.TEXT_NODE:
			sb.append(node.getTextContent());
			break;
		case Node.ATTRIBUTE_NODE:
		case Node.CDATA_SECTION_NODE:
		case Node.ENTITY_REFERENCE_NODE:
		case Node.ENTITY_NODE:
		case Node.PROCESSING_INSTRUCTION_NODE:
		case Node.DOCUMENT_TYPE_NODE:
		case Node.DOCUMENT_FRAGMENT_NODE:
		case Node.NOTATION_NODE:
			break;
		default:
			throw new RuntimeException("nodeType(" + nodeType + ") not implements !!!");
		}
	}

	private int getNodeSiblingIndex(Node node) {
		Node tempNode = node;
		int index = 0;
		while ((tempNode = tempNode.getPreviousSibling()) != null) {
			if (isIgnore(tempNode.getNodeName())) {
				continue;
			}
			++index;
		}
		return index;
	}

}
