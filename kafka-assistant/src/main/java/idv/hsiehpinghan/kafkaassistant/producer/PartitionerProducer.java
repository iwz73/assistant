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
public class PartitionerProducer {
	private final long SLEEP_MILLISECONDS = 100;
	@Autowired
	@Qualifier("partitionerProducer_0")
	private Producer<Integer, String> partitionerProducer;

	public RecordMetadata send(String topic, Integer key, String value)
			throws InterruptedException, ExecutionException {
		ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topic, key, value);
		Future<RecordMetadata> future = partitionerProducer.send(record);
		while (future.isDone() == false) {
			Thread.sleep(SLEEP_MILLISECONDS);
			return future.get();
		}
		return null;
	}

}
