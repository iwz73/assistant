package idv.hsiehpinghan.kafkaassistant.partitioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class PartitionerPartitioner implements Partitioner {
	public static final int PARTITION_COUNT = 1;

	@Override
	public void configure(Map<String, ?> configs) {
		// do nothing
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		int partitionCount = cluster.partitionCountForTopic(topic).intValue();
		if (partitionCount != PARTITION_COUNT) {
			throw new RuntimeException(String.format("partitionCount(%d) != %d", partitionCount, PARTITION_COUNT));
		}
		return (Integer) key;
	}

	@Override
	public void close() {
		// do nothing
	}

}
