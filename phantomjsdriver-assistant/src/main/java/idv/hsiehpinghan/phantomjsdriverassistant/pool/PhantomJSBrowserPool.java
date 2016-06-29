package idv.hsiehpinghan.phantomjsdriverassistant.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.phantomjsdriverassistant.browser.PhantomJSBrowser;
import idv.hsiehpinghan.phantomjsdriverassistant.factory.PhantomJSBrowserFactory;

@Component
public class PhantomJSBrowserPool implements InitializingBean {
	private GenericObjectPool<PhantomJSBrowser> pool;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		PhantomJSBrowserFactory factory = applicationContext.getBean(PhantomJSBrowserFactory.class);
		pool = new GenericObjectPool<PhantomJSBrowser>(factory);
	}

	public PhantomJSBrowser borrowObject() throws Exception {
		PhantomJSBrowser browser = pool.borrowObject();
		return browser;
	}

	public void returnObject(PhantomJSBrowser browser) {
		pool.returnObject(browser);
	}

	public long getCreatedCount() {
		return pool.getCreatedCount();
	}

	public long getReturnedCount() {
		return pool.getReturnedCount();
	}
}
