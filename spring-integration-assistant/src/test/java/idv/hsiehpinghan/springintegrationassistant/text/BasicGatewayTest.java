package idv.hsiehpinghan.springintegrationassistant.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springintegrationassistant.configuration.SpringConfiguration;
import siia.helloworld.channel.HelloService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicGatewayTest extends AbstractTestNGSpringContextTests {
	@Autowired
	@Qualifier("helloGateway")
	private HelloService helloService;

	@Test
	public void test() throws Exception {
		System.err.println(helloService.sayHello("WWW"));
	}
}