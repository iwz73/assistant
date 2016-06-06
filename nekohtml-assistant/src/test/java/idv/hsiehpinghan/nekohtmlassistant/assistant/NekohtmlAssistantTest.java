package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import idv.hsiehpinghan.nekohtmlassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NekohtmlAssistantTest extends AbstractTestNGSpringContextTests {
	private Document doc;
	@Autowired
	private NekohtmlAssistant assistant;

	@BeforeClass
	public void beforeClass() throws SAXException, IOException {
		InputStream inputStream = new ClassPathResource("html/nekohtml_index.html").getInputStream();
		InputSource inputSource = new InputSource(inputStream);
		doc = assistant.getDocument(inputSource);
	}

	// @Test
	public void getBody() {
		Assert.assertEquals(assistant.getElementsByTagName(doc, "body").getLength(), 1);
	}

	@Test
	public void getElementVosString() {
		System.err.println(assistant.getElementVosString(doc));
	}

}
