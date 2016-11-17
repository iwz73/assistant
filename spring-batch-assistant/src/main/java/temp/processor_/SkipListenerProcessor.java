package temp.processor_;

import java.io.IOException;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SkipListenerProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String data) throws Exception {
		if ("5".equals(data)) {
			String msg = "SkipListenerProcessor exception test !!!";
			System.err.println(msg);
			throw new IOException(msg);
		}
		return Integer.valueOf(data);
	}

}