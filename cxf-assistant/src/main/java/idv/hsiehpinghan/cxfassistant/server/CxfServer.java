package idv.hsiehpinghan.cxfassistant.server;

import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.CxfWebService;
import idv.hsiehpinghan.cxfassistant.webservice.impl.CxfWebServiceImpl;

@Component
public class CxfServer {
	private final String ADDRESS = "http://localhost:9000/" + CxfWebService.class.getSimpleName();

	public void start() {
		System.err.println("Starting " + this.getClass().getSimpleName() + "...");
		CxfWebService webService = new CxfWebServiceImpl();
		Endpoint.publish(ADDRESS, webService);
	}

}
