package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.kafkaassistant.vo.AggregateJsonVo;

@Component
public class IntegerAggregateJsonVoConsumer {
	private final long TIMEOUT = 1000;
	@Autowired
	@Qualifier("integerAggregateJsonVoConsumer_0")
	private Consumer<Integer, AggregateJsonVo> integerAggregateJsonVoConsumer;

	public ConsumerRecords<Integer, AggregateJsonVo> poll(String topic) {
		integerAggregateJsonVoConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			return integerAggregateJsonVoConsumer.poll(TIMEOUT);
		}
	}

}
