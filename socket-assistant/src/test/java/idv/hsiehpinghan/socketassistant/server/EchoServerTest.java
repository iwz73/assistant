package idv.hsiehpinghan.socketassistant.server;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class EchoServerTest {
//	private EchoServer server;
//	
//	@BeforeClass
//	public void beforeClass() throws IOException {
//		ApplicationContext applicationContext = TestngSuitSetting
//				.getApplicationContext();
//		server = applicationContext.getBean(EchoServer.class);
//		server.start();
//	}

	@Test
	public void post() throws Exception {
		// TODO : not finished yet.
		String PROXY = "localhost:8888";

		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY)
		     .setFtpProxy(PROXY)
		     .setSslProxy(PROXY);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		WebDriver driver = new FirefoxDriver(cap);
		try {
			driver.get("https://www.google.com.tw/");
		}catch(Exception e) {
			System.err.println("eee");
		}

		System.err.println(driver.getPageSource());
	}
}
