package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.browser.BrowserBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HtmlUnitBrowser extends BrowserBase {
	@Autowired
	private HtmlUnitDriver webDriver;

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
