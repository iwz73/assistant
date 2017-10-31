package idv.hsiehpinghan.springkafkaassistant.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;
import idv.hsiehpinghan.springkafkaassistant.model.JsonModel;

@Component
public class JsonProducer {
	@Autowired
	private KafkaTemplate<String, JsonModel> jsonKafkaTemplate;

	public ListenableFuture<SendResult<String, JsonModel>> send(JsonModel message) {
		ListenableFuture<SendResult<String, JsonModel>> listenableFuture = jsonKafkaTemplate
				.send(KafkaConstant.JSON_TOPIC, message);
		jsonKafkaTemplate.flush();
		return listenableFuture;
	}
}
