package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;

public class FireFoxBrowserTest extends BrowserTest {
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		setBrowser(applicationContext.getBean(FireFoxBrowser.class));
	}
}
