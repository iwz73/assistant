package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Profile("firefox")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FirefoxDriverExtension extends FirefoxDriver implements
		IWebDriverExtension {

	public FirefoxDriverExtension() {
	}

	@Override
	public InputStream getPageSourceAsInputStream() {
		Response resp = execute(DriverCommand.GET_PAGE_SOURCE);
		byte[] bs = resp.getValue().toString().getBytes();
		return new ByteArrayInputStream(bs);
	}
}
