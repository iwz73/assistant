package idv.hsiehpinghan.seleniumchromedriverassistant.utility;

import java.util.Map;

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
	public void setAllElementAttributeValue() throws Exception {
		chromeDriver = chromeDriverPool.borrowObject();
		chromeDriver.get(
				"file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/setAllElementAttributeValue.html");
		String attributeName = "id";
		ChromeDriverUtility.setAllElementAttributeValue(chromeDriver, attributeName);
		WebElement webElement = chromeDriver.findElement(By.id("_1_2_2"));
		Assert.assertEquals(webElement.getText(), "18");
	}

	@Test(dependsOnMethods = { "setAllElementAttributeValue" })
	public void getElementAllAttribute() throws Exception {
		chromeDriver.get(
				"file:///home/hsiehpinghan/git/assistant/selenium-chrome-driver-assistant/html/getElementAllAttribute.html");
		WebElement webElement = chromeDriver.findElement(By.id("empty"));
		Map<String, String> attributeMap = ChromeDriverUtility.getElementAllAttribute(webElement);
		for (Map.Entry<String, String> ent : attributeMap.entrySet()) {
			System.err.println(String.format("%s : %s", ent.getKey(), ent.getValue()));
		}
	}

	@AfterClass
	public void afterClass() {
		chromeDriver.quit();
	}

}
