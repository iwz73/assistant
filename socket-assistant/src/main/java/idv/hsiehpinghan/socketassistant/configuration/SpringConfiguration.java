package idv.hsiehpinghan.socketassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("socketAssistantSpringConfiguration")
@PropertySource("classpath:/socket_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.socketassistant" })
public class SpringConfiguration {

}