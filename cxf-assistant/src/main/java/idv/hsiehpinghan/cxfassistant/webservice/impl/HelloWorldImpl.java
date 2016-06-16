package idv.hsiehpinghan.cxfassistant.webservice.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.HelloWorld;

@Component
@WebService(serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHello(@WebParam(name = "text") String text) {
		System.out.println("sayHello called");
		return "Hello " + text + ", This is world.";
	}

}
