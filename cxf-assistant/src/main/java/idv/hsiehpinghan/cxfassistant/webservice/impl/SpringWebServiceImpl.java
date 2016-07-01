package idv.hsiehpinghan.cxfassistant.webservice.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import idv.hsiehpinghan.cxfassistant.webservice.SpringWebService;

@Component
@WebService(serviceName = "SpringWebServiceImpl")
public class SpringWebServiceImpl implements SpringWebService {

	@Override
	public String execute(String text) {
		return text;
	}

}
