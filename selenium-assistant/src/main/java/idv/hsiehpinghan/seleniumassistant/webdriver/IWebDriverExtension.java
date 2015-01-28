package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.InputStream;

public interface IWebDriverExtension {
	/**
	 * Get page source as InputStream.
	 * 
	 * @return
	 */
	InputStream getPageSourceAsInputStream();
}
