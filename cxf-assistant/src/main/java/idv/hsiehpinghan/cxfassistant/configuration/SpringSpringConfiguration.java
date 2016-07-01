package idv.hsiehpinghan.cxfassistant.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import idv.hsiehpinghan.cxfassistant.webservice.SpringWebService;
import idv.hsiehpinghan.cxfassistant.webservice.impl.SpringWebServiceImpl;

@Configuration("cxfAssistantSpringConfiguration_Spring")
@ComponentScan(basePackages = { "idv.hsiehpinghan.cxfassistant" })
public class SpringSpringConfiguration {

	@Bean
	public Endpoint endpoint(SpringWebServiceImpl springWebServiceImpl) {
		EndpointImpl endpoint = new EndpointImpl(springWebServiceImpl);
		endpoint.publish("http://localhost:9000/" + SpringWebService.class.getSimpleName());
		return endpoint;
	}
}