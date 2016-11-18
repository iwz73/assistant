package idv.hsiehpinghan.springbatchassistant.callback;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class FlatFileFlatFileHeaderCallback implements FlatFileHeaderCallback {

	@Override
	public void writeHeader(Writer writer) throws IOException {
		writer.write("FlatFileFlatFileHeaderCallback !!!");
	}

}
