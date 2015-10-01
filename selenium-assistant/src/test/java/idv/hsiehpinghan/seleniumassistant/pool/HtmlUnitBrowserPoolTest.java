package idv.hsiehpinghan.seleniumassistant.pool;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HtmlUnitBrowserPoolTest {
	private HtmlUnitBrowserPool pool;
	private HtmlUnitBrowser browser_1;
	private HtmlUnitBrowser browser_2;
	private HtmlUnitBrowser browser_3;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		pool = applicationContext.getBean(HtmlUnitBrowserPool.class);
	}

	@Test
	public void borrowObject() throws Exception {
		browser_1 = pool.borrowObject();
		browser_2 = pool.borrowObject();
		browser_3 = pool.borrowObject();
		Assert.assertEquals(3, pool.getCreatedCount());
	}

	@Test
	public void returnObject() {
		pool.returnObject(browser_1);
		pool.returnObject(browser_2);
		pool.returnObject(browser_3);
		Assert.assertEquals(3, pool.getReturnedCount());
	}
}
