package idv.hsiehpinghan.cxfassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.cxfassistant.client.CxfClient;
import idv.hsiehpinghan.cxfassistant.configuration.CxfSpringConfiguration;
import idv.hsiehpinghan.cxfassistant.server.CxfServer;

@ContextConfiguration(classes = { CxfSpringConfiguration.class })
public class CxfTest extends AbstractTestNGSpringContextTests {
	private final String TEXT = "text";
	@Autowired
	private CxfServer server;
	@Autowired
	private CxfClient client;

	@BeforeClass
	public void beforeClass() {
		server.start();
	}

	@Test
	public void execute() {
		Assert.assertEquals(client.request(TEXT), TEXT);
	}

}
