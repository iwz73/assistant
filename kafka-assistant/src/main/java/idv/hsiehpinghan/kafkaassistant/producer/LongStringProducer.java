package idv.hsiehpinghan.kafkaassistant.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LongStringProducer {
	private final long SLEEP_MILLISECONDS = 100;
	@Autowired
	@Qualifier("longStringProducer_0")
	private Producer<Long, String> longStringProducer;

	public RecordMetadata send(String topic, String value) throws InterruptedException, ExecutionException {
		ProducerRecord<Long, String> record = new ProducerRecord<>(topic, value);
		Future<RecordMetadata> future = longStringProducer.send(record);
		while (future.isDone() == false) {
			Thread.sleep(SLEEP_MILLISECONDS);
		}
		return future.get();
	}

}
