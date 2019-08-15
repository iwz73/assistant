package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.TopicConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.TopicProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TopicTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private TopicProducer topicProducer;
	@Autowired
	private TopicConsumer topicConsumer;

	@Test
	public void consume() throws Exception {
		topicConsumer.consume();
	}

	@Test(dependsOnMethods = { "consume" })
	public void publish() throws Exception {
		topicProducer.publish(MESSAGE);
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = topicConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}

}
