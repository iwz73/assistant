package idv.hsiehpinghan.seleniumassistant.suit;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.packageutility.utility.PackageUtility;
import idv.hsiehpinghan.seleniumassistant.browser.FireFoxBrowser;
import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	public static final String URL_BASE = "http://127.0.0.1:8080/";

	private static ApplicationContext applicationContext;
	private static MockHtmlServer htmlServer;
	private static HtmlUnitBrowser htmlUnitBrowser;
	private static FireFoxBrowser fireFoxBrowser;

	@BeforeSuite()
	public void beforeSuite() throws IOException {
		String[] pkgs = PackageUtility.getSpringConfigurationPackages();
		applicationContext = new AnnotationConfigApplicationContext(pkgs);
		htmlServer = applicationContext.getBean(MockHtmlServer.class);
		htmlUnitBrowser = applicationContext.getBean(HtmlUnitBrowser.class);
		fireFoxBrowser = applicationContext.getBean(FireFoxBrowser.class);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static MockHtmlServer getHtmlServer() {
		return htmlServer;
	}

	public static HtmlUnitBrowser getHtmlUnitBrowser() {
		return htmlUnitBrowser;
	}

	public static FireFoxBrowser getFireFoxBrowser() {
		return fireFoxBrowser;
	}

	@AfterSuite
	public void afterClass() {
		htmlServer.stop();
	}
}
