package idv.hsiehpinghan.springkafkaassistant.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class RecordFilterProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ListenableFuture<SendResult<String, String>> send(String message) {
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate
				.send(KafkaConstant.RECORD_FILTER_TOPIC, message);
		kafkaTemplate.flush();
		return listenableFuture;
	}
}
