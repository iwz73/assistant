package idv.hsiehpinghan.seleniumassistant.webdriver;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import java.io.IOException;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HtmlUnitDriverExtensionTest {
	private HtmlUnitDriverExtension htmlUnitDriver;

	@BeforeClass
	public void beforeClass() throws IOException {
		htmlUnitDriver = TestngSuitSetting.getHtmlUnitDriverExtension();
	}

	@Test
	public void getFileName() {
		String str = "attachment; filename=\"2013-01-otc-02-C.zip\"";
		String result = htmlUnitDriver.getFileName(str);
		Assert.assertEquals("2013-01-otc-02-C.zip", result);
	}
}
