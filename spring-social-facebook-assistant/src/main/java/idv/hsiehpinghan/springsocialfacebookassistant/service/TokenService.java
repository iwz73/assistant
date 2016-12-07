package idv.hsiehpinghan.springsocialfacebookassistant.service;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

@Service
public class TokenService implements InitializingBean {
	// private String appNamespace;
	private String appId;
	private String appSecret;
	private String siteUrl;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		// appNamespace = environment.getRequiredProperty("appNamespace");
		appId = environment.getRequiredProperty("appId");
		appSecret = environment.getRequiredProperty("appSecret");
		siteUrl = environment.getRequiredProperty("siteUrl");
	}

	public String getAppAccessTokenContent() throws IOException {
		String httpsUrl = String.format(
				"https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=client_credentials&redirect_uri=%s",
				appId, appSecret, siteUrl);
		return UrlUtility.getContent(httpsUrl);
	}

	public String getAppAccessToken() throws IOException {
		return getAppAccessTokenContent().split("=")[1];
	}

	public String getLongLivedTokenContent(String shortLivedToken) throws IOException {
		final String httpsUrl = String.format(
				"https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=fb_exchange_token&fb_exchange_token=%s",
				appId, appSecret, shortLivedToken);
		return UrlUtility.getContent(httpsUrl);
	}

}
