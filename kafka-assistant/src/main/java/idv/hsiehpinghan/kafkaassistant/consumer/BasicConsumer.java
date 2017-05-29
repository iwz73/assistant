package idv.hsiehpinghan.kafkaassistant.consumer;

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
	private Consumer<String, String> basicConsumer;

	public ConsumerRecords<String, String> poll() {
		while (true) {
			return basicConsumer.poll(TIMEOUT);
		}
	}

}
