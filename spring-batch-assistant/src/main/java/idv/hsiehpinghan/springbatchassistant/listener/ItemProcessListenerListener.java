package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessListenerListener implements
		ItemProcessListener<String, Integer> {

	@Override
	public void beforeProcess(String string) {
		System.err
				.println("ItemProcessListenerListener beforeProcess : string("
						+ string + ")");
	}

	@Override
	public void afterProcess(String string, Integer integer) {
		System.err.println("ItemProcessListenerListener afterProcess : string("
				+ string + "), integer(" + integer + ")");
	}

	@Override
	public void onProcessError(String string, Exception exception) {
		System.err
				.println("ItemProcessListenerListener beforeProcess : string("
						+ string + "), exception(" + exception + ")");
	}

}
