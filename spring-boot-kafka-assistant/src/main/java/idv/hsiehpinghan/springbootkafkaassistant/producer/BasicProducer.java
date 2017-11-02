package idv.hsiehpinghan.springbootkafkaassistant.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BasicProducer {
	  @Autowired
	  private KafkaTemplate<String, String> kafkaTemplate;


}
