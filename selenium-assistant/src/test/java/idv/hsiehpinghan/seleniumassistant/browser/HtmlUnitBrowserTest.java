package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import org.testng.annotations.BeforeClass;

public class HtmlUnitBrowserTest extends BrowserTest {
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		setBrowser(TestngSuitSetting.getHtmlUnitWithJavascriptBrowser());
	}
}
