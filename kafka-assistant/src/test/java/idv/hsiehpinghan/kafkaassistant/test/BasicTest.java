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
import idv.hsiehpinghan.kafkaassistant.consumer.BasicConsumer;
import idv.hsiehpinghan.kafkaassistant.producer.BasicProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	public static final String TOPIC = "basic_topic";
	public static final String VALUE = "basicProducer send";
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;

	@Test
	public void send() throws Exception {
		RecordMetadata recordMetadata = basicProducer.send(TOPIC, VALUE);
		Assert.assertEquals(recordMetadata.topic(), TOPIC);
	}

	@Test(dependsOnMethods = { "send" })
	public void poll() {
		ConsumerRecords<Long, String> consumerRecords = basicConsumer.poll(TOPIC);
		int i = 0;
		for (ConsumerRecord<Long, String> consumerRecord : consumerRecords) {
			String actual = consumerRecord.value();
			Assert.assertEquals(actual, VALUE);
			++i;
		}
		Assert.assertTrue(i > 0);
	}

}
