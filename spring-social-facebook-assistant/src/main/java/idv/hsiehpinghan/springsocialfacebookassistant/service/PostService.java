package idv.hsiehpinghan.springsocialfacebookassistant.service;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

@Service
public class PostService {
	private final String POST = "POST";

	public String post(String userId, String appId, String accessToken, String message) throws IOException {
		final String HTTPS_URL = String.format(
				"https://graph.facebook.com/%s/feed?app_id=%s&access_token=%s&message=%s", userId, appId, accessToken,
				message);
		URL url = new URL(null, HTTPS_URL, new sun.net.www.protocol.https.Handler());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod(POST);
		connection.setDoOutput(true);
		return UrlUtility.getContent(connection);
	}
}
