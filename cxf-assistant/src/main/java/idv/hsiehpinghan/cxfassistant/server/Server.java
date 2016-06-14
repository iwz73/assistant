package idv.hsiehpinghan.cxfassistant.server;

import javax.xml.ws.Endpoint;

import idv.hsiehpinghan.cxfassistant.webservice.HelloWorld;
import idv.hsiehpinghan.cxfassistant.webservice.impl.HelloWorldImpl;

public class Server {
	public Server() {
		System.out.println("Starting Server");
		HelloWorld helloWorld = new HelloWorldImpl();
		String address = "http://localhost:9000/HelloWorld";
		Endpoint.publish(address, helloWorld);
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Server();
		System.out.println("Server ready...");
		Thread.sleep(5*60*1000);
		System.out.println("Server existing");
		System.exit(0);
	}
}
