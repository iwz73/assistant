package idv.hsiehpinghan.zookeeperassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("socketAssistantSpringConfiguration")
@PropertySource("classpath:/zookeeper_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.zookeeperassistant" })
public class SpringConfiguration {
}