package idv.hsiehpinghan.kafkaassistant2.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BasicProducer implements InitializingBean {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final long SLEEP_MILLISECONDS = 100;
	private Producer<Long, String> producer;
	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = generateProperties();
		producer = new KafkaProducer<>(properties);
	}

	public RecordMetadata send(String topic, Long key, String value) throws InterruptedException, ExecutionException {
		ProducerRecord<Long, String> producerRecord = new ProducerRecord<>(topic, key, value);
		Future<RecordMetadata> future = producer.send(producerRecord);
		while (future.isDone() == false) {
			LOGGER.info("producer sending ...");
			Thread.sleep(SLEEP_MILLISECONDS);
		}
		return future.get();
	}

	private Properties generateProperties() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
