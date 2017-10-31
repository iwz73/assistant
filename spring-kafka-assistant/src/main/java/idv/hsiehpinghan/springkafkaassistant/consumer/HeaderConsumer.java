package idv.hsiehpinghan.springkafkaassistant.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class HeaderConsumer {

	@KafkaListener(topics = KafkaConstant.HEADER_TOPIC)
	public void listener(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Payload String message) {
		System.err.println(String.format("HeaderConsumer receive : %d-%s", partition, message));
	}
}
