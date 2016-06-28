package idv.hsiehpinghan.phantomjsdriverassistant.webdriver;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BeanToJsonConverter;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class FirefoxDriverExtension extends FirefoxDriver {

	public void startSession(Capabilities desiredCapabilities) {
		super.startSession(dropCapabilities(desiredCapabilities,
				FirefoxDriver.BINARY, FirefoxDriver.PROFILE));
	}

	/**
	 * Copy from org.openqa.selenium.firefox.FirefoxDriver
	 * 
	 * @param capabilities
	 * @param keysToRemove
	 * @return
	 */
	private static Capabilities dropCapabilities(Capabilities capabilities,
			String... keysToRemove) {
		if (capabilities == null) {
			return new DesiredCapabilities();
		}
		final Set<String> toRemove = Sets.newHashSet(keysToRemove);
		DesiredCapabilities caps = new DesiredCapabilities(Maps.filterKeys(
				capabilities.asMap(), new Predicate<String>() {
					public boolean apply(String key) {
						return !toRemove.contains(key);
					}
				}));

		// Ensure that the proxy is in a state fit to be sent to the extension
		Proxy proxy = Proxy.extractFrom(capabilities);
		if (proxy != null) {
			caps.setCapability(PROXY, new BeanToJsonConverter().convert(proxy));
		}

		return caps;
	}
}
