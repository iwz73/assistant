package idv.hsiehpinghan.springsocialfacebookassistant.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UrlUtility {
	public static String getContent(String httpsUrl) throws IOException {
		HttpsURLConnection connection = getHttpsURLConnection(httpsUrl);
		return getContent(connection);
	}

	private static HttpsURLConnection getHttpsURLConnection(String httpsUrl) throws IOException {
		URL url = new URL(httpsUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("responseCode(" + responseCode + ") not equals to 200 !!!");
		}
		return connection;
	}

	public static String getContent(HttpsURLConnection connection) throws IOException {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
