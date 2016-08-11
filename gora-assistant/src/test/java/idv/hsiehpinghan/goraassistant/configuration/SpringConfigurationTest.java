package idv.hsiehpinghan.goraassistant.configuration;

import org.apache.nutch.util.NutchConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("goraAssistantSpringConfigurationTest")
@ComponentScan(basePackages = { "idv.hsiehpinghan.goraassistant" })
public class SpringConfigurationTest {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public org.apache.hadoop.conf.Configuration configuration() {
		return NutchConfiguration.create();
	}
}