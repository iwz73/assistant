package idv.hsiehpinghan.seleniumchromedriverassistant.utility;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.seleniumchromedriverassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ChromeDriverUtilityTest extends AbstractTestNGSpringContextTests {
	private ChromeDriver chromeDriver;
	@Autowired
	private GenericObjectPool<ChromeDriver> chromeDriverPool;

	@Test
	public void cssSelectorTest() throws Exception {
		chromeDriver = chromeDriverPool.borrowObject();
		chromeDriver.get(
				"file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/CssSelectorPage.html");
		String attributeName = "id";
		ChromeDriverUtility.setAllElementAttributeValue(chromeDriver, attributeName);
		WebElement webElement = chromeDriver.findElement(By.id("_1_2_2"));
		Assert.assertEquals(webElement.getText(), "18");
	}

	@Test(dependsOnMethods = { "cssSelectorTest" })
	public void styleTest() throws Exception {
		chromeDriver
				.get("file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/StylePage.html");
		basicPropertyTest();
		colorPropertyTest();
		backgroundPropertyTest();
	}

	@AfterClass
	public void afterClass() {
		chromeDriver.quit();
	}
	
	private void basicPropertyTest() {
		WebElement webElement = chromeDriver.findElement(By.id("basic"));
		Assert.assertEquals(webElement.getTagName(), "div");
	}

	private void colorPropertyTest() {
		WebElement webElement = null;
		webElement = chromeDriver.findElement(By.id("color_0"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(255, 0, 0, 1)");
		webElement = chromeDriver.findElement(By.id("color_1"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(0, 255, 0, 1)");
		webElement = chromeDriver.findElement(By.id("color_2"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(0, 0, 255, 1)");
		webElement = chromeDriver.findElement(By.id("opacity"));
		Assert.assertEquals(webElement.getCssValue("opacity"), "0.5");
	}

	private void backgroundPropertyTest() {
		WebElement webElement = null;
		webElement = chromeDriver.findElement(By.id("background"));
		Assert.assertEquals(webElement.getCssValue("background-attachment"), "fixed");
		webElement = chromeDriver.findElement(By.id("background-attachment"));
		Assert.assertEquals(webElement.getCssValue("background-attachment"), "scroll");
		webElement = chromeDriver.findElement(By.id("background-blend-mode"));
		Assert.assertEquals(webElement.getCssValue("background-blend-mode"), "color-dodge");
		webElement = chromeDriver.findElement(By.id("background-color"));
		Assert.assertEquals(webElement.getCssValue("background-color"), "rgba(0, 255, 255, 1)");
		webElement = chromeDriver.findElement(By.id("background-image"));
		Assert.assertEquals(webElement.getCssValue("background-image"),
				"url(\"file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/not_exist.gif\")");
		webElement = chromeDriver.findElement(By.id("background-position"));
		Assert.assertEquals(webElement.getCssValue("background-position"), "50% 50%");
		webElement = chromeDriver.findElement(By.id("background-repeat"));
		Assert.assertEquals(webElement.getCssValue("background-repeat"), "repeat-y");
		webElement = chromeDriver.findElement(By.id("background-clip"));
		Assert.assertEquals(webElement.getCssValue("background-clip"), "content-box");
		webElement = chromeDriver.findElement(By.id("background-origin"));
		Assert.assertEquals(webElement.getCssValue("background-origin"), "content-box");
		webElement = chromeDriver.findElement(By.id("background-size"));
		Assert.assertEquals(webElement.getCssValue("background-size"), "80px 60px");

		// https://www.w3schools.com/cssref/default.asp

		System.err.println(webElement.getCssValue("background-size"));

	}
}
