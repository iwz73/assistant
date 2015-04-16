package idv.hsiehpinghan.luceneassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("luceneAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.luceneassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

}
