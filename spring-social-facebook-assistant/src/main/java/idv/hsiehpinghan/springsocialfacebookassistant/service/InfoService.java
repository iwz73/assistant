package idv.hsiehpinghan.springsocialfacebookassistant.service;

import idv.hsiehpinghan.springsocialfacebookassistant.utility.UrlUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

@Service
public class InfoService {
	public String getLoginInfo(String accessToken) throws IOException {
		final String httpsUrl = "https://graph.facebook.com/me?access_token="
				+ accessToken;
		return UrlUtility.getContent(httpsUrl);
	}

	public String getTokenInfo(String accessToken) throws IOException {
//		final String HTTPS_URL = "https://graph.facebook.com/oauth/access_token?client_id="
//				+ appId
//				+ "&client_secret="
//				+ appSecret
//				+ "&grant_type=fb_exchange_token&fb_exchange_token="
//				+ shortLivedToken;
//		
//		GET /debug_token?
//				  input_token={input-token}&amp;
//				  access_token={access-token}
//				  
//		URL url = new URL(HTTPS_URL);
//		HttpsURLConnection connection = (HttpsURLConnection) url
//				.openConnection();
//		int responseCode = connection.getResponseCode();
//		if (responseCode != 200) {
//			throw new RuntimeException("responseCode(" + responseCode
//					+ ") not equals to 200 !!!");
//		}
//		return getContent(connection);
		return null;
	}		  
		
}
