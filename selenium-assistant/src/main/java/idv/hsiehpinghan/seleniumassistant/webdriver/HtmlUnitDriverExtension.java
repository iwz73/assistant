package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitDriverExtension extends HtmlUnitDriver {

	public HtmlUnitDriverExtension() {
		super(BrowserVersion.FIREFOX_38);
	}

	@Override
	public WebClient getWebClient() {
		return super.getWebClient();
	}

	@Override
	protected WebClient modifyWebClient(WebClient client) {
		WebClient modifiedClient = super.modifyWebClient(client);
		modifiedClient.getOptions().setThrowExceptionOnScriptError(false);
		return modifiedClient;
	}
}
