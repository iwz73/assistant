package idv.hsiehpinghan.kafkaassistant.assistant;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ProducerAssistantTest extends AbstractTestNGSpringContextTests {
	private final String VALUE = "ProducerAssistant send";
	@Autowired
	private Environment environment;
	@Autowired
	private ProducerAssistant assistant;

	@Test
	public void send() throws Exception {
		RecordMetadata recordMetadata = assistant.send(VALUE);
		Assert.assertEquals(recordMetadata.topic(), environment.getRequiredProperty("test_topic_0"));
	}
}
