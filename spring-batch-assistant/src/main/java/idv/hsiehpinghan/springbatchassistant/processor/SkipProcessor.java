package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

public class SkipProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String item) throws Exception {
		if (item.equals("6")) {
			throw new RuntimeException("SkipProcessor RuntimeException : item(" + item + ") !!!");
		}
		System.err.println("SkipProcessor process item(" + item + ")");
		return Integer.valueOf(item);
	}

}