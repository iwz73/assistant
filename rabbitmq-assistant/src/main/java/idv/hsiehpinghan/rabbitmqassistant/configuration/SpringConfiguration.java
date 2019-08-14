package idv.hsiehpinghan.rabbitmqassistant.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.rabbitmq.client.ConnectionFactory;

@Configuration("rabbitmqAssistantSpringConfiguration")
@PropertySource("classpath:/rabbitmq_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.rabbitmqassistant" })
public class SpringConfiguration {
	@Value("${rabbitmq.host}")
	private String rabbitmqHost;

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(rabbitmqHost);
		return connectionFactory;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}