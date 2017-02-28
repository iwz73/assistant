package idv.hsiehpinghan.springintegrationassistant.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springintegrationassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springintegrationassistant.service.BasicGatewayService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicGatewayTest extends AbstractTestNGSpringContextTests {
	private static final String message = "basicGateway";
	@Autowired
	@Qualifier("basicGatewayGateway")
	private BasicGatewayService basicGatewayService;

	@Test
	public void test() throws Exception {
		String actual = basicGatewayService.method_0(message);
		Assert.assertEquals(actual, message.toUpperCase());
	}
}