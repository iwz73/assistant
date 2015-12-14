package idv.hsiehpinghan.xmlassistant.utility;

import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

public class DocumentUtilityTest {

	@Test
	public void parse() throws Exception {
		File file = SystemResourceUtility
				.getFileResource("xbrl/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd");
		Document document = DocumentUtility.parse(file);
		Assert.assertNotNull(document);

	}
}
