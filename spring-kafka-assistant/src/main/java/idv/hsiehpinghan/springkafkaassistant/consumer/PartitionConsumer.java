package idv.hsiehpinghan.springkafkaassistant.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaassistant.constant.KafkaConstant;

@Component
public class PartitionConsumer {
	@KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstant.PARTITION_TOPIC, partitionOffsets = {
			@PartitionOffset(partition = "0", initialOffset = "0") }))
	public void listener(String message) {
		System.err.println(String.format("PartitionConsumer receive : %s", message));
	}
}
