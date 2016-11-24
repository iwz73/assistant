package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

public class RestartProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String item) throws Exception {
		System.err.println("RestartProcessor process item(" + item + ")");
		return Integer.valueOf(item);
	}

}