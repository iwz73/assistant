package idv.hsiehpinghan.nanohttpdassistant.server;

import idv.hsiehpinghan.nanohttpdassistant.suit.TestngSuitSetting;

import java.io.IOException;

import junit.framework.Assert;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MockHtmlServerTest {
	private MockHtmlServer server;
	private HtmlUnitDriver htmlUnit;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		server = applicationContext.getBean(MockHtmlServer.class);
		htmlUnit = new HtmlUnitDriver(true);
	}

	@Test
	public void serveHtml() throws IOException {
		htmlUnit.get("http://127.0.0.1:8080/html/nanohttpd_index.html");
		Assert.assertEquals("Index page", htmlUnit.getTitle());
	}

	@AfterClass
	public void afterClass() {
		server.stop();
	}
}
