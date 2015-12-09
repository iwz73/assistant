package idv.hsiehpinghan.springsocialfacebookassistant.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class UrlUtility {
	public static String getContent(String httpsUrl) throws IOException {
		HttpsURLConnection connection = getHttpsURLConnection(httpsUrl);
		return getContent(connection);
	}

	private static HttpsURLConnection getHttpsURLConnection(String httpsUrl)
			throws IOException {
		URL url = new URL(httpsUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url
				.openConnection();
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("responseCode(" + responseCode
					+ ") not equals to 200 !!!");
		}
		return connection;
	}

	private static String getContent(HttpsURLConnection connection)
			throws IOException {
		final int SIZE = 64;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		char[] cbuf = new char[SIZE];
		while (reader.read(cbuf) != -1) {
			sb.append(cbuf);
			Arrays.fill(cbuf, '\0');
		}
		reader.close();
		return sb.toString();
	}
}
