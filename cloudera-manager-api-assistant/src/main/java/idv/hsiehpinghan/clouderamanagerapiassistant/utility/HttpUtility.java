package idv.hsiehpinghan.clouderamanagerapiassistant.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtility {
	private final static int TEN_SECONDS = 10 * 1000;

	public static HttpURLConnection getConnection(String httpUrl) throws IOException {
		return getConnection(httpUrl, null);
	}

	public static HttpURLConnection getConnection(String httpUrl, Map<String, String> requestProperties)
			throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(TEN_SECONDS);
		connection.setReadTimeout(TEN_SECONDS);
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		if (requestProperties != null) {
			for (Map.Entry<String, String> ent : requestProperties.entrySet()) {
				connection.setRequestProperty(ent.getKey(), ent.getValue());
			}
		}
		return connection;
	}
}
