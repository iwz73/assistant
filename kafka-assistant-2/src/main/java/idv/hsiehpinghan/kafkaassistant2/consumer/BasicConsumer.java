package idv.hsiehpinghan.kafkaassistant2.consumer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BasicConsumer implements InitializingBean {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final String GROUP_ID = this.getClass().getSimpleName();
	private final Duration TIMEOUT = Duration.of(10, ChronoUnit.SECONDS);
	private Consumer<Long, String> consumer;
	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = generateProperties();
		consumer = new KafkaConsumer<>(properties);
	}

	public ConsumerRecords<Long, String> poll(String topic) {
		LOGGER.info("consumer polling ...");
		consumer.subscribe(Arrays.asList(topic));
		return consumer.poll(TIMEOUT);
	}

	private Properties generateProperties() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}
}
