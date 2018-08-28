package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LongStringConsumer {
	private final long TIMEOUT = 1000;
	@Autowired
	@Qualifier("longStringConsumer_0")
	private Consumer<Long, String> longStringConsumer;

	public ConsumerRecords<Long, String> poll(String topic) {
		longStringConsumer.subscribe(Arrays.asList(topic));
		return longStringConsumer.poll(TIMEOUT);
	}

}
