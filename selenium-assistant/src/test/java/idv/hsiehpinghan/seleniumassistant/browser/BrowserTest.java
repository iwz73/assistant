package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webelement.Select;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public abstract class BrowserTest {
	private static final String URL_BASE = "http://127.0.0.1:8080/";
	private BrowserBase browser;

	public void beforeClass() {
		// ApplicationContext applicationContext = TestngSuitSetting
		// .getApplicationContext();
	}

	@Test
	public void browse() {
		browser.browse(URL_BASE + "html/selenium_index.html");
		Assert.assertEquals("Index page", browser.getWebDriver().getTitle());
	}

	@Test(dependsOnMethods = { "browse" })
	public void getSelect() {
		Select select = browser.getSelect(By.cssSelector("#selectId"));
		Assert.assertNotNull(select);
	}

	public void setBrowser(BrowserBase browser) {
		this.browser = browser;
	}

}
