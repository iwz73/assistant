package idv.hsiehpinghan.springkafkaassistant.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class BasicConsumer {
	@KafkaListener(topics = KafkaConstant.BASIC_TOPIC)
	public void basicTopicListener(String message) {
		System.err.println(String.format("basicTopicListener receive : %s", message));
	}
}
