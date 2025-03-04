package idv.hsiehpinghan.cxfassistant.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.JaxWsWebService;

@Component
public class JaxWsClient {
	private final QName SERVICE_NAME = new QName("http://webservice.cxfassistant.hsiehpinghan.idv/",
			JaxWsWebService.class.getSimpleName());
	private final QName PORT_NAME = new QName("http://webservice.cxfassistant.hsiehpinghan.idv/",
			"JaxWsWebServicePort");
	private final String ENDPOINT_ADDRESS = "http://localhost:9000/" + JaxWsWebService.class.getSimpleName();

	public String request(String text) {
		Service service = generateService();
		JaxWsWebService webService = service.getPort(JaxWsWebService.class);
		return webService.execute(text);
	}

	private Service generateService() {
		Service service = Service.create(SERVICE_NAME);
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
		return service;
	}
}
