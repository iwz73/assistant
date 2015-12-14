package idv.hsiehpinghan.xmlassistant.utility;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DocumentUtility {
	public static Document parse(File file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder localDocumentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		return localDocumentBuilder.parse(file);
	}
}
