package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;

import org.cyberneko.html.parsers.DOMParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
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
		// String serialNodeName = generateSerialNodeName(node);
		// String serialIndex = generateSerialIndex(node);
		String visibleText = generateVisibleText(node);

		System.err.println(visibleText);

		// System.err.println(node.get);
		// short nodeType = node.getNodeType();
		// switch (nodeType) {
		// case Node.ELEMENT_NODE:
		// ElementVo vo = generateElementVo((Element)node, parentVo);
		// System.err.println(vo.getText());
		// return vo;
		// case Node.TEXT_NODE:
		// case Node.ATTRIBUTE_NODE:
		// case Node.CDATA_SECTION_NODE:
		// case Node.ENTITY_REFERENCE_NODE:
		// case Node.ENTITY_NODE:
		// case Node.PROCESSING_INSTRUCTION_NODE:
		// case Node.COMMENT_NODE:
		// case Node.DOCUMENT_NODE:
		// case Node.DOCUMENT_TYPE_NODE:
		// case Node.DOCUMENT_FRAGMENT_NODE:
		// case Node.NOTATION_NODE:
		// break;
		// default:
		// throw new RuntimeException("nodeType(" + nodeType + ") not implements
		// !!!");
		// }

		// System.err.println(node.getChildNodes().item(9));
		// System.err.println(node.getAttributes().getNamedItem("bgcolor"));
		// NodeList nodeList = node.getChildNodes();
		// for(int i = 0, size = nodeList.getLength(); i < size; ++i) {
		// System.err.println(nodeList.item(i));
		// }
		return null;
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

	private String generateVisibleText(Node node) {
		StringBuilder sb = new StringBuilder();
		appendChildrenVisibleText(sb, node);
		return sb.toString();
	}

	private void appendChildrenVisibleText(StringBuilder sb, Node node) {
		short nodeType = node.getNodeType();
		switch (nodeType) {
		case Node.TEXT_NODE:
			sb.append(node.getTextContent().trim());
			break;
		case Node.ELEMENT_NODE:
			NodeList nodeList = node.getChildNodes();
			for (int i = 0, size = nodeList.getLength(); i < size; ++i) {
				appendChildrenVisibleText(sb, nodeList.item(i));
			}
			break;
		case Node.COMMENT_NODE:
			// do nothing.
			break;
		case Node.ATTRIBUTE_NODE:
		case Node.CDATA_SECTION_NODE:
		case Node.ENTITY_REFERENCE_NODE:
		case Node.ENTITY_NODE:
		case Node.PROCESSING_INSTRUCTION_NODE:
		case Node.DOCUMENT_NODE:
		case Node.DOCUMENT_TYPE_NODE:
		case Node.DOCUMENT_FRAGMENT_NODE:
		case Node.NOTATION_NODE:
		default:
			throw new RuntimeException("nodeType(" + nodeType + ") not implements !!!");
		}
	}
}
