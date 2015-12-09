package idv.hsiehpinghan.springsocialfacebookassistant.service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
	@Autowired
	private TokenService tokenService;

	public String getLoginInfo(String accessToken) throws IOException {
		final String httpsUrl = "https://graph.facebook.com/me?access_token="
				+ accessToken;
		return UrlUtility.getContent(httpsUrl);
	}

	public String getTokenInfo(String inputToken) throws IOException {
		String appAccessToken = tokenService.getAppAccessToken().split("=")[1];
		final String httpsUrl = "https://graph.facebook.com/debug_token?input_token="
				+ inputToken + "&access_token=" + appAccessToken;
		return UrlUtility.getContent(httpsUrl);
	}

}
