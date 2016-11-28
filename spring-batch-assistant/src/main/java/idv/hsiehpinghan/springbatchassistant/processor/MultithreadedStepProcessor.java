package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

public class MultithreadedStepProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String item) throws Exception {
		long threadId = Thread.currentThread().getId();
		System.err.println("threadId(" + threadId + ") MultithreadedStepProcessor process item(" + item + ")");
		return Integer.valueOf(item);
	}

}