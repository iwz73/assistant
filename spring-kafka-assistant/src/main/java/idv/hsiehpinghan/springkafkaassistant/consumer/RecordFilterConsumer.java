package idv.hsiehpinghan.springkafkaassistant.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class RecordFilterConsumer {
	@KafkaListener(topics = KafkaConstant.RECORD_FILTER_TOPIC, containerFactory = "kafkaListenerContainerFactoryWithRecordFilterStrategy")
	public void listener(String message) {
		System.err.println(String.format("RecordFilterConsumer receive : %s", message));
	}
}
