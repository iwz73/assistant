package idv.hsiehpinghan.kafkaassistant.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration("kafkaAssistantSpringConfiguration")
@PropertySource("classpath:/kafka_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.kafkaassistant" })
public class SpringConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Producer<String, String> producer() {
		Properties properties = generateProducerProperties();
		return new KafkaProducer<>(properties);
	}

	private Properties generateProducerProperties() {
		Properties properties = new Properties();
		String metadataBrokerList = environment.getRequiredProperty("metadata_broker_list");
		properties.put("metadata.broker.list", metadataBrokerList);
		String bootstrap_servers = environment.getRequiredProperty("bootstrap_servers");
		properties.put("bootstrap.servers", bootstrap_servers);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return properties;
	}

}