package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.Page;

public class HtmlUnitDriverExtension extends HtmlUnitDriver implements
		IWebDriverExtension {
	private static final String CONTENT_DISPOSITION = "Content-disposition";

	public HtmlUnitDriverExtension() {
	}

	public HtmlUnitDriverExtension(boolean enableJavascript) {
		super(enableJavascript);
	}

	/**
	 * Get file name.
	 * 
	 * @return
	 */
	public String getFileName() {
		Page page = lastPage();
		if (page == null) {
			return null;
		}
		// ie. attachment; filename="2013-01-otc-02-C.zip"
		String contDisp = page.getWebResponse().getResponseHeaderValue(
				CONTENT_DISPOSITION);
		return getFileName(contDisp);
	}

	/**
	 * Get page source as inputStream.
	 */
	@Override
	public InputStream getPageSourceAsInputStream() {
		Page page = lastPage();
		if (page == null) {
			return null;
		}

		InputStream is = null;
		try {
			is = page.getWebResponse().getContentAsStream();
		} catch (IOException e) {
			IOUtils.closeQuietly(is);
			is = null;
		}
		return is;
	}
	
	/**
	 * Set webdrive's page.
	 * @param page
	 */
	public void setPage(Page page) {
		getCurrentWindow().setEnclosedPage(page);
	}
	
	/**
	 * Get webdriver's page.
	 * @return
	 */
	public Page getPage() {
		return lastPage();
	}

	
	String getFileName(String str) {
		int idxBegin = str.indexOf("\"") + 1;
		int idxEnd = str.lastIndexOf("\"");
		return str.substring(idxBegin, idxEnd);
	}


}
