package idv.hsiehpinghan.seleniumassistant.webelement;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectTest {
	private MockHtmlServer htmlServer;
	private Select select;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		htmlServer = applicationContext.getBean(MockHtmlServer.class);
		HtmlUnitBrowser htmlUnitBrowser = applicationContext
				.getBean(HtmlUnitBrowser.class);
		htmlUnitBrowser.browse("http://127.0.0.1:8080/html/selenium_index.html");
		select = htmlUnitBrowser.getSelect(By.cssSelector("#selectId"));
	}

	@Test
	public void getOptions() {
		List<Option> options = select.getOptions();
		Assert.assertEquals(3, options.size());
	}

	@AfterClass
	public void afterClass() {
		htmlServer.stop();
	}
}
