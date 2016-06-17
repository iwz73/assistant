package idv.hsiehpinghan.cxfassistant.webservice.impl;

import javax.jws.WebService;

import idv.hsiehpinghan.cxfassistant.webservice.JaxWsWebService;

@WebService(serviceName = "JaxWsWebServiceImpl")
public class JaxWsWebServiceImpl implements JaxWsWebService {

	@Override
	public String execute(String text) {
		return text;
	}

}
