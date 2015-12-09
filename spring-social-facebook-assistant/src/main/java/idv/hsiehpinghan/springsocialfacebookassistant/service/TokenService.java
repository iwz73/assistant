package idv.hsiehpinghan.springsocialfacebookassistant.service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements InitializingBean {
	// private String appNamespace;
	private String appId;
	private String appSecret;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		// appNamespace = environment.getRequiredProperty("appNamespace");
		appId = environment.getRequiredProperty("appId");
		appSecret = environment.getRequiredProperty("appSecret");
	}

	public String getAppAccessToken() throws IOException {
		String httpsUrl = "https://graph.facebook.com/oauth/access_token?client_id="
				+ appId
				+ "&amp;client_secret="
				+ appSecret
				+ "&amp;grant_type=client_credentials";
		return UrlUtility.getContent(httpsUrl);
	}

	public String getLongLivedToken(String shortLivedToken) throws IOException {
		final String httpsUrl = "https://graph.facebook.com/oauth/access_token?client_id="
				+ appId
				+ "&client_secret="
				+ appSecret
				+ "&grant_type=fb_exchange_token&fb_exchange_token="
				+ shortLivedToken;
		return UrlUtility.getContent(httpsUrl);
	}

}
