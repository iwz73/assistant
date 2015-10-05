package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.factory.DesiredCapabilitiesFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
import idv.hsiehpinghan.seleniumassistant.webdriver.ChromeDriverExtension;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class ChromeBrowser extends BrowserBase {
	private ChromeDriverExtension webDriver;

	public ChromeBrowser() {
		initEnvironment();
		webDriver = new ChromeDriverExtension();
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.startSession(DesiredCapabilitiesFactory
				.generateDesiredCapabilities(property));
	}

	private void initEnvironment() {
		final String PROPERTY = "webdriver.chrome.driver";
		String chromedriverPath = System.getProperty(PROPERTY);
		if (chromedriverPath != null) {
			return;
		}
		File chromedriver = new File(ClassLoader.getSystemResource(
				"chromedriver").getFile());
		if (chromedriver.canExecute() == false) {
			chromedriver.setExecutable(true);
		}
		System.setProperty(PROPERTY, chromedriver.getAbsolutePath());
	}

}
