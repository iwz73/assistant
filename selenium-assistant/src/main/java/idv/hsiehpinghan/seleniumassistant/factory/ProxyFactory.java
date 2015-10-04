package idv.hsiehpinghan.seleniumassistant.factory;

import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.openqa.selenium.Proxy;

public class ProxyFactory {

	public static Proxy generateProxy(BrowserProperty property) {
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(property.getHttpProxy());
		return proxy;
	}

}
