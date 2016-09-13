package idv.hsiehpinghan.instagramassistant.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.instagramassistant.utility.UrlUtility;

@Service
public class TokenService implements InitializingBean {
	private final String HTTPS_URL = "https://api.instagram.com/oauth/access_token";
	private String clientId;
	private String clientSecret;
	private String serverSideRedirectUri;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		clientId = environment.getRequiredProperty("clientId");
		clientSecret = environment.getRequiredProperty("clientSecret");
		serverSideRedirectUri = environment.getRequiredProperty("serverSideRedirectUri");
	}

	public String getAccessToken(String code) throws IOException {
		final String PARAMETER = "grant_type=" + URLEncoder.encode("authorization_code","UTF-8") + "&client_id=" + URLEncoder.encode(clientId,"UTF-8") + "&client_secret="
				+ URLEncoder.encode(clientSecret,"UTF-8") + "&redirect_uri=" + URLEncoder.encode(serverSideRedirectUri,"UTF-8") + "&code=" + URLEncoder.encode(code,"UTF-8");
		
		System.err.println(PARAMETER);
		
		final Map<String, String> REQUEST_PROPERTIES = getRequestProperties(PARAMETER);
//		
//		 curl -F 'client_id=d9f9a50903ea4e1092469b4804237a5a' \
//		    -F 'client_secret=4fe82076c53e48ceab6e2fce1dfd72d2' \
//		    -F 'grant_type=authorization_code' \
//		    -F 'redirect_uri=http://localhost:8080/instagram-assistant/instagram/serverSideAssessToken' \
//		    -F 'code=ee7363e0bd954c7c984d0c5722c61828' \
//		    https://api.instagram.com/oauth/access_token
		
//		con.setRequestProperty("Content-Type","application/x-www- form-urlencoded");	
		
		return UrlUtility.getContent(HTTPS_URL, REQUEST_PROPERTIES, PARAMETER);
	}

	private Map<String, String> getRequestProperties(String parameter) {
		Map<String, String> map = new HashMap<>();
		map.put("Content-Type", "application/x-www-form-urlencoded");
		map.put("Content-length", String.valueOf(parameter.length())); 
		return map;
	}
}