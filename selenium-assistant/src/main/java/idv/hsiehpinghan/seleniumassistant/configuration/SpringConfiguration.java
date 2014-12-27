package idv.hsiehpinghan.seleniumassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("seleniumAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.seleniumassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
}
