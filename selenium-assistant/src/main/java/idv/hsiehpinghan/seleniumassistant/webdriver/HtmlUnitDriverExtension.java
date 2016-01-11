package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

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
		modifiedClient.setCssErrorHandler(new ErrorHandler() {
			@Override
			public void error(CSSParseException arg0) throws CSSException {
				// do nothing
			}

			@Override
			public void fatalError(CSSParseException arg0) throws CSSException {
				// do nothing
			}

			@Override
			public void warning(CSSParseException arg0) throws CSSException {
				// do nothing
			}
		});
		return modifiedClient;
	}
}
