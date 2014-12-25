package idv.hsiehpinghan.nanohttpdassistant.server;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.kevoree.library.nanohttp.NanoHTTPD;
import org.springframework.stereotype.Component;

@Component
public class MockHtmlServer extends NanoHTTPD {
	private static final int DEFAULT_PORT = 8080;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public MockHtmlServer() throws IOException {
		super(DEFAULT_PORT);
	}

	/**
	 * Serve request.
	 */
	@Override
	public Response serve(String uri, String method, Properties header,
			Properties parms, Properties files, InputStream body) {
		logger.debug("uri : " + uri);
		logger.debug("method : " + method);
		logger.debug("header : " + header);
		logger.debug("parms : " + parms);
		logger.debug("files : " + files);
		String html = null;
		try {
			html = FileUtils.readFileToString(getMockFile(uri));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_HTML,
				html);
	}

	private File getMockFile(String uri) {
		String filePath;
		if (uri.startsWith("/")) {
			filePath = uri.substring(1);
		} else {
			filePath = uri;
		}
		return ResourceUtility.getFileResource(filePath);
	}
}
