package idv.hsiehpinghan.jerseyassistant.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtility {
	public static void writeToFile(InputStream inputStream, String filePath) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(filePath));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
