package idv.hsiehpinghan.seleniumassistant.utility;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.seleniumassistant.browser.BrowserBase;
import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class AjaxWaitUtilityTest {
	private static final String URL_BASE = "http://127.0.0.1:8080/";
	private MockHtmlServer htmlServer;
	private BrowserBase browser;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		browser = applicationContext.getBean(HtmlUnitBrowser.class,
				BrowserVersion.FIREFOX_24, true, false);
		htmlServer = TestngSuitSetting.getHtmlServer();
	}

	@Test
	public void waitUntilOptionsDifferent() {
		browser.browse(URL_BASE + "html/selenium_index.html");
		Select s = browser.getSelect(By.cssSelector("#selectId"));
		Select s2 = browser.getSelect(By.cssSelector("#selectId_2"));
		Assert.assertTrue(AjaxWaitUtility.waitUntilOptionsDifferent(s2,
				s.getOptions()));
	}

	@Test(expectedExceptions = { TimeoutException.class })
	public void waitUntilOptionsDifferent2() {
		browser.browse(URL_BASE + "html/selenium_index.html");
		Select s = browser.getSelect(By.cssSelector("#selectId"));
		Select s3 = browser.getSelect(By.cssSelector("#selectId_3"));
		Assert.assertTrue(AjaxWaitUtility.waitUntilOptionsDifferent(s3,
				s.getOptions()));
	}

//	@AfterClass
//	public void afterClass() {
//		htmlServer.stop();
//	}
}
