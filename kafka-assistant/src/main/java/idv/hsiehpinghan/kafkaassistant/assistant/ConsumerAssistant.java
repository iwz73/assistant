package idv.hsiehpinghan.kafkaassistant.assistant;

import java.util.ArrayList;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAssistant {
	private final long TIMEOUT = 1000;
	@Autowired
	private Consumer<String, String> consumer;

	public ConsumerRecords<String, String> poll() {
		while (true) {
			consumer.seekToBeginning(new ArrayList<TopicPartition>());
			consumer.beginningOffsets(new ArrayList<TopicPartition>());
			return consumer.poll(TIMEOUT);
		}
	}

}
