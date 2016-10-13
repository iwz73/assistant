package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;

import org.cyberneko.html.parsers.DOMParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import idv.hsiehpinghan.nekohtmlassistant.vo.ElementVo;

@Component
public class NekohtmlAssistant {
	private static final String NODE_NAME = "node_name";
	private static final String INDEX = "index";
	private static final String CLAZZ = "class";

	public Document getDocument(InputSource inputSource) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(inputSource);
		return parser.getDocument();
	}

	public NodeList getElementsByTagName(Document document, String tagName) {
		return document.getElementsByTagName(tagName);
	}

	public ElementVo generateElementVo(Node node) {
		String serialAncestorNodeName = generateSerialAncestorNodeName(node);
		String serialAncestorIndex = generateSerialAncestorIndex(node);
		String serialAncestorClass = generateSerialAncestorClass(node);
		String serialPosterityNodeName = generateSerialPosterityNodeName(node);
		String serialPosterityIndex = generateSerialPosterityIndex(node);
		String serialPosterityClass = generateSerialPosterityClass(node);
		String visibleText = generateVisibleText(node);
		return new ElementVo(serialAncestorNodeName, serialAncestorIndex, serialAncestorClass, serialPosterityNodeName,
				serialPosterityIndex, serialPosterityClass, visibleText);
	}

	public String getElementVosString(Node node) {
		StringBuilder sb = new StringBuilder();
		appendSubNodeElementVoString(sb, node);
		return sb.toString();
	}

	public String getHtmlString(Node node) {
		StringBuilder sb = new StringBuilder();
		appendSubHtmlString(sb, node);
		return sb.toString();
	}

	public String getHtmlStructureId(Node node) {
		Node bodyNode = getBodyNode(node);
		StringBuilder sb = new StringBuilder();
		appendSubHtmlStructureId(sb, bodyNode, 0);
		return sb.toString();
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

	private boolean isIgnore(String nodeName) {
		switch (nodeName) {
		case "#text":
		case "#comment":
		case "SCRIPT":
		case "NOSCRIPT":
		case "FONT":
		case "STRONG":
		case "BR":
		case "EM":
			return true;
		default:
			return false;
		}
	}

	private Node getBodyNode(Node node) {
		if (node.getNodeName().equalsIgnoreCase("BODY")) {
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

	private void appendSubNodeElementVoString(StringBuilder sb, Node node) {
		short nodeType = node.getNodeType();
		switch (nodeType) {
		case Node.ELEMENT_NODE:
		case Node.DOCUMENT_NODE:
			ElementVo vo = generateElementVo(node);
			sb.append(vo);
			sb.append(System.lineSeparator());
			NodeList nodeList = node.getChildNodes();
			for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
				appendSubNodeElementVoString(sb, nodeList.item(i));
			}
			break;
		case Node.TEXT_NODE:
		case Node.COMMENT_NODE:
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

	private String generateSerialAncestorNodeName(Node node) {
		StringBuilder sb = new StringBuilder();
		appendAncestorNodeName(sb, node);
		return sb.toString();
	}

	private String generateSerialPosterityNodeName(Node node) {
		StringBuilder sb = new StringBuilder();
		appendPosterity(sb, node, NODE_NAME);
		return sb.toString();
	}

	private String generateSerialPosterityIndex(Node node) {
		StringBuilder sb = new StringBuilder();
		appendPosterity(sb, node, INDEX);
		return sb.toString();
	}

	private String generateSerialPosterityClass(Node node) {
		StringBuilder sb = new StringBuilder();
		appendPosterity(sb, node, CLAZZ);
		return sb.toString();
	}

	private void appendAncestorNodeName(StringBuilder sb, Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			appendAncestorNodeName(sb, parentNode);
		}
		sb.append(node.getNodeName());
		sb.append(",");
	}

	private void appendPosterity(StringBuilder sb, Node node, String type) {
		if (NODE_NAME.equals(type)) {
			sb.append(node.getNodeName());
			sb.append(",");
		} else if (INDEX.equals(type)) {
			sb.append(getNodeIndex(node));
			sb.append(",");
		} else if (CLAZZ.equals(type)) {
			sb.append(getNodeClass(node));
			sb.append(",");
		} else {
			throw new RuntimeException("type(" + type + ") not implements !!!");
		}
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			Node childNode = nodeList.item(i);
			short nodeType = childNode.getNodeType();
			switch (nodeType) {
			case Node.TEXT_NODE:
			case Node.COMMENT_NODE:
			case Node.ATTRIBUTE_NODE:
			case Node.CDATA_SECTION_NODE:
				continue;
			case Node.ELEMENT_NODE:
			case Node.DOCUMENT_NODE:
			case Node.ENTITY_REFERENCE_NODE:
			case Node.ENTITY_NODE:
			case Node.PROCESSING_INSTRUCTION_NODE:
			case Node.DOCUMENT_TYPE_NODE:
			case Node.DOCUMENT_FRAGMENT_NODE:
			case Node.NOTATION_NODE:
				appendPosterity(sb, childNode, type);
				break;
			default:
				throw new RuntimeException("nodeType(" + nodeType + ") not implements !!!");
			}
		}
	}

	private String generateSerialAncestorIndex(Node node) {
		StringBuilder sb = new StringBuilder();
		appendAncestorIndex(sb, node);
		return sb.toString();
	}

	private void appendAncestorIndex(StringBuilder sb, Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			appendAncestorIndex(sb, parentNode);
		}
		sb.append(getNodeIndex(node));
		sb.append(",");
	}

	private String generateSerialAncestorClass(Node node) {
		StringBuilder sb = new StringBuilder();
		appendAncestorClass(sb, node);
		return sb.toString();
	}

	private void appendAncestorClass(StringBuilder sb, Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			appendAncestorClass(sb, parentNode);
		}
		NamedNodeMap nameNodeMap = node.getAttributes();
		if (nameNodeMap == null) {
			return;
		}
		Node classNode = nameNodeMap.getNamedItem("class");
		if (classNode == null) {
			return;
		}
		String classValue = classNode.getNodeValue();
		if (classValue != null) {
			sb.append(classValue);
			sb.append(",");
		}
	}

	private String getNodeClass(Node node) {
		NamedNodeMap nameNodeMap = node.getAttributes();
		if (nameNodeMap == null) {
			return "";
		}
		Node classNode = nameNodeMap.getNamedItem("class");
		if (classNode == null) {
			return "";
		}
		return classNode.getNodeValue();
	}

	private String generateVisibleText(Node node) {
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

	private int getNodeIndex(Node node) {
		Node tempNode = node;
		int index = 0;
		while ((tempNode = tempNode.getPreviousSibling()) != null) {
			++index;
		}
		return index;
	}

}
