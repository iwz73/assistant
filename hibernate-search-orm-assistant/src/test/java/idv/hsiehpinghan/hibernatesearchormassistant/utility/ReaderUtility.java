package idv.hsiehpinghan.hibernatesearchormassistant.utility;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ReaderUtility {
	private static final int CAPACITY = 1024;

	/**
	 * Convert reader content to string.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static String readAsString(Reader reader) throws IOException {
		CharBuffer buffer = CharBuffer.allocate(CAPACITY);
		StringBuilder sb = new StringBuilder();
		while (reader.read(buffer) >= 0) {
			sb.append(buffer.flip());
		}
		return sb.toString();
	}

}