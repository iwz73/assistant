package idv.hsiehpinghan.seleniumassistant.webelement;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectTest {
	private HtmlUnitBrowser browser;

//	@BeforeClass
//	public void beforeClass() throws IOException {
//		ApplicationContext applicationContext = TestngSuitSetting
//				.getApplicationContext();
//		browser = applicationContext.getBean(HtmlUnitBrowser.class);
//		browser.browse(TestngSuitSetting.URL_BASE + "html/selenium_index.html");
//
//	}
//
//	@Test
//	public void getOptions() {
//		Select select = browser.getSelect(By.cssSelector("#selectId"));
//		List<Option> options = select.getOptions();
//		Assert.assertEquals(3, options.size());
//	}

}
