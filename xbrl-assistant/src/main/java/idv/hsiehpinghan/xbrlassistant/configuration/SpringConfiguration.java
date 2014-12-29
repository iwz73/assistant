package idv.hsiehpinghan.xbrlassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration("xbrlAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.xbrlassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
