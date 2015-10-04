package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.factory.ProxyFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
import idv.hsiehpinghan.seleniumassistant.webdriver.FirefoxDriverExtension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxBrowser extends BrowserBase {
	private FirefoxDriverExtension webDriver = new FirefoxDriverExtension();

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.startSession(generateDesiredCapabilities(property));
	}

	private DesiredCapabilities generateDesiredCapabilities(
			BrowserProperty property) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,
				property.isJavascriptEnabled());
		capabilities.setCapability(CapabilityType.PROXY,
				ProxyFactory.generateProxy(property));
		return capabilities;
	}

}
