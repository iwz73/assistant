package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BasicConsumer {
	private final long TIMEOUT = 1000;
	@Autowired
	@Qualifier("basicConsumer_0")
	private Consumer<Long, String> basicConsumer;

	public ConsumerRecords<Long, String> poll(String topic) {
		basicConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			return basicConsumer.poll(TIMEOUT);
		}
	}

}
