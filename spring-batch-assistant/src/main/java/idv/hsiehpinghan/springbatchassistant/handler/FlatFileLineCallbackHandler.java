package idv.hsiehpinghan.springbatchassistant.handler;

import org.springframework.batch.item.file.LineCallbackHandler;

public class FlatFileLineCallbackHandler implements LineCallbackHandler {

	@Override
	public void handleLine(String line) {
		System.err.println("FlatFileLineCallbackHandler handleLine(" + line + ") !!!");
	}

}
