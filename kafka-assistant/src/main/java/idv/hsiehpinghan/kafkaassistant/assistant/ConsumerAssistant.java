package idv.hsiehpinghan.kafkaassistant.assistant;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAssistant {
	private final long TIMEOUT = 1000;
	@Autowired
	private Consumer<String, String> consumer;

	public ConsumerRecords<String, String> poll() {
		while (true) {
			return consumer.poll(TIMEOUT);
		}
	}

}
