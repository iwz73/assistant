package idv.hsiehpinghan.nanohttpdassistant.server;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.kevoree.library.nanohttp.NanoHTTPD;
import org.springframework.stereotype.Component;

@Component
public class HttpServer extends NanoHTTPD {
	private static final int DEFAULT_PORT = 8080;

	public HttpServer() throws IOException {
		super(DEFAULT_PORT);
	}

	public Response serveHtml(File htmlFile) throws IOException {
		String html = FileUtils.readFileToString(htmlFile);
		return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_HTML, html);
	}
}
