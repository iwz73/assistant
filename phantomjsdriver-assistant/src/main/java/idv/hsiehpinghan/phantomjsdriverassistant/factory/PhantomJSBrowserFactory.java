package idv.hsiehpinghan.phantomjsdriverassistant.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.phantomjsdriverassistant.browser.PhantomJSBrowser;

@Component
public class PhantomJSBrowserFactory extends BasePooledObjectFactory<PhantomJSBrowser> {

	@Override
	public PhantomJSBrowser create() throws Exception {
		return new PhantomJSBrowser();
	}

	@Override
	public PooledObject<PhantomJSBrowser> wrap(PhantomJSBrowser object) {
		return new DefaultPooledObject<PhantomJSBrowser>(object);
	}

	@Override
	public void passivateObject(PooledObject<PhantomJSBrowser> pooledObject) throws Exception {
		PhantomJSBrowser object = pooledObject.getObject();
		object.clean();
	}

}
