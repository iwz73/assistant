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

	public NodeList getElementsByTagName(Document document, String tagName) {
		return document.getElementsByTagName(tagName);
	}

	public Document removeElementsByNodeName(Document document, String nodeName) {

		// System.err.println(document.normalize());

		return null;
	}

	public ElementVo generateElementVo(Node node) {
		String serialNodeName = generateSerialNodeName(node);
		String serialIndex = generateSerialIndex(node);
		String serialClass = generateSerialClass(node);
		String visibleText = generateVisibleText(node);
		return new ElementVo(serialNodeName, serialIndex, serialClass, visibleText);
	}

	public String getElementVosString(Node node) {
		StringBuilder sb = new StringBuilder();
		appendSubNodeElementVoString(sb, node);
		return sb.toString();
	}

	public String getHtmlString(Node node) {
		StringBuilder sb = new StringBuilder();
		appendSubNodeHtmlString(sb, node);
		return sb.toString();
	}
	
	private void appendSubNodeHtmlString(StringBuilder sb, Node node) {
		short nodeType = node.getNodeType();
		switch (nodeType) {
		case Node.DOCUMENT_TYPE_NODE:
			appendSubDocumentTypeNodeHtmlString(sb, node);
			break;
		case Node.COMMENT_NODE:
			appendSubCommentNodeHtmlString(sb, node);
			break;
		case Node.ELEMENT_NODE:
//			appendSubElementNodeHtmlString(sb, node);
//			break;			
		case Node.DOCUMENT_NODE:
		case Node.TEXT_NODE:
		case Node.ATTRIBUTE_NODE:
		case Node.CDATA_SECTION_NODE:
		case Node.ENTITY_REFERENCE_NODE:
		case Node.ENTITY_NODE:
		case Node.PROCESSING_INSTRUCTION_NODE:
		case Node.DOCUMENT_FRAGMENT_NODE:
		case Node.NOTATION_NODE:
		default:
			break;
//			throw new RuntimeException("nodeType(" + nodeType + ") not implements !!!");
		}
	}
	
	private void appendSubDocumentTypeNodeHtmlString(StringBuilder sb, Node node) {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			appendSubNodeHtmlString(sb, nodeList.item(i));
		}
	}

	private void appendSubCommentNodeHtmlString(StringBuilder sb, Node node) {
		
		System.err.println(node.getTextContent());
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			appendSubNodeHtmlString(sb, nodeList.item(i));
		}
	}
	
	private void appendSubElementNodeHtmlString(StringBuilder sb, Node node) {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
			appendSubNodeHtmlString(sb, nodeList.item(i));
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

}
