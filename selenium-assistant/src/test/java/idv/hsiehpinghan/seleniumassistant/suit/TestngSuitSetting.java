package idv.hsiehpinghan.seleniumassistant.suit;

import idv.hsiehpinghan.nanohttpdassistant.server.MockHtmlServer;
import idv.hsiehpinghan.objectutility.utility.ClassUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	public static final String URL_BASE = "http://127.0.0.1:8080/";

	private static AnnotationConfigApplicationContext applicationContext;
	private static MockHtmlServer htmlServer;

	// private static HtmlUnitDriverExtension htmlUnitDriverExtension;
	// private static HtmlUnitFirefoxVersionBrowser
	// htmlUnitFirefoxVersionBrowser;
	// private static HtmlUnitWithJavascriptBrowser
	// htmlUnitWithJavascriptBrowser;
	// private static FirefoxBrowser firefoxBrowser;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		Class<?>[] clsArr = ClassUtility.getAnnotatedClasses(
				"idv.hsiehpinghan", Configuration.class);
		applicationContext = new AnnotationConfigApplicationContext(clsArr);
		htmlServer = applicationContext.getBean(MockHtmlServer.class);
		// htmlUnitDriverExtension = applicationContext.getBean(
		// "htmlUnitDriverExtension", HtmlUnitDriverExtension.class);
		// htmlUnitWithJavascriptBrowser = applicationContext
		// .getBean(HtmlUnitWithJavascriptBrowser.class);
		// htmlUnitFirefoxVersionBrowser = applicationContext
		// .getBean(HtmlUnitFirefoxVersionBrowser.class);
		// firefoxBrowser = applicationContext.getBean(FirefoxBrowser.class);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static MockHtmlServer getHtmlServer() {
		return htmlServer;
	}

	// public static HtmlUnitWithJavascriptBrowser
	// getHtmlUnitWithJavascriptBrowser() {
	// return htmlUnitWithJavascriptBrowser;
	// }
	//
	// public static FirefoxBrowser getFirefoxBrowser() {
	// return firefoxBrowser;
	// }
	//
	// public static HtmlUnitDriverExtension getHtmlUnitDriverExtension() {
	// return htmlUnitDriverExtension;
	// }
	//
	// public static HtmlUnitFirefoxVersionBrowser
	// getHtmlUnitFirefoxVersionBrowser() {
	// return htmlUnitFirefoxVersionBrowser;
	// }

	@AfterSuite
	public void afterClass() {
		htmlServer.stop();
	}

}
