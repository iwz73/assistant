package idv.hsiehpinghan.goraassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("goraAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.goraassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

}