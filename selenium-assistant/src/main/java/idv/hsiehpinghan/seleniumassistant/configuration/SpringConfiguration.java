package idv.hsiehpinghan.seleniumassistant.configuration;

import org.openqa.selenium.firefox.FirefoxDriver;
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
		return new HtmlUnitDriver(true);
	}

	@Bean
	public FirefoxDriver firefoxDriver() {
		return new FirefoxDriver();
	}
}
