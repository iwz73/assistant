package idv.hsiehpinghan.seleniumassistant.browser;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.BeforeClass;

public class ChromeBrowserTest extends BrowserTest {
	private ChromeBrowser browser;

	@BeforeClass
	public void beforeClass() {
		browser = new ChromeBrowser();
		
		
//		Properties properties = new Properties();
//		properties.setProperty(CapabilityType.SUPPORTS_JAVASCRIPT, "false");
//		browser.setProperties(properties);
	}

	@Override
	public BrowserBase getBrowser() {
		return browser;
	}

}
