package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.testng.annotations.BeforeClass;

public class HtmlUnitBrowserTest extends BrowserTest {
	private HtmlUnitBrowser browser;

	@BeforeClass
	public void beforeClass() {
		browser = new HtmlUnitBrowser();
		browser.setBrowserProperty(generateBrowserProperty());
	}

	@Override
	public BrowserBase getBrowser() {
		return browser;
	}

	private BrowserProperty generateBrowserProperty() {
		boolean javascriptEnabled = true;
		String proxy = null;
		return new BrowserProperty(javascriptEnabled, proxy);
	}
}
