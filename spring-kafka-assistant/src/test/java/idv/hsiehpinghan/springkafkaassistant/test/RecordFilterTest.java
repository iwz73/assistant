package idv.hsiehpinghan.springkafkaassistant.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springkafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springkafkaassistant.producer.RecordFilterProducer;

@RunWith(SpringRunner.class)
@Import(SpringConfiguration.class)
public class RecordFilterTest {
	private final long ONE_SECOND = 1000;
	@Autowired
	private RecordFilterProducer recordFilterProducer;

	@Test
	public void sendAndReceive() throws Exception {
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener established...");
			Thread.sleep(ONE_SECOND);
		}
		for (int i = 0; i < 10; ++i) {
			recordFilterProducer.send(String.valueOf(i));
		}
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener receive message...");
			Thread.sleep(ONE_SECOND);
		}
	}
}
