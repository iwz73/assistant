package idv.hsiehpinghan.socketassistant.assistant;

import idv.hsiehpinghan.socketassistant.enumeration.HttpMethod;
import idv.hsiehpinghan.socketassistant.enumeration.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class SocketAssistant {
	private final int TIMEOUT = 10 * 1000;
	private final HashSet<String> TLS_PROTOCOLS = new HashSet<String>(
			Arrays.asList(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
	private final HashSet<String> TLS_CIPHERS = new HashSet<String>(
			Arrays.asList(new String[] {
					"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
					"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
					"TLS_RSA_WITH_AES_256_CBC_SHA256",
					"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
					"TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
					"TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
					"TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
					"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
					"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
					"TLS_RSA_WITH_AES_256_CBC_SHA",
					"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
					"TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
					"TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
					"TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
					"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
					"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
					"TLS_RSA_WITH_AES_128_CBC_SHA256",
					"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
					"TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
					"TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
					"TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
					"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
					"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
					"TLS_RSA_WITH_AES_128_CBC_SHA",
					"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
					"TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
					"TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
					"TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
					"TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
					"TLS_ECDHE_RSA_WITH_RC4_128_SHA",
					"SSL_RSA_WITH_RC4_128_SHA",
					"TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
					"TLS_ECDH_RSA_WITH_RC4_128_SHA",
					"TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
					"TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
					"SSL_RSA_WITH_3DES_EDE_CBC_SHA",
					"TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
					"TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
					"SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
					"SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
					"SSL_RSA_WITH_RC4_128_MD5",
					"TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
					"TLS_RSA_WITH_NULL_SHA256",
					"TLS_ECDHE_ECDSA_WITH_NULL_SHA",
					"TLS_ECDHE_RSA_WITH_NULL_SHA", "SSL_RSA_WITH_NULL_SHA",
					"TLS_ECDH_ECDSA_WITH_NULL_SHA",
					"TLS_ECDH_RSA_WITH_NULL_SHA", "SSL_RSA_WITH_NULL_MD5",
					"SSL_RSA_WITH_DES_CBC_SHA", "SSL_DHE_RSA_WITH_DES_CBC_SHA",
					"SSL_DHE_DSS_WITH_DES_CBC_SHA",
					"TLS_KRB5_WITH_RC4_128_SHA", "TLS_KRB5_WITH_RC4_128_MD5",
					"TLS_KRB5_WITH_3DES_EDE_CBC_SHA",
					"TLS_KRB5_WITH_3DES_EDE_CBC_MD5",
					"TLS_KRB5_WITH_DES_CBC_SHA", "TLS_KRB5_WITH_DES_CBC_MD5" }));

	public String getContent(String urlString, Map<String, String> headerMap)
			throws IOException {
		URL url = new URL(urlString);
		Protocol protocol = getProtocol(url);
		String host = url.getHost();
		int port = getPort(url, protocol);
		String path = "".equals(url.getFile()) ? "/" : url.getFile();
		return getContent(protocol, host, port, path, headerMap);
	}

	public String getContent(Protocol protocol, String host, int port,
			String path, Map<String, String> headerMap) throws IOException {
		return getContent(HttpMethod.GET, protocol, host, port, path,
				headerMap, null);
	}

	public String getContent(Protocol protocol, String host, int port,
			String path, Map<String, String> headerMap,
			Map<String, String> criteriaMap) throws IOException {
		return getContent(HttpMethod.POST, protocol, host, port, path,
				headerMap, criteriaMap);
	}

	private String getContent(HttpMethod httpMethod, Protocol protocol,
			String host, int port, String path, Map<String, String> headerMap,
			Map<String, String> criteriaMap) throws IOException {
		Socket socket = null;
		InputStream inputStream = null;
		try {
			socket = generateSocket(httpMethod, protocol, host, port, path,
					headerMap, criteriaMap);
			inputStream = socket.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, Charsets.UTF_8);
			return writer.toString();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (socket != null) {
				socket.close();
			}
		}
	}

	@SuppressWarnings("resource")
	private Socket generateSocket(HttpMethod httpMethod, Protocol protocol,
			String host, int port, String path, Map<String, String> headerMap,
			Map<String, String> criteriaMap) throws IOException {
		Socket socket = null;
		socket = new Socket();
		socket.setSoTimeout(TIMEOUT);
		InetSocketAddress socketAddress = new InetSocketAddress(host, port);
		socket.connect(socketAddress, TIMEOUT);
		if (Protocol.HTTPS == protocol) {
			socket = generateSSLSocket(socket, host, port);
		}
		OutputStream socketOutputStream = socket.getOutputStream();
		byte[] request = generateRequest(httpMethod, host, port, path,
				headerMap, criteriaMap);
		socketOutputStream.write(request);
		socketOutputStream.flush();
		return socket;

	}

	private byte[] generateRequest(HttpMethod httpMethod, String host,
			int port, String path, Map<String, String> headerMap,
			Map<String, String> criteriaMap) {
		StringBuffer request = new StringBuffer();
		request.append(httpMethod).append(" ").append(path).append(" ")
				.append("HTTP/1.0").append("\r\n");
		request.append("Host:").append(" ").append(host).append(":")
				.append(port).append("\r\n");
		for (Map.Entry<String, String> ent : headerMap.entrySet()) {
			request.append(ent.getKey() + ": " + ent.getValue() + "\r\n");
		}
		request.append("\r\n");
		if (criteriaMap != null) {
			addRequestBody(request, criteriaMap);
		}
		return request.toString().getBytes();
	}

	private void addRequestBody(StringBuffer request,
			Map<String, String> criteriaMap) {
		int i = 0;
		for (Map.Entry<String, String> ent : criteriaMap.entrySet()) {
			if (i != 0) {
				request.append("&");
			}
			request.append(ent.getKey()).append("=").append(ent.getValue());
			++i;
		}
	}

	private SSLSocket generateSSLSocket(Socket socket, String host, int port)
			throws IOException {
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslSocket = (SSLSocket) factory.createSocket(socket, host,
				port, true);
		sslSocket.setUseClientMode(true);
		Set<String> protocols = new HashSet<String>(Arrays.asList(sslSocket
				.getSupportedProtocols()));
		Set<String> ciphers = new HashSet<String>(Arrays.asList(sslSocket
				.getSupportedCipherSuites()));
		protocols.retainAll(TLS_PROTOCOLS);
		ciphers.retainAll(TLS_CIPHERS);
		sslSocket.setEnabledProtocols(protocols.toArray(new String[protocols
				.size()]));
		sslSocket.setEnabledCipherSuites(ciphers.toArray(new String[ciphers
				.size()]));
		sslSocket.startHandshake();
		return sslSocket;
	}

	private int getPort(URL url, Protocol protocol) {
		int port = url.getPort();
		if (url.getPort() == -1) {
			switch (protocol) {
			case HTTP:
				return 80;
			case HTTPS:
				return 443;
			default:
				throw new RuntimeException("Protocol(" + protocol
						+ ") not implements !!!");
			}
		}
		return port;
	}

	private Protocol getProtocol(URL url) {
		String protocol = url.getProtocol();
		switch (protocol) {
		case "http":
			return Protocol.HTTP;
		case "https":
			return Protocol.HTTPS;
		default:
			throw new RuntimeException("Protocol(" + protocol
					+ ") not implements !!!");
		}
	}
}
