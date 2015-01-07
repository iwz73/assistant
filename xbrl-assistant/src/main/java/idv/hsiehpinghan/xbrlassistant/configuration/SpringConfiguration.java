package idv.hsiehpinghan.xbrlassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration("xbrlAssistantSpringConfiguration")
@PropertySource("classpath:xbrl-assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.xbrlassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
