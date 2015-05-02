package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class HtmlUnitBrowserTest extends BrowserTest {
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		HtmlUnitBrowser browser = applicationContext.getBean(
				HtmlUnitBrowser.class, BrowserVersion.FIREFOX_24, true, false);
		setBrowser(browser);
	}
}
