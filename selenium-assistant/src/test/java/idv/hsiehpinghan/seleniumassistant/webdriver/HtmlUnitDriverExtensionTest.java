package idv.hsiehpinghan.seleniumassistant.webdriver;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import java.io.IOException;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HtmlUnitDriverExtensionTest {
	private HtmlUnitDriverExtension htmlUnitDriver;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		 ApplicationContext applicationContext = TestngSuitSetting
		 .getApplicationContext();
		 htmlUnitDriver = applicationContext.getBean(HtmlUnitDriverExtension.class);
	}

	@Test
	public void getFileName() {
		String str = "attachment; filename=\"2013-01-otc-02-C.zip\"";
		String result = htmlUnitDriver.getFileName(str);
		Assert.assertEquals("2013-01-otc-02-C.zip", result);
	}
}
