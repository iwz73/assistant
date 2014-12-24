package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.seleniumassistant.browser.BrowserBase;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;

import org.openqa.selenium.By;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public abstract class BrowserTest {
	private static final String URL_BASE = "http://127.0.0.1:8080/";
	private BrowserBase browser;
	private MockHtmlServer htmlServer;

	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		htmlServer = applicationContext.getBean(MockHtmlServer.class);
	}

	@Test
	public void browse() {
		browser.browse(URL_BASE + "html/selenium_index.html");
		Assert.assertEquals("Index page", browser.getWebDriver().getTitle());
	}

	@Test
	public void getSelect() {
		Select select = browser.getSelect(By.cssSelector("#selectId"));
		Assert.assertNotNull(select);
	}

	@AfterClass
	public void afterClass() {
		htmlServer.stop();
	}

	public void setBrowser(BrowserBase browser) {
		this.browser = browser;
	}

}
