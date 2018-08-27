package idv.hsiehpinghan.kafkaassistant2.kafkastreams;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.apache.kafka.streams.test.OutputVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant2.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class StreamsDslKafkaStreamsTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private StreamsDslKafkaStreams streamsDslKafkaStreams;

	@Test
	public void startTopicStreamTopic() throws Exception {
		String inputTopic = "topicStreamTopicInputTopic";
		String outputTopic = "topicStreamTopicOutputTopic";
		Topology topology = streamsDslKafkaStreams.generateTopicStreamTopicTopology(inputTopic, outputTopic);
		Properties properties = streamsDslKafkaStreams.generateProperties();
		try (TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology, properties);) {
			ConsumerRecordFactory<Long, String> consumerRecordFactory = new ConsumerRecordFactory<>(inputTopic,
					new LongSerializer(), new StringSerializer());
			Long key = new Date().getTime();
			String value = String.valueOf(key);
			ConsumerRecord<byte[], byte[]> consumerRecord = consumerRecordFactory.create(key, value);
			topologyTestDriver.pipeInput(consumerRecord);
			ProducerRecord<Long, String> producerRecord = topologyTestDriver.readOutput(outputTopic,
					new LongDeserializer(), new StringDeserializer());
			OutputVerifier.compareKeyValue(producerRecord, key, value);
		}
	}

}
