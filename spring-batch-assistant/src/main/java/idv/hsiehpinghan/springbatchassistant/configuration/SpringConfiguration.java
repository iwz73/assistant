package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration("springBatchAssistantSpringConfiguration")
@PropertySource("classpath:/spring_batch_assistant.property")
@ImportResource(value = { "classpath:/batch/LinearFlow.xml" })
@ComponentScan(basePackages = { "idv.hsiehpinghan.springbatchassistant" })
public class SpringConfiguration {

}
