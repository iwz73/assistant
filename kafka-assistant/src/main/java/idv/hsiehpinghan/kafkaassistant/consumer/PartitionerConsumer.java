package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.kafkaassistant.partitioner.PartitionerPartitioner;

@Component
public class PartitionerConsumer {
	private final long TIMEOUT = 5000;
	@Autowired
	@Qualifier("partitionerConsumer_0")
	private Consumer<Integer, String> partitionerConsumer;
	@Autowired
	@Qualifier("partitionerConsumer_1")
	private Consumer<Integer, String> partitionerFetchAllConsumer;

	public ConsumerRecords<Integer, String> poll(String topic) {
		partitionerConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			return partitionerConsumer.poll(TIMEOUT);
		}
	}

	public ConsumerRecords<Integer, String> fetchAll(String topic) {
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
