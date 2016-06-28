package idv.hsiehpinghan.phantomjsdriverassistant.browser;

import org.openqa.selenium.WebDriver;

import idv.hsiehpinghan.phantomjsdriverassistant.factory.DesiredCapabilitiesFactory;
import idv.hsiehpinghan.phantomjsdriverassistant.property.BrowserProperty;
import idv.hsiehpinghan.phantomjsdriverassistant.webdriver.FirefoxDriverExtension;

public class FirefoxBrowser extends BrowserBase {
	private FirefoxDriverExtension webDriver = new FirefoxDriverExtension();

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.startSession(DesiredCapabilitiesFactory.generateDesiredCapabilities(property));
	}

}
