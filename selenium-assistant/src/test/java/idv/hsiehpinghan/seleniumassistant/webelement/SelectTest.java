package idv.hsiehpinghan.seleniumassistant.webelement;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitWithJavascriptBrowser;
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
		HtmlUnitWithJavascriptBrowser htmlUnitWithJavascriptBrowser = TestngSuitSetting
				.getHtmlUnitWithJavascriptBrowser();
		htmlUnitWithJavascriptBrowser.browse(TestngSuitSetting.URL_BASE
				+ "html/selenium_index.html");
		select = htmlUnitWithJavascriptBrowser.getSelect(By
				.cssSelector("#selectId"));
	}

	@Test
	public void getOptions() {
		List<Option> options = select.getOptions();
		Assert.assertEquals(3, options.size());
	}

}
