package idv.hsiehpinghan.kafkaassistant.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BasicProducer {
	private final long SLEEP_MILLISECONDS = 100;
	@Autowired
	@Qualifier("basicProducer_0")
	private Producer<String, String> basicProducer;
	@Autowired
	private Environment environment;

	public RecordMetadata send(String value) throws InterruptedException, ExecutionException {
		String topic = environment.getRequiredProperty("test_topic_0");
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, value);
		Future<RecordMetadata> future = basicProducer.send(record);
		while (future.isDone() == false) {
			Thread.sleep(SLEEP_MILLISECONDS);
			return future.get();
		}
		return null;
	}

}
