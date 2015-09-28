package idv.hsiehpinghan.socketassistant.server;

import idv.hsiehpinghan.socketassistant.property.SocketAssistantProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("echoServer")
public class EchoServer implements InitializingBean, Runnable {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SocketAssistantProperty socketAssistantProperty;

	@Override
	public void afterPropertiesSet() throws IOException {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(
				socketAssistantProperty.getEchoServerPort())) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					EchoThread thread = new EchoThread(socket);
					thread.run();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private class EchoThread implements Runnable {
		private PrintWriter out;
		private BufferedReader in;

		public EchoThread(Socket socket) throws IOException {
			OutputStream rawOut = socket.getOutputStream();
			InputStream rawIn = socket.getInputStream();
			out = new PrintWriter(rawOut);
			in = new BufferedReader(new InputStreamReader(rawIn));
		}

		@Override
		public void run() {
			String data;
			try {
				data = in.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.err.println(this + "recv : " + data);
			out.println(data);
			out.flush();
			System.err.println(this + "send : " + data);
			// in.close();
			// out.close();
		}

	}

	// public static void main(String[] args) throws IOException {
	// EchoServer server = new EchoServer(8888);
	// }
}
