package idv.hsiehpinghan.phantomjsdriverassistant.factory;

import org.openqa.selenium.Proxy;

import idv.hsiehpinghan.phantomjsdriverassistant.property.BrowserProperty;

public class ProxyFactory {

	public static Proxy generateProxy(BrowserProperty property) {
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(property.getHttpProxy());
		return proxy;
	}

}
