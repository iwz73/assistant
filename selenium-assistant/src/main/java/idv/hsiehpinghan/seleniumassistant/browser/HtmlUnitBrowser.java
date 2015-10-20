package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.factory.ProxyFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import com.gargoylesoftware.htmlunit.ProxyConfig;

public class HtmlUnitBrowser extends BrowserBase {
	private static final ProxyConfig NO_PROXY_CONFIG = new ProxyConfig();
	private HtmlUnitDriverExtension webDriver = new HtmlUnitDriverExtension();

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

	@Override
	public void setBrowserProperty(BrowserProperty property) {
		webDriver.setJavascriptEnabled(property.isJavascriptEnabled());
		if (":".equals(property.getHttpProxy())) {
			webDriver.getWebClient().getOptions()
					.setProxyConfig(NO_PROXY_CONFIG);
		} else {
			webDriver.setProxySettings(ProxyFactory.generateProxy(property));
		}
	}

}
