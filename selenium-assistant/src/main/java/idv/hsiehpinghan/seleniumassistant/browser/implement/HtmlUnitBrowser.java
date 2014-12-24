package idv.hsiehpinghan.seleniumassistant.browser.implement;

import idv.hsiehpinghan.seleniumassistant.browser.Browser;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HtmlUnitBrowser implements Browser {
	@Autowired
	private HtmlUnitDriver htmlUnitDriver;

	public void browse(String url) {
		htmlUnitDriver.get(url);
	}

	HtmlUnitDriver getHtmlUnitDriver() {
		return htmlUnitDriver;
	}
}
