package idv.hsiehpinghan.kafkaassistant.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.kafkaassistant.consumer.PartitionerConsumer;
import idv.hsiehpinghan.kafkaassistant.partitioner.PartitionerPartitioner;
import idv.hsiehpinghan.kafkaassistant.producer.PartitionerProducer;

/**
 * execute "/opt/kafka_2.11-0.10.1.1/bin/kafka-topics.sh --create --zookeeper
 * localhost:2181 --replication-factor 2 --partitions 2 --topic
 * partitioner_topic_2" first.
 * 
 * @author thank
 *
 */
@ContextConfiguration(classes = { SpringConfiguration.class })
public class PartitionerTest extends AbstractTestNGSpringContextTests {
	public static final String TOPIC = "partitioner_topic";
	public static final String VALUE = "partitionerProducer send";
	public static final int SENT_AMOUT = 10;
	@Autowired
	private PartitionerProducer partitionerProducer;
	@Autowired
	private PartitionerConsumer partitionerConsumer;

	@Test
	public void send() throws Exception {
		for (int i = 0; i < SENT_AMOUT; ++i) {
			RecordMetadata recordMetadata = partitionerProducer.send("partitioner_topic",
					i % PartitionerPartitioner.PARTITION_COUNT, VALUE);
			Assert.assertEquals(recordMetadata.topic(), TOPIC);
		}
	}

	@Test(dependsOnMethods = { "send" })
	public void poll() {
		ConsumerRecords<Integer, String> consumerRecords = partitionerConsumer.poll(TOPIC);
		int receiveAmount = 0;
		for (ConsumerRecord<Integer, String> consumerRecord : consumerRecords) {
			Assert.assertEquals(consumerRecord.value(), VALUE);
			++receiveAmount;
		}
		Assert.assertTrue(SENT_AMOUT <= receiveAmount);
	}

	@Test(dependsOnMethods = { "poll" })
	public void fetchAll() {
		ConsumerRecords<Integer, String> consumerRecords = partitionerConsumer.fetchAll(TOPIC);
		int receiveAmount = 0;
		for (ConsumerRecord<Integer, String> consumerRecord : consumerRecords) {
			Assert.assertEquals(consumerRecord.value(), VALUE);
			++receiveAmount;
		}
		Assert.assertTrue(receiveAmount >= SENT_AMOUT);
	}
}
