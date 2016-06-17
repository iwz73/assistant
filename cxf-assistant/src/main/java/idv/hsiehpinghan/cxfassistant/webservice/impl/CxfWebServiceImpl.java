package idv.hsiehpinghan.cxfassistant.webservice.impl;

import javax.jws.WebService;

import idv.hsiehpinghan.cxfassistant.webservice.CxfWebService;

@WebService(serviceName = "CxfWebServiceImpl")
public class CxfWebServiceImpl implements CxfWebService {

	@Override
	public String execute(String text) {
		return text;
	}

}
