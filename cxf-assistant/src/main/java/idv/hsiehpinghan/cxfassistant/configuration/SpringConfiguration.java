package idv.hsiehpinghan.cxfassistant.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import idv.hsiehpinghan.cxfassistant.webservice.impl.HelloWorldImpl;

@Configuration("cxfAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.cxfassistant" })
public class SpringConfiguration {

	@Bean 
	public Endpoint endpoint(HelloWorldImpl helloWorldImpl) {
	    EndpointImpl endpoint = new EndpointImpl(helloWorldImpl); 
	    endpoint.publish("http://localhost:9000/HelloWorld"); 
	    return endpoint; 
	} 
}