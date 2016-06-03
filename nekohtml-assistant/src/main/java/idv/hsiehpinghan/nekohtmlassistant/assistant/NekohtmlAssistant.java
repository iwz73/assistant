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

	public Document getDocument(InputSource inputSource) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(inputSource);
		return parser.getDocument();
	}

	public NodeList getElementsByTagName(Document document, String tagname) {
		return document.getElementsByTagName(tagname);
	}

	public ElementVo generateElementVo(Node node) {
		String serialNodeName = generateSerialNodeName(node);
		String serialIndex = generateSerialIndex(node);
		String serialClass = generateSerialClass(node);
		String visibleText = generateVisibleText(node);
		return new ElementVo(serialNodeName, serialIndex, serialClass, visibleText);
	}

	private String generateSerialNodeName(Node node) {
		StringBuilder sb = new StringBuilder();
		appendAncestorNodeName(sb, node);
		return sb.toString();
	}

	private void appendAncestorNodeName(StringBuilder sb, Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			appendAncestorNodeName(sb, parentNode);
		}
		sb.append(node.getNodeName());
	}

	private String generateSerialIndex(Node node) {
		StringBuilder sb = new StringBuilder();
		appendAncestorIndex(sb, node);
		return sb.toString();
	}

	private void appendAncestorIndex(StringBuilder sb, Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			appendAncestorIndex(sb, parentNode);
		}
		Node tempNode = node;
		int index = 0;
		while ((tempNode = tempNode.getPreviousSibling()) != null) {
			++index;
		}
		sb.append(index);
	}

	private String generateSerialClass(Node node) {
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
		}
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

	// private void appendChildrenVisibleText(StringBuilder sb, Node node) {
	// short nodeType = node.getNodeType();
	// switch (nodeType) {
	// case Node.TEXT_NODE:
	// sb.append(node.getTextContent().trim());
	// break;
	// case Node.ELEMENT_NODE:
	// NodeList nodeList = node.getChildNodes();
	// for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
	// appendChildrenVisibleText(sb, nodeList.item(i));
	// }
	// break;
	// case Node.COMMENT_NODE:
	// // do nothing.
	// break;
	// case Node.ATTRIBUTE_NODE:
	// case Node.CDATA_SECTION_NODE:
	// case Node.ENTITY_REFERENCE_NODE:
	// case Node.ENTITY_NODE:
	// case Node.PROCESSING_INSTRUCTION_NODE:
	// case Node.DOCUMENT_NODE:
	// case Node.DOCUMENT_TYPE_NODE:
	// case Node.DOCUMENT_FRAGMENT_NODE:
	// case Node.NOTATION_NODE:
	// default:
	// throw new RuntimeException("nodeType(" + nodeType + ") not implements
	// !!!");
	// }
	// }
}
