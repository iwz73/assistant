package idv.hsiehpinghan.seleniumchromedriverassistant.factory;

import java.util.Set;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.seleniumchromedriverassistant.constant.Constant;

@Component
public class ChromeDriverFactory extends BasePooledObjectFactory<ChromeDriver> implements InitializingBean {
	private String webdriverChromeDriver;
	private String binary;
	private boolean headless;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		webdriverChromeDriver = environment.getRequiredProperty("webdriver_chrome_driver", String.class);
		binary = environment.getRequiredProperty("binary", String.class);
		headless = environment.getRequiredProperty("headless", Boolean.class);
	}

	@Override
	public ChromeDriver create() throws Exception {
		System.setProperty("webdriver.chrome.driver", webdriverChromeDriver);
		ChromeOptions chromeOptions = generateChromeOptions();
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
		return chromeDriver;
	}

	@Override
	public PooledObject<ChromeDriver> wrap(ChromeDriver chromeDriver) {
		return new DefaultPooledObject<ChromeDriver>(chromeDriver);
	}

	@Override
	public void destroyObject(final PooledObject<ChromeDriver> pooledObject) throws Exception {
		ChromeDriver chromeDriver = pooledObject.getObject();
		chromeDriver.quit();
	}

	@Override
	public boolean validateObject(final PooledObject<ChromeDriver> pooledObject) {
		return true;
	}

	@Override
	public void activateObject(final PooledObject<ChromeDriver> pooledObject) throws Exception {
		// do nothing
	}

	@Override
	public void passivateObject(final PooledObject<ChromeDriver> pooledObject) throws Exception {
		ChromeDriver chromeDriver = pooledObject.getObject();
		openNewEmptyTab(chromeDriver);
		String currentWindowHandle = chromeDriver.getWindowHandle();
		Set<String> windowHandles = chromeDriver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (currentWindowHandle == windowHandle) {
				continue;
			}
			chromeDriver.switchTo().window(windowHandle);
			chromeDriver.close();
		}
	}

	private void openNewEmptyTab(ChromeDriver chromeDriver) {
		chromeDriver.get(Constant.DEFAULT_URL);
		chromeDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	private ChromeOptions generateChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary(binary);
		chromeOptions.setHeadless(headless);
		return chromeOptions;
	}

}
