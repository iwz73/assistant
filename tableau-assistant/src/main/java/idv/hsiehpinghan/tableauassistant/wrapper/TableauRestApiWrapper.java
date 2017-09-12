package idv.hsiehpinghan.tableauassistant.wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import idv.hsiehpinghan.tableauassistant.model.SignInRequestBody;
import idv.hsiehpinghan.tableauassistant.model.SignInResponseBody;
import idv.hsiehpinghan.tableauassistant.utility.JAXBUtility;

@Component
public class TableauRestApiWrapper implements InitializingBean {
	private final Logger LOGGER = LoggerFactory.getLogger(TableauRestApiWrapper.class);
	// Authentication
	private String signInUrl;
	private String signOutUrl;
//	private String switchSiteUrl;
	// Sites
	private String createSiteUrl;	
	
	
	@Autowired
	@Qualifier("signInRequestBodyJAXBContext")
	private JAXBContext signInRequestJAXBContext;
	@Autowired
	@Qualifier("signInResponseBodyJAXBContext")
	private JAXBContext signInResponseJAXBContext;
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		String tableauServerIp = environment.getRequiredProperty("tableau_server_ip");
		String tableauVersion = environment.getRequiredProperty("tableau_version");

		signInUrl = String.format("https://%s/api/%s/%s/%s", tableauServerIp, tableauVersion, "auth", "signin");
		signOutUrl = String.format("https://%s/api/%s/%s/%s", tableauServerIp, tableauVersion, "auth", "signout");
//		switchSiteUrl = String.format("https://%s/api/%s/%s/%s", tableauServerIp, tableauVersion, "auth", "switchSite");
		createSiteUrl = String.format("https://%s/api/%s/%s", tableauServerIp, tableauVersion, "sites");

		
	}

	public SignInResponseBody signIn(SignInRequestBody signInRequestBody) throws JAXBException {
		String requestBody = JAXBUtility.marshallAsString(signInRequestJAXBContext, signInRequestBody);
		LOGGER.debug("signIn requestBody : {}", requestBody);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(signInUrl, signInRequestBody, String.class);
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode != 200) {
			throw new RuntimeException(String.format("statusCode({%d}) != 200 !!!", statusCode));
		}
		String responseBody = responseEntity.getBody();
		LOGGER.debug("signIn responseBody : {}", responseBody);
		SignInResponseBody signInResponseBody = JAXBUtility.unmarshallWithString(signInResponseJAXBContext,
				responseBody, SignInResponseBody.class);
		return signInResponseBody;
	}

	public void signOut(String token) {
		LOGGER.debug("signOut");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-Tableau-Auth", token);
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(signOutUrl, httpEntity, String.class);
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode != 204) {
			throw new RuntimeException(String.format("statusCode({%d}) != 204 !!!", statusCode));
		}
	}
}
