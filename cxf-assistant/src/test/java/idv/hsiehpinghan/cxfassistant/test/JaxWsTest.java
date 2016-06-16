package idv.hsiehpinghan.cxfassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.cxfassistant.client.JaxWsClient;
import idv.hsiehpinghan.cxfassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.cxfassistant.server.JaxWsServer;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JaxWsTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JaxWsServer server;
	@Autowired
	private JaxWsClient client;

	@Test
	public void execute() {
		client.request();
	}

}
