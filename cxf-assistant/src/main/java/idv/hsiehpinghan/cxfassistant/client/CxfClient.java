package idv.hsiehpinghan.cxfassistant.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.CxfWebService;

@Component
public class CxfClient {
	private final String ENDPOINT_ADDRESS = "http://localhost:9000/" + CxfWebService.class.getSimpleName();

	public String request(String text) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CxfWebService.class);
		factory.setAddress(ENDPOINT_ADDRESS);
		CxfWebService webService = (CxfWebService) factory.create();
		return webService.execute(text);
	}
}
