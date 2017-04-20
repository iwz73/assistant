package idv.hsiehpinghan.kafkaassistant.assistant;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;

@Test(dependsOnGroups = { "ProducerAssistantTest" })
@ContextConfiguration(classes = { SpringConfiguration.class })
public class ConsumerAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ConsumerAssistant assistant;

	@Test
	public void poll() {
		ConsumerRecords<String, String> consumerRecords = assistant.poll();
		int i = 0;
		for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
			String actual = consumerRecord.value();
			Assert.assertEquals(actual, ProducerAssistantTest.VALUE);
			++i;
		}
		Assert.assertTrue(i > 0);
	}

}
