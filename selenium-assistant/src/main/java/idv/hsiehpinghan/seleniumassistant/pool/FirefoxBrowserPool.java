package idv.hsiehpinghan.seleniumassistant.pool;

import idv.hsiehpinghan.seleniumassistant.browser.FirefoxBrowser;
import idv.hsiehpinghan.seleniumassistant.factory.FirefoxBrowserFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FirefoxBrowserPool implements InitializingBean {
	private BrowserProperty browserProperty = new BrowserProperty(true, null);
	private GenericObjectPool<FirefoxBrowser> pool;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		FirefoxBrowserFactory factory = applicationContext
				.getBean(FirefoxBrowserFactory.class);
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
