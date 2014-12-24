package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.browser.BrowserBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FireFoxBrowser extends BrowserBase {
	@Autowired
	private FirefoxDriver webDriver;

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
