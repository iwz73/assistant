package idv.hsiehpinghan.nanohttpdassistant.server;

import idv.hsiehpinghan.nanohttpdassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MockHtmlServerTest {
	private MockHtmlServer server;
	private HtmlUnitDriver htmlUnit;
	private FirefoxDriver firefox;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		server = applicationContext.getBean(MockHtmlServer.class);
		htmlUnit = new HtmlUnitDriver(true);
		firefox = new FirefoxDriver();
	}

	@Test
	public void serveHtml() throws Exception {
		htmlUnit.get("http://127.0.0.1:8080/html/nanohttpd_index.html");
		Assert.assertEquals(htmlUnit.getTitle(), "Index page");
		firefox.get("http://127.0.0.1:8080/html/nanohttpd_index.html");
		Assert.assertEquals(firefox.getTitle(), "Index page");
	}

	@AfterClass
	public void afterClass() {
		server.stop();
	}
}
