package idv.hsiehpinghan.springbatchassistant.callback;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileFooterCallback;

public class FlatFileFlatFileFooterCallback implements FlatFileFooterCallback {

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write("FlatFileFlatFileFooterCallback !!!");
	}

}
