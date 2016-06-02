package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;
import java.util.List;

import org.cyberneko.html.parsers.DOMParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
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

	public List<ElementVo> getAllElementVo(Node node) {

		System.err.println(node.getNodeType());

		return null;
	}
}
