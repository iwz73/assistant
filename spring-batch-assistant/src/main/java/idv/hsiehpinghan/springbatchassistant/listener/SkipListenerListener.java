package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class SkipListenerListener implements SkipListener<String, Integer> {

	@Override
	public void onSkipInProcess(String string, Throwable throwable) {
		System.err.println("SkipListenerListener onSkipInProcess string("
				+ string + "), throwable(" + throwable + ")");
	}

	@Override
	public void onSkipInRead(Throwable throwable) {
		System.err.println("SkipListenerListener onSkipInProcess throwable("
				+ throwable + ")");
	}

	@Override
	public void onSkipInWrite(Integer integer, Throwable throwable) {
		System.err.println("SkipListenerListener onSkipInProcess integer("
				+ integer + "), throwable(" + throwable + ")");
	}

}
