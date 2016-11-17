package temp.listener_;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class ItemReadListenerListener implements ItemReadListener<String> {

	@Override
	public void afterRead(String string) {
		System.err.println("ItemReadListenerListener afterRead : string("
				+ string + ")");
	}

	@Override
	public void beforeRead() {
		System.err.println("ItemReadListenerListener beforeRead.");
	}

	@Override
	public void onReadError(Exception exception) {
		System.err.println("ItemReadListenerListener onReadError : exception("
				+ exception + ")");
	}

}
