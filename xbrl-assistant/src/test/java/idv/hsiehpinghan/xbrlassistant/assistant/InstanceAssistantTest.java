package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.suit.TestngSuitSetting;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class InstanceAssistantTest {
	private InstanceAssistant instanceAssistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		instanceAssistant = applicationContext.getBean(InstanceAssistant.class);
	}

	@Test
	public void getXbrlTaxonomyVersion() throws ParserConfigurationException,
			SAXException, IOException {
		String instancePath = "xbrl-instance/2013-01-sii-01-C/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml";
		File instanceFile = ResourceUtility.getFileResource(instancePath);
		XbrlTaxonomyVersion version = instanceAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		Assert.assertEquals(XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31, version);
	}

}
