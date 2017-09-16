package idv.hsiehpinghan.kafkaassistant.configuration;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import idv.hsiehpinghan.kafkaassistant.partitioner.PartitionerPartitioner;

@Configuration("kafkaAssistantSpringConfiguration")
@PropertySource("classpath:/kafka_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.kafkaassistant" })
public class SpringConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Producer<String, String> basicProducer_0() {
		Properties properties = generateBasicProducerProperties();
		return new KafkaProducer<>(properties);
	}

	@Bean
	public Producer<Integer, String> partitionerProducer_0() {
		Properties properties = generatePartitionerProducerProperties();
		return new KafkaProducer<>(properties);
	}

	@Bean
	public Consumer<String, String> basicConsumer_0() {
		Properties properties = generateBasicConsumerProperties();
		return new KafkaConsumer<>(properties);
	}

	@Bean
	public Consumer<Integer, String> partitionerConsumer_0() {
		Properties properties = generatePartitionerConsumerProperties();
		return new KafkaConsumer<>(properties);
	}

	private Properties generateBasicProducerProperties() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getRequiredProperty("bootstrap_servers"));
		properties.put(ProducerConfig.ACKS_CONFIG, environment.getRequiredProperty("acks"));
		properties.put(ProducerConfig.RETRIES_CONFIG, environment.getRequiredProperty("retries"));
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, environment.getRequiredProperty("batch_size"));
		properties.put(ProducerConfig.LINGER_MS_CONFIG, environment.getRequiredProperty("linger_ms"));
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, environment.getRequiredProperty("buffer_memory"));
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

	private Properties generatePartitionerProducerProperties() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getRequiredProperty("bootstrap_servers"));
		properties.put(ProducerConfig.ACKS_CONFIG, environment.getRequiredProperty("acks"));
		properties.put(ProducerConfig.RETRIES_CONFIG, environment.getRequiredProperty("retries"));
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, environment.getRequiredProperty("batch_size"));
		properties.put(ProducerConfig.LINGER_MS_CONFIG, environment.getRequiredProperty("linger_ms"));
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, environment.getRequiredProperty("buffer_memory"));
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, PartitionerPartitioner.class.getName());
		return properties;
	}

	private Properties generateBasicConsumerProperties() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getRequiredProperty("bootstrap_servers"));
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getRequiredProperty("basic_group_id"));
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, environment.getRequiredProperty("enable_auto_commit"));
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
				environment.getRequiredProperty("auto_commit_interval_ms"));
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getRequiredProperty("auto_offset_reset"));
		return properties;
	}

	private Properties generatePartitionerConsumerProperties() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getRequiredProperty("bootstrap_servers"));
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getRequiredProperty("partitioner_group_id"));
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, environment.getRequiredProperty("enable_auto_commit"));
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
				environment.getRequiredProperty("auto_commit_interval_ms"));
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getRequiredProperty("auto_offset_reset"));
		return properties;
	}
}