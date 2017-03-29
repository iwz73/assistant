package idv.hsiehpinghan.xmlassistant.utility;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import idv.hsiehpinghan.xmlassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DocumentUtilityTest extends AbstractTestNGSpringContextTests {
	private Document document;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Test
	public void parse() throws Exception {
		File file = resourceLoader
				.getResource("classpath:/xbrl/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd").getFile();
//		File file = SystemResourceUtility
//				.getFileResource("xbrl/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd");
		Document document = DocumentUtility.parse(file);

		System.err.println(document.getDocumentElement().getFirstChild());
		
		Assert.assertNotNull(document);

	}

}
