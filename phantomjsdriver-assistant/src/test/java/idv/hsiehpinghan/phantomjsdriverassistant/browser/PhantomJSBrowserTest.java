package idv.hsiehpinghan.phantomjsdriverassistant.browser;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.phantomjsdriverassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class PhantomJSBrowserTest extends AbstractTestNGSpringContextTests {
	private PhantomJSDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = generatePhantomJSDriver();
	}

	@Test
	public void get() {
		driver.get("https://weather.com/zh-TW/weather/hourbyhour/l/TWXX0021:1:TW");
		System.err.println(driver.getPageSource());
	}

	private PhantomJSDriver generatePhantomJSDriver() {
		String path = "/home/hsiehpinghan/git/assistant/phantomjsdriver-assistant/src/test/phantomjs-2.1.1-linux-x86_64/phantomjs";
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
		return new PhantomJSDriver(desiredCapabilities);
	}
}
