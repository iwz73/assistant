package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.RoutingConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.RoutingProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class RoutingTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private RoutingProducer routingProducer;
	@Autowired
	private RoutingConsumer routingConsumer;

	@Test
	public void consume() throws Exception {
		routingConsumer.consume();
	}

	@Test(dependsOnMethods = { "consume" })
	public void publish() throws Exception {
		routingProducer.publish(MESSAGE);
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = routingConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}

}
