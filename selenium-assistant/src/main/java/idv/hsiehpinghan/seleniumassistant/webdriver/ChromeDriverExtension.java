package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverExtension extends ChromeDriver {
	public void startSession(Capabilities desiredCapabilities) {
		super.startSession(desiredCapabilities);
	}
}
