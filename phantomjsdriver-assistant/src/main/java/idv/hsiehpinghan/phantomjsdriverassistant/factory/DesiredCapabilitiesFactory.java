package idv.hsiehpinghan.phantomjsdriverassistant.factory;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import idv.hsiehpinghan.phantomjsdriverassistant.property.BrowserProperty;

public class DesiredCapabilitiesFactory {

	public static DesiredCapabilities generateDesiredCapabilities(BrowserProperty property) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, property.isJavascriptEnabled());
		capabilities.setCapability(CapabilityType.PROXY, ProxyFactory.generateProxy(property));
		return capabilities;
	}

}
