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

	/**
	 * Get file name by response's Content-disposition.
	 * 
	 * @return
	 */
	public String getDownloadFileName() {
		return webDriver.getFileName();
	}

	/**
	 * Cache current page for ajax page backward.
	 */
	public void cacheCurrentPage() {
		this.page = webDriver.getPage();
	}

	/**
	 * Restore cacheCurrentPage's page.
	 */
	public void restorePage() {
		webDriver.setPage(page);
	}

	@Override
	protected WebDriver getWebDriver() {
		return webDriver;
	}

	// public HtmlUnitDriverExtension testGet() {
	// return webDriver;
	// }
	//
	// public void testSet(HtmlUnitDriverExtension wd) {
	// webDriver = wd;
	// }
}
