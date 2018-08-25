package idv.hsiehpinghan.kafkaassistant2.producer;

import java.util.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant2.configuration.SpringConfiguration;
import idv.hsiehpinghan.kafkaassistant2.consumer.BasicConsumer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	private final Long KEY = new Date().getTime();
	private final String VALUE = String.valueOf(KEY);
	@Value("${basic.topic}")
	private String topic;
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;

	@Test
	public void send() throws Exception {
		RecordMetadata recordMetadata = basicProducer.send(KEY, VALUE);
		Assert.assertEquals(recordMetadata.topic(), topic);
	}

	@Test(dependsOnMethods = { "send" })
	public void poll() {
		ConsumerRecords<Long, String> consumerRecords = basicConsumer.poll();
		boolean isExist = false;
		for (ConsumerRecord<Long, String> consumerRecord : consumerRecords) {
			Long key = consumerRecord.key();
			String value = consumerRecord.value();
			if (KEY.equals(key) && VALUE.equals(value)) {
				isExist = true;
			}
		}
		Assert.assertTrue(isExist);
	}
}
