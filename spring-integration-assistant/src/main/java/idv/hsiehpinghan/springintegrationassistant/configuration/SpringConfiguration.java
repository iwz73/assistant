package idv.hsiehpinghan.springintegrationassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration("springIntegrationAssistantSpringConfiguration")
@ImportResource(value = { "classpath:/applicationContext.xml" })
@ComponentScan(basePackages = { "idv.hsiehpinghan.springintegrationassistant" })
public class SpringConfiguration {

}