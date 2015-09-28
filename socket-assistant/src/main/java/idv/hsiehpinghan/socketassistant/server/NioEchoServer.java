package idv.hsiehpinghan.socketassistant.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("nioEchoServer")
public class NioEchoServer {
	
	public NioEchoServer(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket ss = serverChannel.socket();
		ss.bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();
			while(it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				if(key.isAcceptable()) {
					System.err.println("connected.");
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					SocketChannel client = server.accept();
					client.configureBlocking(false);
					SelectionKey clientKey = client.register(selector,  SelectionKey.OP_WRITE);
				} else if(key.isWritable()) {
					SocketChannel client = (SocketChannel)key.channel();
					ByteBuffer buff = ByteBuffer.allocate(100);
					buff.put("abc".getBytes());
					buff.flip();
					client.write(buff);
					client.close();
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		NioEchoServer server = new NioEchoServer(8888);
	}
}
