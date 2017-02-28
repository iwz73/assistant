package idv.hsiehpinghan.springintegrationassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springintegrationassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicChannelTest extends AbstractTestNGSpringContextTests {
	private static final String MESSAGE = "basicChannel";
	@Autowired
	@Qualifier("basicChannelChannel")
	private MessageChannel messageChannel;

	@Test
	public void test() throws Exception {
		Message<String> message = MessageBuilder.withPayload(MESSAGE).build();
		messageChannel.send(message);
	}
}