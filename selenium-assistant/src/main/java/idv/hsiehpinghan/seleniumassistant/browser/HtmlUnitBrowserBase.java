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
	public boolean hasAttachment() {
		String attachment = getAttachment();
		if (attachment == null) {
			return false;
		}
		if ("".equals(attachment.trim())) {
			return false;
		}
		return true;
	}

	@Override
	public String getAttachmentFileName() {
		String str = getAttachment();
		int idxBegin = str.indexOf("\"") + 1;
		int idxEnd = str.lastIndexOf("\"");
		return str.substring(idxBegin, idxEnd);
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
