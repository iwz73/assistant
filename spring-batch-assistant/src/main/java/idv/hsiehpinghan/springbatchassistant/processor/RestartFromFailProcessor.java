package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

public class RestartFromFailProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String item) throws Exception {
		System.err.println("RestartFromFailProcessor process item(" + item + ")");
		return Integer.valueOf(item);
	}

}