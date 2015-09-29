package idv.hsiehpinghan.nanohttpdassistant.server;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import fi.iki.elonen.NanoHTTPD;

@Component
public class MockHtmlServer extends NanoHTTPD {
	private static final int DEFAULT_PORT = 8080;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public MockHtmlServer() throws IOException {
		super(DEFAULT_PORT);
		start();
	}

	/**
	 * Serve request.
	 */
	@Override
	public Response serve(IHTTPSession session) {
		logger.debug("uri : " + session.getUri());
		logger.debug("method : " + session.getMethod());
		logger.debug("queryParameterString : "
				+ session.getQueryParameterString());
		logger.debug("headers : " + session.getHeaders());
		logger.debug("parms : " + session.getParms());
		logger.debug("cookies : " + session.getCookies());
		logger.debug("inputStream : " + session.getInputStream());
		InputStream mockFile = null;
		try {
			mockFile = getMockFile(session.getUri());
			return new NanoHTTPD.Response(NanoHTTPD.Response.Status.OK,
					NanoHTTPD.MIME_HTML, mockFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private InputStream getMockFile(String uri) throws IOException {
		String filePath;
		if (uri.startsWith("/")) {
			filePath = uri.substring(1);
		} else {
			filePath = uri;
		}
		return ResourceUtility.getResourceAsStream(filePath);
	}
}
