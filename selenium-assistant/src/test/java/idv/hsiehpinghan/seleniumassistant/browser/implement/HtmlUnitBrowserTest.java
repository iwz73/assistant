package idv.hsiehpinghan.seleniumassistant.browser.implement;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HtmlUnitBrowserTest {
	private HtmlUnitBrowser htmlUnitBrowser;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		htmlUnitBrowser = applicationContext.getBean(HtmlUnitBrowser.class);
	}

	@Test
	public void browse() {
		File f = ResourceUtility.getFileResource("html/index.html");
		htmlUnitBrowser.browse(f.getAbsolutePath());
		System.out.println(htmlUnitBrowser.getHtmlUnitDriver().getPageSource());
	}
}
