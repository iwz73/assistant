package idv.hsiehpinghan.hibernatesearchormassistant.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamUtility {

	/**
	 * Convert inputStream content to string.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String readAsString(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		}
		return sb.toString();
	}

}