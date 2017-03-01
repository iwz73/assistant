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
public class BasicRouterTest extends AbstractTestNGSpringContextTests {
	private static final String MESSAGE_0 = "basicRouter";
	private static final Integer MESSAGE_1 = 3;
	@Autowired
	@Qualifier("basicRouterChannel")
	private MessageChannel messageChannel;

	@Test
	public void test() throws Exception {
		testRouter(MESSAGE_0);
		testRouter(MESSAGE_1);
	}

	private void testRouter(Object msg) {
		Message<Object> message = MessageBuilder.withPayload(msg).build();
		messageChannel.send(message);
	}
}