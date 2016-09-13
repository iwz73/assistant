package idv.hsiehpinghan.instagramassistant.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.instagramassistant.utility.UrlUtility;

@Service
public class InfoService {
	@Autowired
	private Environment environment;
	@Autowired
	private TokenService tokenService;

	public String getEndpointData(String code, String endpoint, String redirectUri) throws IOException {
		String instagramApiUri = environment.getRequiredProperty("instagramApiUri");
		String accessToken = tokenService.getAccessToken(code, redirectUri);
		String httpsUrl = instagramApiUri + endpoint + "/?access_token=" + accessToken;
		return UrlUtility.getContent(httpsUrl);
	}

}
