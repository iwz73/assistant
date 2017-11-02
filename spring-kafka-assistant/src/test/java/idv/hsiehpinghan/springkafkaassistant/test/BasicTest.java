package idv.hsiehpinghan.springkafkaassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springkafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springkafkaassistant.producer.BasicProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	private final long ONE_SECOND = 1000;
	@Autowired
	private BasicProducer basicProducer;

	@Test
	public void sendAndReceive() throws Exception {
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener established...");
			Thread.sleep(ONE_SECOND);
		}
		basicProducer.send("BasicTest");
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener receive message...");
			Thread.sleep(ONE_SECOND);
		}
	}
}
