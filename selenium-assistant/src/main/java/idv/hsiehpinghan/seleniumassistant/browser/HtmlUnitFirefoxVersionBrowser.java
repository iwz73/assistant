package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverWithFirefoxVersionExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("htmlUnit-with-firefox-version")
public class HtmlUnitFirefoxVersionBrowser extends HtmlUnitBrowserBase {
	@Autowired
	private HtmlUnitDriverWithFirefoxVersionExtension webDriver;

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

}
