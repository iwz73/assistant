package idv.hsiehpinghan.springbatchassistant.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

@Component
public class ItemWriteListenerListener implements ItemWriteListener<String> {

	@Override
	public void beforeWrite(List<? extends String> strings) {
		System.err.println("ItemWriteListenerListener beforeWrite : strings("
				+ strings + ")");
	}

	@Override
	public void afterWrite(List<? extends String> strings) {
		System.err.println("ItemWriteListenerListener afterWrite : strings("
				+ strings + ")");
	}

	@Override
	public void onWriteError(Exception exception, List<? extends String> strings) {
		System.err.println("ItemWriteListenerListener onWriteError : strings("
				+ strings + "), exception(" + exception + ")");
	}

}
