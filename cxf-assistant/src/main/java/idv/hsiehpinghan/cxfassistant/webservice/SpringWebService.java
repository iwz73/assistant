package idv.hsiehpinghan.cxfassistant.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SpringWebService {
	String execute(@WebParam(name = "text") String text);
}
