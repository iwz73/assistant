package idv.hsiehpinghan.seleniumassistant.pool;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;
import idv.hsiehpinghan.seleniumassistant.factory.HtmlUnitBrowserFactory;
import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HtmlUnitBrowserPool implements InitializingBean {
	private BrowserProperty browserProperty = new BrowserProperty(true, null);
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
		HtmlUnitBrowser browser = pool.borrowObject();
		browser.setBrowserProperty(browserProperty);
		return browser;
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
