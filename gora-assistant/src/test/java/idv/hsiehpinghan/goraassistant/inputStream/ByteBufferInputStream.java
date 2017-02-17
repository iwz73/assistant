package idv.hsiehpinghan.goraassistant.inputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {
	private ByteBuffer byteBuffer;

	public ByteBufferInputStream(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	@Override
	public int read() throws IOException {
		if (byteBuffer.hasRemaining() == false) {
			return -1;
		}
		return byteBuffer.get() & 0xFF;
	}

	@Override
	public int read(byte[] bytes, int off, int len) throws IOException {
		if (byteBuffer.hasRemaining() == false) {
			return -1;
		}
		len = Math.min(len, byteBuffer.remaining());
		byteBuffer.get(bytes, off, len);
		return len;
	}
}
