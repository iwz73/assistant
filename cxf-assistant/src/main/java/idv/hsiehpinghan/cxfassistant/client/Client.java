package idv.hsiehpinghan.cxfassistant.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import idv.hsiehpinghan.cxfassistant.webservice.HelloWorld;

public class Client {
	private static final QName SERVICE_NAME = new QName("http://webservice.cxfassistant.hsiehpinghan.idv/", "HelloWorld");
	private static final QName PORT_NAME = new QName("http://webservice.cxfassistant.hsiehpinghan.idv/", "HelloWorldPort");
	
	public static void main(String[] args) {
		System.out.println("Starting Client");
		Service service = Service.create(SERVICE_NAME);
		String endpointAddress = "http://localhost:9000/HelloWorld";
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		HelloWorld helloWorld = service.getPort(HelloWorld.class);
		System.out.println(helloWorld.sayHello("thank "));
		System.err.println(0);
	}
}
