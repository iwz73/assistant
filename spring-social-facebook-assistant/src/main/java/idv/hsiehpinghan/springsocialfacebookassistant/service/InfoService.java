package idv.hsiehpinghan.springsocialfacebookassistant.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

@Service
public class InfoService {
//	@Autowired
//	private TokenService tokenService;

	public String getLoginInfo(String accessToken) throws IOException {
		final String httpsUrl = String.format("https://graph.facebook.com/me?access_token=%s", accessToken);
		return UrlUtility.getContent(httpsUrl);
	}

	public String getTokenInfo(String inputToken, String appAccessToken) throws IOException {
//		String appAccessToken = tokenService.getAppAccessToken().split("=")[1];
		final String httpsUrl = String.format("https://graph.facebook.com/debug_token?input_token=%s&access_token=%s",
				inputToken, appAccessToken);
		return UrlUtility.getContent(httpsUrl);
	}

	public String getFeeds(String pageId, String appAccessToken) throws IOException {
//		String appAccessToken = tokenService.getAppAccessToken().split("=")[1];
		final String httpsUrl = String.format("https://graph.facebook.com/%s/feed?fields=message&access_token=%s",
				pageId, appAccessToken);
		return UrlUtility.getContent(httpsUrl);
	}

	public String getNodeType(String pageId, String appAccessToken) throws IOException {
//		String appAccessToken = tokenService.getAppAccessToken().split("=")[1];
		final String httpsUrl = String.format(
				"https://graph.facebook.com/%s?fields=metadata{type}&metadata=1&access_token=%s", pageId,
				appAccessToken);
		return UrlUtility.getContent(httpsUrl);
	}
}
