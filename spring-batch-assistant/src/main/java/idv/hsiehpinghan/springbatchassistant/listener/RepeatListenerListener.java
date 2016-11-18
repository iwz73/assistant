package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatListener;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class RepeatListenerListener implements RepeatListener {
	@Override
	public void open(RepeatContext repeatContext) {
		System.err.println("RepeatListenerListener open!!!");
	}

	@Override
	public void before(RepeatContext repeatContext) {
		System.err.println("RepeatListenerListener before!!!");
	}

	@Override
	public void onError(RepeatContext repeatContext, Throwable throwable) {
		System.err.println("RepeatListenerListener onError!!!");
	}

	@Override
	public void after(RepeatContext repeatContext, RepeatStatus repeatStatus) {
		System.err.println("RepeatListenerListener after!!!");
	}

	@Override
	public void close(RepeatContext repeatContext) {
		System.err.println("RepeatListenerListener close!!!");
	}

}
