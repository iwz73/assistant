package idv.hsiehpinghan.seleniumassistant.webelement;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectTest {
	private Select select;

	@BeforeClass
	public void beforeClass() throws IOException {
		// ApplicationContext applicationContext = TestngSuitSetting
		// .getApplicationContext();
		HtmlUnitBrowser browser = TestngSuitSetting.getHtmlUnitBrowser();
		browser.browse(TestngSuitSetting.URL_BASE + "html/selenium_index.html");
		select = browser.getSelect(By.cssSelector("#selectId"));
	}

	@Test
	public void getOptions() {
		List<Option> options = select.getOptions();
		Assert.assertEquals(3, options.size());
	}

}
