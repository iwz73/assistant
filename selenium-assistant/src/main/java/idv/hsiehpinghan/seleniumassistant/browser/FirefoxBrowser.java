package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.FirefoxDriverExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("firefox")
public class FirefoxBrowser extends BrowserBase {
	@Autowired
	private FirefoxDriverExtension webDriver;

	@Override
	public void download(File file) {
		InputStream is = webDriver.getPageSourceAsInputStream();
		try {
			FileUtils.copyInputStreamToFile(is, file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public String getAttachment() {
		throw new RuntimeException("Not implement !!!");
	}

	@Override
	public boolean hasAttachment() {
		throw new RuntimeException("Not implement !!!");
	}

	@Override
	public String getAttachmentFileName() {
		throw new RuntimeException("Not implement !!!");
	}

	@Override
	public void cacheCurrentPage() {
		throw new RuntimeException("Not implement !!!");
	}

	@Override
	public void restorePage() {
		throw new RuntimeException("Not implement !!!");
	}

}
