package idv.hsiehpinghan.xmlassistant.assistant;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Component
public class DocumentAssistant {

	public Document parse(File file, boolean namespaceAware)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(namespaceAware);
		DocumentBuilder localDocumentBuilder = documentBuilderFactory.newDocumentBuilder();
		return localDocumentBuilder.parse(file);
	}

	public Document generateDocument(boolean namespaceAware) throws ParserConfigurationException {
		DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		localDocumentBuilderFactory.setNamespaceAware(namespaceAware);
		DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
		return localDocumentBuilder.newDocument();
	}

}
