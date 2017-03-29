package idv.hsiehpinghan.xmlassistant.assistant;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import idv.hsiehpinghan.xmlassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DocumentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private DocumentAssistant assistant;

	@Test
	public void parse() throws IOException, ParserConfigurationException, SAXException {
		File file = resourceLoader
				.getResource("classpath:/xbrl/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd")
				.getFile();
		boolean namespaceAware = true;
		Document doc = assistant.parse(file, namespaceAware);
		Assert.assertNotNull(doc);
	}

	@Test
	public void generateDocument() throws ParserConfigurationException {
		boolean namespaceAware = true;
		Document doc = assistant.generateDocument(namespaceAware);
		Assert.assertNotNull(doc);
	}
}
