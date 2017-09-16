package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PartitionerConsumer implements InitializingBean {
	private final long TIMEOUT = 1000;
	private String topic;
	@Autowired
	@Qualifier("partitionerConsumer_0")
	private Consumer<Integer, String> partitionerConsumer;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		topic = environment.getRequiredProperty("partitioner_topic");
	}

	public ConsumerRecords<Integer, String> poll() {
		partitionerConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			return partitionerConsumer.poll(TIMEOUT);
		}
	}

}
