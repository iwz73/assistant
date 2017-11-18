package idv.hsiehpinghan.seleniumchromedriverassistant.configuration;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import idv.hsiehpinghan.seleniumchromedriverassistant.factory.ChromeDriverFactory;

@Configuration("seleniumChromeDriverAssistantSpringConfiguration")
@PropertySource("classpath:/selenium_chrome_driver_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.seleniumchromedriverassistant" })
public class SpringConfiguration {
	@Autowired
	private ChromeDriverFactory chromeDriverFactory;

	@Bean
	public GenericObjectPool<ChromeDriver> chromeDriverPool() {
		GenericObjectPool<ChromeDriver> chromeDriverPool = new GenericObjectPool<>(chromeDriverFactory);
		return chromeDriverPool;
	}

}
