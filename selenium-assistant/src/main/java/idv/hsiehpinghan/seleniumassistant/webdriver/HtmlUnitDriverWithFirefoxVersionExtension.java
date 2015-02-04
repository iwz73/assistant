package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;

@Component
@Profile("htmlUnit_with_firefox_version")
public class HtmlUnitDriverWithFirefoxVersionExtension extends
		HtmlUnitDriverExtension {
	public HtmlUnitDriverWithFirefoxVersionExtension() {
		super(BrowserVersion.FIREFOX_24);
	}
}
