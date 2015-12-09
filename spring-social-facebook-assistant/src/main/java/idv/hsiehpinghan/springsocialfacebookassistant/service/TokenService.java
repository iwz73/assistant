package idv.hsiehpinghan.springsocialfacebookassistant.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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

	public String getLongLivedToken(String shortLivedToken) throws IOException {
		final String HTTPS_URL = "https://graph.facebook.com/oauth/access_token?client_id="
				+ appId
				+ "&client_secret="
				+ appSecret
				+ "&grant_type=fb_exchange_token&fb_exchange_token="
				+ shortLivedToken;
		URL url = new URL(HTTPS_URL);
		HttpsURLConnection connection = (HttpsURLConnection) url
				.openConnection();
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("responseCode(" + responseCode
					+ ") not equals to 200 !!!");
		}
		return getContent(connection);
	}

	private String getContent(HttpsURLConnection connection) throws IOException {
		final int SIZE = 64;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		char[] cbuf = new char[SIZE];
		while (reader.read(cbuf) != -1) {
			sb.append(cbuf);
		}
		reader.close();
		return sb.toString();
	}
}
