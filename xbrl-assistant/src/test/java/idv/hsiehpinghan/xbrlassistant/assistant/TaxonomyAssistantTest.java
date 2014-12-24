package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.suit.TestngSuitSetting;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import jcx.xbrl.taxonomy.XbrlTaxonomy;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class TaxonomyAssistantTest {
	private TaxonomyAssistant taxonomyAssistant;
	private XbrlTaxonomy tifrsCiCr20130331;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		taxonomyAssistant = applicationContext.getBean(TaxonomyAssistant.class);
		tifrsCiCr20130331 = (XbrlTaxonomy)applicationContext.getBean("tifrsCiCr20130331");
	}

	@Test
	public void getXbrlTaxonomy() throws ParserConfigurationException,
			SAXException, IOException {
		String instancePath = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		File instanceFile = ResourceUtility.getFileResource(instancePath);
		XbrlTaxonomy taxonomy = taxonomyAssistant.getXbrlTaxonomy(instanceFile);
		Assert.assertSame(taxonomy, tifrsCiCr20130331);
	}

}
