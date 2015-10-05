package idv.hsiehpinghan.seleniumassistant.factory;

import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {

	public static DesiredCapabilities generateDesiredCapabilities(
			BrowserProperty property) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,
				property.isJavascriptEnabled());
		capabilities.setCapability(CapabilityType.PROXY,
				ProxyFactory.generateProxy(property));
		return capabilities;
	}

}
