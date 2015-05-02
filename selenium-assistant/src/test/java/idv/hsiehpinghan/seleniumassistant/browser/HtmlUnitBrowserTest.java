package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;

public class HtmlUnitBrowserTest extends BrowserTest {
	private ApplicationContext applicationContext;

	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		applicationContext = TestngSuitSetting.getApplicationContext();
		HtmlUnitBrowser browser = applicationContext
				.getBean(HtmlUnitBrowser.class);
		setBrowser(browser);
	}
}
