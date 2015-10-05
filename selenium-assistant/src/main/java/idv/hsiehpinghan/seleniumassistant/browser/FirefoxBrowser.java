package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.factory.DesiredCapabilitiesFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
import idv.hsiehpinghan.seleniumassistant.webdriver.FirefoxDriverExtension;

import org.openqa.selenium.WebDriver;

public class FirefoxBrowser extends BrowserBase {
	private FirefoxDriverExtension webDriver = new FirefoxDriverExtension();

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.startSession(DesiredCapabilitiesFactory
				.generateDesiredCapabilities(property));
	}

}
