package idv.hsiehpinghan.hdfsassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/hdfs_assistant.property")
@Configuration("hdfsAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.hdfsassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

}
