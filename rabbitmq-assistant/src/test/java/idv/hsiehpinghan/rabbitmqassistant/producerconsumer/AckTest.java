package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.AckConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.AckProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class AckTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private AckProducer ackProducer;
	@Autowired
	private AckConsumer ackConsumer;

	@Test
	public void publish() throws Exception {
		ackProducer.publish(MESSAGE);
	}

	/**
	 * run twice to be success !!!
	 */
	@Test(dependsOnMethods = { "publish" })
	public void consume() throws Exception {
		ackConsumer.consume();
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = ackConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}
}
