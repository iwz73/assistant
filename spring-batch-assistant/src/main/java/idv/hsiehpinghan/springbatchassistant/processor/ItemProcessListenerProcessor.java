package idv.hsiehpinghan.springbatchassistant.processor;

import java.io.IOException;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessListenerProcessor implements
		ItemProcessor<String, Integer> {

	@Override
	public Integer process(String data) throws Exception {
		if ("5".equals(data)) {
			String msg = "ItemProcessListenerProcessor exception test !!!";
			System.err.println(msg);
			throw new IOException(msg);
		}
		return Integer.valueOf(data);
	}

}