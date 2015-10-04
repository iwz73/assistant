package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.testng.annotations.BeforeClass;

public class FirefoxBrowserTest extends BrowserTest {
	private FirefoxBrowser browser;

	@BeforeClass
	public void beforeClass() {
		browser = new FirefoxBrowser();
//		browser.setProperties(new BrowserProperty(false, false));
	}

	@Override
	public BrowserBase getBrowser() {
		return browser;
	}
}
