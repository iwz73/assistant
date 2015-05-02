package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;

public class FirefoxBrowserTest extends BrowserTest {
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		FirefoxBrowser browser = applicationContext
				.getBean(FirefoxBrowser.class);
		setBrowser(browser);
	}
}
