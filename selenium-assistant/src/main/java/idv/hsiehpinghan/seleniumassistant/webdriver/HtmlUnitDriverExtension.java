package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitDriverExtension extends HtmlUnitDriver {

	public HtmlUnitDriverExtension() {
		super(BrowserVersion.FIREFOX_24);
	}

	public WebClient getWebClient() {
		return super.getWebClient();
	}
}
