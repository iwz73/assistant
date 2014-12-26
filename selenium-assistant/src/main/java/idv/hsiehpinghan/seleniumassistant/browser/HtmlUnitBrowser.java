package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.Page;

@Component
public class HtmlUnitBrowser extends BrowserBase {
	private Page page;
	@Autowired
	private HtmlUnitDriverExtension webDriver;

	@Override
	public File download(String filePath) {
		InputStream is = webDriver.getPageSourceAsInputStream();
		File f = new File(filePath);
		try {
			FileUtils.copyInputStreamToFile(is, f);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return f;
	}

	@Override
	public String getDownloadFileName() {
		return webDriver.getFileName();
	}

	@Override
	public void cacheCurrentPage() {
		this.page = webDriver.getPage();
	}

	@Override
	public void restorePage() {
		webDriver.setPage(page);
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
