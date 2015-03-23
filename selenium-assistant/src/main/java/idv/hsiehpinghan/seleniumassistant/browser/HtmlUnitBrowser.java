package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Profile("htmlUnit")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HtmlUnitBrowser extends HtmlUnitBrowserBase {
	@Autowired
	@Qualifier("htmlUnitDriverExtension")
	private HtmlUnitDriverExtension webDriver;

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

}
