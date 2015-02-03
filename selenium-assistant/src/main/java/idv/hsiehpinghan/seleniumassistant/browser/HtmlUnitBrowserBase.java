package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.gargoylesoftware.htmlunit.Page;

public abstract class HtmlUnitBrowserBase extends BrowserBase {
	private Page page;

	@Override
	public void download(File file) {
		InputStream is = getWebDriver().getPageSourceAsInputStream();
		try {
			FileUtils.copyInputStreamToFile(is, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getContentType() {
		return getWebDriver().getContentType();
	}

	@Override
	public String getAttachment() {
		return getWebDriver().getAttachment();
	}

	@Override
	public void cacheCurrentPage() {
		this.page = getWebDriver().getPage();
	}

	@Override
	public void restorePage() {
		getWebDriver().setPage(page);
	}

	@Override
	public abstract HtmlUnitDriverExtension getWebDriver();

}
