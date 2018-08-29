package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StringLongConsumer {
	private final long TIMEOUT = 1000;
	@Autowired
	@Qualifier("stringLongConsumer_0")
	private Consumer<String, Long> stringLongConsumer;

	public ConsumerRecords<String, Long> poll(String topic) {
		stringLongConsumer.subscribe(Arrays.asList(topic));
		return stringLongConsumer.poll(TIMEOUT);
	}

}
