package idv.hsiehpinghan.phantomjsdriverassistant.browser;

import org.openqa.selenium.WebDriver;

import idv.hsiehpinghan.phantomjsdriverassistant.webdriver.PhantomJSDriverExtension;

public class PhantomJSBrowser extends BrowserBase {
	private PhantomJSDriverExtension webDriver = new PhantomJSDriverExtension();

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
