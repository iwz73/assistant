package idv.hsiehpinghan.springsocialfacebookassistant.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

@Service
public class InfoService {
	public String getLoginInfo(String accessToken) throws IOException {
		final String HTTPS_URL = "https://graph.facebook.com/me?access_token="
				+ accessToken;
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
