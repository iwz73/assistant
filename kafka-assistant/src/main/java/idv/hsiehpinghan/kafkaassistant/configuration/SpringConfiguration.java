package idv.hsiehpinghan.kafkaassistant.configuration;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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

	@Bean
	public Consumer<String, String> consumer() {
		Properties properties = generateConsumerProperties();
		Consumer<String, String> consumer = new KafkaConsumer<>(properties);
		String topic = environment.getRequiredProperty("test_topic_0");
		consumer.subscribe(Arrays.asList(topic));
		return consumer;
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

	private Properties generateConsumerProperties() {
		Properties properties = new Properties();
		String group_id_0 = environment.getRequiredProperty("group_id_0");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, group_id_0);
		String bootstrap_servers = environment.getRequiredProperty("bootstrap_servers");
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}
}