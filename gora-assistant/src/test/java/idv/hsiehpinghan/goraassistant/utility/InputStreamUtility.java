package idv.hsiehpinghan.goraassistant.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import idv.hsiehpinghan.goraassistant.inputStream.ByteBufferInputStream;

public class InputStreamUtility {

	public static String readAsString(ByteBuffer byteBuffer) throws IOException {
		InputStream inputStream = convertToInputStream(byteBuffer);
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

	private static InputStream convertToInputStream(ByteBuffer byteBuffer) {
		return new ByteBufferInputStream(byteBuffer);
	}
}