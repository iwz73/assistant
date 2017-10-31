package idv.hsiehpinghan.springkafkaassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springkafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springkafkaassistant.model.JsonModel;
import idv.hsiehpinghan.springkafkaassistant.producer.JsonProducer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JsonTest extends AbstractTestNGSpringContextTests {
	private final long ONE_SECOND = 1000;
	@Autowired
	private JsonProducer jsonProducer;

	@Test
	public void sendAndReceive() throws Exception {
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener established...");
			Thread.sleep(ONE_SECOND);
		}
		JsonModel message = new JsonModel(0, "JsonTest_0");
		jsonProducer.send(message);
		for (int i = 0; i < 5; ++i) {
			System.err.println("waiting @KafkaListener receive message...");
			Thread.sleep(ONE_SECOND);
		}
	}
}
