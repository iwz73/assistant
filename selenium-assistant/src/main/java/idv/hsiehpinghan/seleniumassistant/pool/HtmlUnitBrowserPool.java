package idv.hsiehpinghan.seleniumassistant.pool;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.factory.HtmlUnitBrowserFactory;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HtmlUnitBrowserPool implements InitializingBean {
	private GenericObjectPool<HtmlUnitBrowser> pool;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		HtmlUnitBrowserFactory factory = applicationContext
				.getBean(HtmlUnitBrowserFactory.class);
		pool = new GenericObjectPool<HtmlUnitBrowser>(factory);
	}

	public HtmlUnitBrowser borrowObject() throws Exception {
		return pool.borrowObject();
	}

	public void returnObject(HtmlUnitBrowser browser) {
		pool.returnObject(browser);
	}

	public long getCreatedCount() {
		return pool.getCreatedCount();
	}

	public long getReturnedCount() {
		return pool.getReturnedCount();
	}
}
