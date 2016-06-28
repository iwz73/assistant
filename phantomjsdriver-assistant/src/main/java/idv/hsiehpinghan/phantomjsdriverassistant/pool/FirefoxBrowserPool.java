package idv.hsiehpinghan.phantomjsdriverassistant.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.phantomjsdriverassistant.browser.FirefoxBrowser;
import idv.hsiehpinghan.phantomjsdriverassistant.factory.FirefoxBrowserFactory;
import idv.hsiehpinghan.phantomjsdriverassistant.property.BrowserProperty;

@Component
public class FirefoxBrowserPool implements InitializingBean {
	private BrowserProperty browserProperty = new BrowserProperty(true, null);
	private GenericObjectPool<FirefoxBrowser> pool;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		FirefoxBrowserFactory factory = applicationContext.getBean(FirefoxBrowserFactory.class);
		pool = new GenericObjectPool<FirefoxBrowser>(factory);
	}

	public FirefoxBrowser borrowObject() throws Exception {
		FirefoxBrowser browser = pool.borrowObject();
		browser.setBrowserProperty(browserProperty);
		return browser;
	}

	public void returnObject(FirefoxBrowser browser) {
		pool.returnObject(browser);
	}

	public long getCreatedCount() {
		return pool.getCreatedCount();
	}

	public long getReturnedCount() {
		return pool.getReturnedCount();
	}
}
