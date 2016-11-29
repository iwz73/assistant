package idv.hsiehpinghan.springintegrationassistant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import siia.helloworld.channel.HelloService;

public class App {
	public static void main(String[] args) {
		// String cfg = "applicationContext.xml";
		// ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		// MessageChannel channel = context.getBean("names",
		// MessageChannel.class);
		// Message<String> message =
		// MessageBuilder.withPayload("World").build();
		// channel.send(message);

		String cfg = "applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		HelloService helloService = context.getBean("helloGateway", HelloService.class);
		System.out.println(helloService.sayHello("World"));
	}
}
