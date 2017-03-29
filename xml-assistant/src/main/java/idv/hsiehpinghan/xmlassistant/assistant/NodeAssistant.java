package idv.hsiehpinghan.xmlassistant.assistant;

import javax.xml.soap.Node;

import org.springframework.stereotype.Component;

@Component
public class NodeAssistant {

	public String getNodeTypeName(short nodeType) {
		switch (nodeType) {
		case Node.ELEMENT_NODE:
			return "ELEMENT_NODE";
		case Node.ATTRIBUTE_NODE:
			return "ATTRIBUTE_NODE";
		case Node.TEXT_NODE:
			return "TEXT_NODE";
		case Node.CDATA_SECTION_NODE:
			return "CDATA_SECTION_NODE";
		case Node.ENTITY_REFERENCE_NODE:
			return "ENTITY_REFERENCE_NODE";
		case Node.ENTITY_NODE:
			return "ENTITY_NODE";
		case Node.PROCESSING_INSTRUCTION_NODE:
			return "PROCESSING_INSTRUCTION_NODE";
		case Node.COMMENT_NODE:
			return "COMMENT_NODE";
		case Node.DOCUMENT_NODE:
			return "DOCUMENT_NODE";
		case Node.DOCUMENT_TYPE_NODE:
			return "DOCUMENT_TYPE_NODE";
		case Node.DOCUMENT_FRAGMENT_NODE:
			return "DOCUMENT_FRAGMENT_NODE";
		case Node.NOTATION_NODE:
			return "NOTATION_NODE";
		default:
			throw new RuntimeException("nodeType(" + nodeType + ") not implements !!!");
		}
	}
}
