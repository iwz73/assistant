package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IntegerStringConsumer {
	private final long TIMEOUT = 1000;
	@Autowired
	@Qualifier("integerStringConsumer_0")
	private Consumer<Integer, String> integerStringConsumer;

	public ConsumerRecords<Integer, String> poll(String topic) {
		integerStringConsumer.subscribe(Arrays.asList(topic));
		return integerStringConsumer.poll(TIMEOUT);
	}

}
