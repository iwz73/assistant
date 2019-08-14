package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.BasicConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.BasicProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;

	@Test
	public void publish() throws Exception {
		basicProducer.publish(MESSAGE);
	}

	@Test(dependsOnMethods = { "publish" })
	public void consume() throws Exception {
		basicConsumer.consume();
		Thread.sleep(SLEEP_MILLISECONDS);
		List<String> messages = basicConsumer.getMessages();
		Assert.assertTrue(messages.contains(MESSAGE));
	}
}
