package idv.hsiehpinghan.mahoutassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("mahoutAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mahoutassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

}
