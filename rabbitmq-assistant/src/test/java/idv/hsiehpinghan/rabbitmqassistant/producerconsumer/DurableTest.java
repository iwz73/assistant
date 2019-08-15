package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.DurableConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.DurableProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DurableTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private DurableProducer durableProducer;
	@Autowired
	private DurableConsumer durableConsumer;

	@Test
	public void publish() throws Exception {
		durableProducer.publish(MESSAGE);
	}

	/**
	 * restart rabbitmq and check if data exists.
	 */
	@Test(dependsOnMethods = { "publish" })
	public void consume() throws Exception {
		durableConsumer.consume();
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = durableConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}
}
