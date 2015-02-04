package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverWithJavascriptExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("htmlUnit_with_javascript")
public class HtmlUnitWithJavascriptBrowser extends HtmlUnitBrowserBase {
	@Autowired
	private HtmlUnitDriverWithJavascriptExtension webDriver;

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

}
