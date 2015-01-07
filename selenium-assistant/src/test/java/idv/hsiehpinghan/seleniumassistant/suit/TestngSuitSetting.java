package idv.hsiehpinghan.seleniumassistant.suit;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.packageutility.utility.PackageUtility;
import idv.hsiehpinghan.seleniumassistant.browser.FirefoxBrowser;
import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitWithJavascriptBrowser;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	public static final String URL_BASE = "http://127.0.0.1:8080/";

	private static AnnotationConfigApplicationContext applicationContext;
	private static MockHtmlServer htmlServer;
	private static HtmlUnitDriverExtension htmlUnitDriverExtension;
	private static HtmlUnitWithJavascriptBrowser htmlUnitWithJavascriptBrowser;
	private static FirefoxBrowser firefoxBrowser;

	@BeforeSuite()
	public void beforeSuite() throws IOException {
		String[] pkgs = PackageUtility.getSpringConfigurationPackages();
		String[] testPkgs = addTestComponentScanBasePackages(pkgs);
		applicationContext = new AnnotationConfigApplicationContext(testPkgs);
		htmlServer = applicationContext.getBean(MockHtmlServer.class);
		htmlUnitDriverExtension = applicationContext.getBean(
				"htmlUnitDriverExtension", HtmlUnitDriverExtension.class);
		htmlUnitWithJavascriptBrowser = applicationContext
				.getBean(HtmlUnitWithJavascriptBrowser.class);
		firefoxBrowser = applicationContext.getBean(FirefoxBrowser.class);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static MockHtmlServer getHtmlServer() {
		return htmlServer;
	}

	public static HtmlUnitWithJavascriptBrowser getHtmlUnitWithJavascriptBrowser() {
		return htmlUnitWithJavascriptBrowser;
	}

	public static FirefoxBrowser getFirefoxBrowser() {
		return firefoxBrowser;
	}

	public static HtmlUnitDriverExtension getHtmlUnitDriverExtension() {
		return htmlUnitDriverExtension;
	}

	@AfterSuite
	public void afterClass() {
		htmlServer.stop();
	}

	private String[] addTestComponentScanBasePackages(String[] pkgs) {
		String nanohttpdassistant = "idv.hsiehpinghan.nanohttpdassistant.configuration";
		if (ArrayUtils.contains(pkgs, nanohttpdassistant) == false) {
			return ArrayUtils.add(pkgs, nanohttpdassistant);
		}
		return pkgs;
	}
}
