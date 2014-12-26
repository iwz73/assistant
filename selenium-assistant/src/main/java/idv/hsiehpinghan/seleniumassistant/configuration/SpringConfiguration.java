package idv.hsiehpinghan.seleniumassistant.configuration;

import idv.hsiehpinghan.seleniumassistant.webdriver.FirefoxDriverExtension;
import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("seleniumAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.seleniumassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public HtmlUnitDriverExtension HtmlUnitDriver() {
		return new HtmlUnitDriverExtension(true);
	}

	@Bean
	public FirefoxDriverExtension firefoxDriver() {
		return new FirefoxDriverExtension();
	}
}
