package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.PrefetchCountConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.PrefetchCountProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class PrefetchCountTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private PrefetchCountProducer prefetchCountProducer;
	@Autowired
	private PrefetchCountConsumer prefetchCountConsumer;

	@Test
	public void publish() throws Exception {
		prefetchCountProducer.publish(MESSAGE);
	}

	/**
	 * restart rabbitmq and check if data exists.
	 */
	@Test(dependsOnMethods = { "publish" })
	public void consume() throws Exception {
		prefetchCountConsumer.consume();
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = prefetchCountConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}
}
