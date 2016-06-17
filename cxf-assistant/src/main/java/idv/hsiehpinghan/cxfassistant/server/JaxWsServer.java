package idv.hsiehpinghan.cxfassistant.server;

import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.JaxWsWebService;
import idv.hsiehpinghan.cxfassistant.webservice.impl.JaxWsWebServiceImpl;

@Component
public class JaxWsServer {
	private final String ADDRESS = "http://localhost:9000/" + JaxWsWebService.class.getSimpleName();

	public void start() {
		System.err.println("Starting " + this.getClass().getSimpleName() + "...");
		JaxWsWebService webService = new JaxWsWebServiceImpl();
		Endpoint.publish(ADDRESS, webService);
	}

}
