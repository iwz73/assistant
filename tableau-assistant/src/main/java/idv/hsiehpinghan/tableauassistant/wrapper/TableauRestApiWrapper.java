package idv.hsiehpinghan.tableauassistant.wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import idv.hsiehpinghan.tableauassistant.model.SignInRequest;
import idv.hsiehpinghan.tableauassistant.model.SignInResponse;
import idv.hsiehpinghan.tableauassistant.utility.JAXBUtility;

@Component
public class TableauRestApiWrapper implements InitializingBean {
	private final Logger LOGGER = LoggerFactory.getLogger(TableauRestApiWrapper.class);
	private String signInUrl;
	@Autowired
	@Qualifier("signInRequestJAXBContext")
	private JAXBContext signInRequestJAXBContext;
	@Autowired
	@Qualifier("signInResponseJAXBContext")
	private JAXBContext signInResponseJAXBContext;
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		String tableauServerUrl = environment.getRequiredProperty("tableau_server_url");
		String tableauVersion = environment.getRequiredProperty("tableau_version");
		// Authentication
		signInUrl = String.format("%s/api/%s/%s/%s", tableauServerUrl, tableauVersion, "auth", "signin");

	}

	public SignInResponse signIn(SignInRequest signInRequest) throws JAXBException {
		String requestBody = JAXBUtility.marshallAsString(signInRequestJAXBContext, signInRequest);
		LOGGER.debug("requestBody : {}", requestBody);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(signInUrl, signInRequest, String.class);
		String responseBody = responseEntity.getBody();
		LOGGER.debug("responseBody : {}", responseBody);
		SignInResponse signInResponse = JAXBUtility.unmarshallWithString(signInResponseJAXBContext, responseBody,
				SignInResponse.class);
		return signInResponse;
	}

}
