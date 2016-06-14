package idv.hsiehpinghan.cxfassistant.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import idv.hsiehpinghan.cxfassistant.webservice.HelloWorld;

public class CxfClient {

	public static void main(String[] args) {
		System.out.println("Starting Client");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9000/HelloWorld");
		HelloWorld helloWorld = (HelloWorld) factory.create();
		System.out.println(helloWorld.sayHello("thank "));
		System.err.println(0);
	}
}
