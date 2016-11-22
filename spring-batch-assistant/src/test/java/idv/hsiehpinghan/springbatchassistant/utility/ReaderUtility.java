package idv.hsiehpinghan.springbatchassistant.utility;

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

	/**
	 * Convert reader content to char array.
	 * 
	 * @param reader
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public static char[] readAsCharArray(Reader reader, int length)
			throws IOException {
		char[] clobCharArray = new char[length];
		reader.read(clobCharArray);
		return clobCharArray;
	}

	/**
	 * Convert reader content to char array.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static char[] readAsCharArray(Reader reader) throws IOException {
		CharBuffer result = CharBuffer.allocate(CAPACITY);
		CharBuffer buffer = CharBuffer.allocate(CAPACITY);
		while (reader.read(buffer) >= 0) {
			result.append(buffer);
		}
		return result.array();
	}
}
