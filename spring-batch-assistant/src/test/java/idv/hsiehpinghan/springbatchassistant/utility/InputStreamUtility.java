package idv.hsiehpinghan.springbatchassistant.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class InputStreamUtility {

	/**
	 * Convert inputStream content to string.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String readAsString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}

	/**
	 * Convert inputStream content to byte array.
	 * 
	 * @param inputStream
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public static byte[] readAsByteArray(InputStream inputStream, int length) throws IOException {
		byte[] byteArray = new byte[length];
		inputStream.read(byteArray);
		return byteArray;
	}

	/**
	 * Write inputStream to file.
	 * 
	 * @param inputStream
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeToFile(InputStream inputStream, String filePath) throws IOException {
		try (OutputStream out = new FileOutputStream(new File(filePath));) {
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		}
	}
}
