package idv.hsiehpinghan.seleniumchromedriverassistant.pool;

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
public class ChromeDriverPoolTest extends AbstractTestNGSpringContextTests {
	private ChromeDriver borrowedobject1;
	private ChromeDriver borrowedobject2;
	private ChromeDriver borrowedobject3;
	@Autowired
	private GenericObjectPool<ChromeDriver> chromeDriverPool;

	@Test
	public void borrowObject() throws Exception {
		borrowedobject1 = chromeDriverPool.borrowObject();
		Assert.assertNotNull(borrowedobject1);
	}

	@Test(dependsOnMethods = { "borrowObject" })
	public void returnObject() throws Exception {
		borrowedobject1.get("https://www.google.com.tw/");
		chromeDriverPool.returnObject(borrowedobject1);
	}

	@Test(dependsOnMethods = { "returnObject" })
	public void borrowObjectAgain() throws Exception {
		borrowedobject2 = chromeDriverPool.borrowObject();
		Assert.assertTrue(borrowedobject1 == borrowedobject2);
	}

	@Test(dependsOnMethods = { "borrowObjectAgain" })
	public void styleTest() throws Exception {
		borrowedobject3 = chromeDriverPool.borrowObject();
		borrowedobject3
				.get("file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/TestPage.html");
		basicPropertyTest();
		colorPropertyTest();
		backgroundPropertyTest();
	}

	@AfterClass
	public void afterClass() {
		borrowedobject1.quit();
		borrowedobject2.quit();
		borrowedobject3.quit();
	}

	private void basicPropertyTest() {
		WebElement webElement = borrowedobject3.findElement(By.id("basic"));
		Assert.assertEquals(webElement.getTagName(), "div");
	}

	private void colorPropertyTest() {
		WebElement webElement = null;
		webElement = borrowedobject3.findElement(By.id("color_0"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(255, 0, 0, 1)");
		webElement = borrowedobject3.findElement(By.id("color_1"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(0, 255, 0, 1)");
		webElement = borrowedobject3.findElement(By.id("color_2"));
		Assert.assertEquals(webElement.getCssValue("color"), "rgba(0, 0, 255, 1)");
		webElement = borrowedobject3.findElement(By.id("opacity"));
		Assert.assertEquals(webElement.getCssValue("opacity"), "0.5");
	}

	private void backgroundPropertyTest() {
		WebElement webElement = null;
		webElement = borrowedobject3.findElement(By.id("background"));
		Assert.assertEquals(webElement.getCssValue("background-attachment"), "fixed");
		webElement = borrowedobject3.findElement(By.id("background-attachment"));
		Assert.assertEquals(webElement.getCssValue("background-attachment"), "scroll");
		webElement = borrowedobject3.findElement(By.id("background-blend-mode"));
		Assert.assertEquals(webElement.getCssValue("background-blend-mode"), "color-dodge");
		webElement = borrowedobject3.findElement(By.id("background-color"));
		Assert.assertEquals(webElement.getCssValue("background-color"), "rgba(0, 255, 255, 1)");
		webElement = borrowedobject3.findElement(By.id("background-image"));
		Assert.assertEquals(webElement.getCssValue("background-image"),
				"url(\"file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/not_exist.gif\")");
		webElement = borrowedobject3.findElement(By.id("background-position"));
		Assert.assertEquals(webElement.getCssValue("background-position"), "50% 50%");
		webElement = borrowedobject3.findElement(By.id("background-repeat"));
		Assert.assertEquals(webElement.getCssValue("background-repeat"), "repeat-y");
		webElement = borrowedobject3.findElement(By.id("background-clip"));
		Assert.assertEquals(webElement.getCssValue("background-clip"), "content-box");
		webElement = borrowedobject3.findElement(By.id("background-origin"));
		Assert.assertEquals(webElement.getCssValue("background-origin"), "content-box");
		webElement = borrowedobject3.findElement(By.id("background-size"));
		Assert.assertEquals(webElement.getCssValue("background-size"), "80px 60px");

		https://www.w3schools.com/cssref/default.asp

		System.err.println(webElement.getCssValue("background-size"));

	}

}
