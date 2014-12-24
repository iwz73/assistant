package idv.hsiehpinghan.nanohttpdassistant.server;

import idv.hsiehpinghan.nanohttpdassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HttpServerTest {
	private HttpServer server;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		server = applicationContext.getBean(HttpServer.class);
	}

	@Test
	public void serveHtml() throws IOException {
		File htmlFile = ResourceUtility.getFileResource("html/index.html");
		InputStream is = server.serveHtml(htmlFile).data;
		System.out.println(IOUtils.toString(is));
	}

	@AfterClass
	public void afterClass() {
		server.stop();
	}
}
