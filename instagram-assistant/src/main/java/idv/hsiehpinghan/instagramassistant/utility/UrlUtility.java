package idv.hsiehpinghan.instagramassistant.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class UrlUtility {
	private static final String POST = "POST";

	public static String getContent(String httpUrl, Map<String, String> requestProperties, String parameter)
			throws IOException {
		URL url = new URL(httpUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod(POST);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		addRequestProperties(connection, requestProperties);
		addParameter(connection, parameter);
		return getContent(connection);
	}

	private static String getContent(HttpURLConnection connection) throws IOException {
		final int SIZE = 1024;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[SIZE];
			while (reader.read(cbuf) != -1) {
				sb.append(cbuf);
			}
			return sb.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static void addRequestProperties(HttpURLConnection connection, Map<String, String> requestProperties) {
		for (Map.Entry<String, String> ent : requestProperties.entrySet()) {
			connection.setRequestProperty(ent.getKey(), ent.getValue());
		}
	}

	private static void addParameter(HttpURLConnection connection, String parameter) throws IOException {
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			outputStream = connection.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(parameter);
			dataOutputStream.flush();
		} finally {
			if (dataOutputStream != null) {
				dataOutputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

}
