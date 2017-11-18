package idv.hsiehpinghan.seleniumchromedriverassistant.listener;

import org.apache.commons.pool2.SwallowedExceptionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverPoolSwallowedExceptionListener implements SwallowedExceptionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChromeDriverPoolSwallowedExceptionListener.class);

	@Override
	public void onSwallowException(Exception e) {
		LOGGER.error("onSwallowException !!!", e);
	}

}
