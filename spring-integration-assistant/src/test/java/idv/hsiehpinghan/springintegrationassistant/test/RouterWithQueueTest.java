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
public class RouterWithQueueTest extends AbstractTestNGSpringContextTests {
	private static final String MESSAGE_0 = "basicRouter";
	@Autowired
	@Qualifier("routerWithQueueChannel")
	private MessageChannel messageChannel;

	@Test
	public void test() throws Exception {
		for (int i = 0; i < 10; ++i) {
			String msg = MESSAGE_0 + i;
			testRouter(msg);
			System.err.println("sent : " + msg);
			testRouter(i);
			System.err.println("sent : " + i);
		}
	}

	private void testRouter(Object msg) {
		Message<Object> message = MessageBuilder.withPayload(msg).build();
		messageChannel.send(message);
	}
}