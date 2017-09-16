package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.kafkaassistant.partitioner.PartitionerPartitioner;

@Component
public class PartitionerConsumer implements InitializingBean {
	private final long TIMEOUT = 5000;
	private String topic;
	@Autowired
	@Qualifier("partitionerConsumer_0")
	private Consumer<Integer, String> partitionerConsumer;
	@Autowired
	@Qualifier("partitionerConsumer_1")
	private Consumer<Integer, String> partitionerFetchAllConsumer;
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

	public ConsumerRecords<Integer, String> fetchAll() {
		List<TopicPartition> topicPartitions = new ArrayList<>(PartitionerPartitioner.PARTITION_COUNT);
		for (int i = 0, size = PartitionerPartitioner.PARTITION_COUNT; i < size; ++i) {
			TopicPartition topicPartition = new TopicPartition(topic, i);
			topicPartitions.add(topicPartition);
		}
		partitionerFetchAllConsumer.assign(topicPartitions);
		partitionerFetchAllConsumer.seekToBeginning(topicPartitions);
		while (true) {
			return partitionerFetchAllConsumer.poll(TIMEOUT);
		}
	}
}
