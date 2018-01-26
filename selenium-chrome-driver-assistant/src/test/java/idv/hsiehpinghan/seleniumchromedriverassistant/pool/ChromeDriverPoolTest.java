package idv.hsiehpinghan.seleniumchromedriverassistant.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
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

	@AfterClass
	public void afterClass() {
		borrowedobject1.quit();
		borrowedobject2.quit();
	}

}
