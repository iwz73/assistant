package idv.hsiehpinghan.phantomjsdriverassistant.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.phantomjsdriverassistant.browser.FirefoxBrowser;

@Component
public class FirefoxBrowserFactory extends BasePooledObjectFactory<FirefoxBrowser> {

	@Override
	public FirefoxBrowser create() throws Exception {
		return new FirefoxBrowser();
	}

	@Override
	public PooledObject<FirefoxBrowser> wrap(FirefoxBrowser object) {
		return new DefaultPooledObject<FirefoxBrowser>(object);
	}

	@Override
	public void passivateObject(PooledObject<FirefoxBrowser> pooledObject) throws Exception {
		FirefoxBrowser object = pooledObject.getObject();
		object.clean();
	}

}
