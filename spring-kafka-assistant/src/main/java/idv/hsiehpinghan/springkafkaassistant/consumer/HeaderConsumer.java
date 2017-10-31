package idv.hsiehpinghan.springkafkaassistant.consumer;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class HeaderConsumer {

	@KafkaListener(topics = KafkaConstant.HEADER_TOPIC)
	public void listener(@Headers Map<Object, Object> headerMap, @Payload String message) {
		for (Map.Entry<Object, Object> ent : headerMap.entrySet()) {
			Object key = ent.getKey();
			Object value = ent.getValue();
			System.err.println(String.format("HeaderConsumer header(%s=%s)", key, value));
		}
		System.err.println(String.format("HeaderConsumer receive : %s", message));
	}
}
