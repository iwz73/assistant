package idv.hsiehpinghan.socketassistant.assistant;

import idv.hsiehpinghan.socketassistant.enumeration.HttpMethod;
import idv.hsiehpinghan.socketassistant.enumeration.Protocol;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class SocketAssistant_bk {

	public String getContent(Protocol protocol, String host, int port,
			String path, HttpMethod httpMethod) throws IOException {
		Socket socket = null;
		OutputStream outputStream = null;
		int socketPort = getSocketPost(protocol, port);
		try {
			socket = generateConnectedSocket(host, socketPort);
			outputStream = new BufferedOutputStream(socket.getOutputStream());
			byte[] requestString = generateRequestString(httpMethod, host,
					socketPort, path);
			outputStream.write(requestString);
			outputStream.flush();
			
			String str = convertToString(socket.getInputStream());
			
			System.err.println(str);
			
			return str;
		} finally {
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(socket);
		}
	}

	private String convertToString(InputStream inputStream) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, Charsets.UTF_8);
		return writer.toString();
	}

	private int getSocketPost(Protocol protocol, int port) {
		if (port == -1) {
			if (Protocol.HTTP == protocol) {
				return 80;
			}
			if (Protocol.HTTPS == protocol) {
				return 443;
			}
			throw new RuntimeException("Protocol(" + protocol
					+ ") not implements !!!");
		}
		return port;
	}

	private byte[] generateRequestString(HttpMethod httpMethod, String host,
			int socketPort, String path) {
		StringBuffer reqStr = new StringBuffer();
		reqStr.append(httpMethod.name()).append(" ").append(path).append(" ")
				.append("HTTP/1.0\r\n");
		reqStr.append("Host: ").append(host).append(":").append(socketPort)
				.append("\r\n");
		
//		
//		reqStr.append("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0").append("\r\n");
//		reqStr.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append("\r\n");
//		reqStr.append("Accept-Language: en-US,en;q=0.5").append("\r\n");
//		reqStr.append("Accept-Encoding: gzip, deflate").append("\r\n");
//		reqStr.append("Referer: http://mops.twse.com.tw/mops/web/index").append("\r\n");
//		reqStr.append("Cookie: jcsession=jHttpSession@b804e6; __utma=193825960.1568423537.1449110234.1449130470.1449194994.4; __utmz=193825960.1449194994.4.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); _ga=GA1.3.1568423537.1449110234; newmops2=selfObj%3DtagCon1%7CTYPEK%3Dpub%7Ccode%3D98%7C").append("\r\n");
//		reqStr.append("Connection: keep-alive").append("\r\n");
//		reqStr.append("Pragma: no-cache").append("\r\n");
//		reqStr.append("Cache-Control: no-cache").append("\r\n");


//		reqStr.append("GET /mops/web/t51sb01 HTTP/1.1").append("\r\n");
//		reqStr.append("Host: mops.twse.com.tw").append("\r\n");
//		reqStr.append("Connection: keep-alive").append("\r\n");
//		reqStr.append("Cache-Control: max-age=0").append("\r\n");
//		reqStr.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").append("\r\n");
//		reqStr.append("Upgrade-Insecure-Requests: 1").append("\r\n");
//		reqStr.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36").append("\r\n");
//		reqStr.append("Accept-Encoding: gzip, deflate, sdch").append("\r\n");
//		reqStr.append("Accept-Language: zh-TW,zh;q=0.8,en-US;q=0.6,en;q=0.4").append("\r\n");
		
		
//		reqStr.append("GET /mops/web/t51sb01 HTTP/1.1").append("\r\n");
//		reqStr.append("Host: mops.twse.com.tw").append("\r\n");
//		reqStr.append("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0").append("\r\n");
//		reqStr.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append("\r\n");
//		reqStr.append("Accept-Language: en-US,en;q=0.5").append("\r\n");
//		reqStr.append("Accept-Encoding: gzip, deflate").append("\r\n");
//		reqStr.append("Connection: keep-alive").append("\r\n");
		
//		reqStr.append("GET /mops/web/t51sb01 HTTP/1.0").append("\r\n");
//		reqStr.append("Host: mops.twse.com.tw").append("\r\n");
//		reqStr.append("Accept-Encoding: x-gzip, gzip").append("\r\n");
//		reqStr.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append("\r\n");
//		reqStr.append("User-Agent: My Nutch Spider/Nutch-2.3").append("\r\n");
//		reqStr.append("If-Modified-Since: Thu, 01 Jan 1970 00:00:00 GMT").append("\r\n");



//		reqStr.append("Cookie: jcsession=jHttpSession@169360f; __utma=193825960.749465396.1446522280.1449482655.1449499850.4; __utmz=193825960.1449499850.4.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); _ga=GA1.3.749465396.1446522280; _gat=1; newmops2=selfObj%3DtagCon1%7Cco_id%3D2412%7Cyear%3D89%7Cmonth%3D%7Cb_date%3D%7Ce_date%3D%7CTYPEK%3Dpub%7Ccode%3D98%7C").append("\r\n");
		
		System.err.println(reqStr.toString());

		return reqStr.toString().getBytes();
	}

	private Socket generateConnectedSocket(String host, int socketPort)
			throws IOException {
		InetSocketAddress socketAddress = new InetSocketAddress(host,
				socketPort);
		Socket socket = new Socket();
		
		socket.setSoTimeout(10000);
		
		socket.connect(socketAddress);
		return socket;
	}
}
