package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class HtmlUnitDriverExtension extends HtmlUnitDriver {

	public HtmlUnitDriverExtension() {
		super(BrowserVersion.FIREFOX_24);
	}

}
