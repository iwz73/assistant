package idv.hsiehpinghan.rabbitmqassistant.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.rabbitmqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.rabbitmqassistant.consumer.RpcConsumer;
import idv.hsiehpinghan.rabbitmqassistant.producer.RpcProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class RpcTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILLISECONDS = 1000 * 3;
	private final String MESSAGE = String.valueOf(System.currentTimeMillis());
	@Autowired
	private RpcProducer rpcProducer;
	@Autowired
	private RpcConsumer rpcConsumer;

	@Test
	public void publish() throws Exception {
		runConsumerInOtherThread();
		Thread.sleep(SLEEP_MILLISECONDS);
		String returnMessage = rpcProducer.publish(MESSAGE);
		Assert.assertEquals(String.format("Hello, %s.", MESSAGE), returnMessage);
	}

	private void runConsumerInOtherThread() {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					rpcConsumer.consume();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.submit(runnable);
	}
}
