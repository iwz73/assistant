package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class RetryListenerListener implements RetryListener {
	@Override
	public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
		System.err.println("RetryListenerListener open!!!");
		return true;
	}

	@Override
	public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback,
			Throwable throwable) {
		System.err.println("RetryListenerListener onError!!!");
	}

	@Override
	public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback,
			Throwable throwable) {
		System.err.println("RetryListenerListener close!!!");
	}

}
