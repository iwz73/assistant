package idv.hsiehpinghan.cxfassistant.webservice.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import idv.hsiehpinghan.cxfassistant.webservice.HelloWorld;

@WebService(serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHello(@WebParam(name = "text") String text) {
		System.out.println("sayHello called");
		return "Hello " + text + ", This is world.";
	}

}
