package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.factory.ProxyFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

public class HtmlUnitBrowser extends BrowserBase {
	private HtmlUnitDriverExtension webDriver = new HtmlUnitDriverExtension();

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.setJavascriptEnabled(property.isJavascriptEnabled());
		webDriver.setProxySettings(ProxyFactory.generateProxy(property));
	}

}
