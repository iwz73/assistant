package idv.hsiehpinghan.hdfsassistant.utility;

import java.io.File;
import java.net.URL;

public class ResourceUtility {
	public static File getFileResource(String filePath) {
		URL url = ClassLoader.getSystemResource(filePath);
		return new File(url.getPath());
	}
}
