package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BasicProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String data) throws Exception {
		return Integer.valueOf(data);
	}

}