package idv.hsiehpinghan.kafkaassistant.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PartitionerProducer implements InitializingBean {
	private final long SLEEP_MILLISECONDS = 100;
	private String topic;
	@Autowired
	@Qualifier("partitionerProducer_0")
	private Producer<Integer, String> partitionerProducer;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		topic = environment.getRequiredProperty("partitioner_topic");
	}

	public RecordMetadata send(Integer key, String value) throws InterruptedException, ExecutionException {
		ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topic, key, value);
		Future<RecordMetadata> future = partitionerProducer.send(record);
		while (future.isDone() == false) {
			Thread.sleep(SLEEP_MILLISECONDS);
			return future.get();
		}
		return null;
	}

}
