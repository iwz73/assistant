package idv.hsiehpinghan.seleniumassistant.configuration;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("seleniumAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.seleniumassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public HtmlUnitDriver HtmlUnitDriver() {
		return new HtmlUnitDriverExtension(true);
//		return new HtmlUnitDriver(true);
	}

	@Bean
	public FirefoxDriver firefoxDriver() {
//		FirefoxProfile prof = new FirefoxProfile();
//		prof.setPreference("browser.download.defaultFolder", "/tmp");
//		prof.setPreference("browser.download.dir", "/tmp");
//		prof.setPreference("browser.download.useDownloadDir", true);
//		// 0 = desktop, 1 = default download folder , 2 = user defined location.
//		prof.setPreference("browser.download.folderList", 1);
//		prof.setPreference("browser.helperApps.neverAsk.saveToDisk","application/zip");
//		
//		
//		prof.setPreference("browser.helperApps.alwaysAsk.force", false);
//		prof.setPreference("browser.download.manager.showWhenStarting",false);
//		
//		return new FirefoxDriver(prof);
		
		return new FirefoxDriver();
	}
}
