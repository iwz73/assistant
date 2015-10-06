package idv.hsiehpinghan.seleniumassistant.factory;

import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowser;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

@Component
public class HtmlUnitBrowserFactory extends
		BasePooledObjectFactory<HtmlUnitBrowser> {

	@Override
	public HtmlUnitBrowser create() throws Exception {
		return new HtmlUnitBrowser();
	}

	@Override
	public PooledObject<HtmlUnitBrowser> wrap(HtmlUnitBrowser object) {
		return new DefaultPooledObject<HtmlUnitBrowser>(object);
	}

	@Override
	public void passivateObject(PooledObject<HtmlUnitBrowser> pooledObject)
			throws Exception {
		HtmlUnitBrowser object = pooledObject.getObject();
		object.clean();
	}

}
