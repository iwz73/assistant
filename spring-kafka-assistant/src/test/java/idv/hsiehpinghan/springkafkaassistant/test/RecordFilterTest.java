package idv.hsiehpinghan.springkafkaassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springkafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springkafkaassistant.producer.RecordFilterProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class RecordFilterTest extends AbstractTestNGSpringContextTests {
	private final long ONE_SECOND = 1000;
	@Autowired
	private RecordFilterProducer recordFilterProducer;

	@Test
	public void sendAndReceive() throws Exception {
		for (int i = 0; i < 10; ++i) {
			System.err.println("waiting @KafkaListener established...");
			Thread.sleep(ONE_SECOND);
		}
		for (int i = 0; i < 10; ++i) {
			recordFilterProducer.send(String.valueOf(i));
		}
		for (int i = 0; i < 10; ++i) {
			System.err.println("waiting @KafkaListener receive message...");
			Thread.sleep(ONE_SECOND);
		}
	}
}
