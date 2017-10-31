package idv.hsiehpinghan.springkafkaassistant.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;
import idv.hsiehpinghan.springkafkaassistant.model.JsonModel;

@Component
public class JsonConsumer {
	@KafkaListener(topics = KafkaConstant.JSON_TOPIC, containerFactory = "jsonKafkaListenerContainerFactory")
	public void listener(JsonModel message) {
		System.err.println(String.format("JsonConsumer receive : %s", message));
	}
}
