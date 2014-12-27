package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.Page;

@Component
@Profile("htmlUnit")
public class HtmlUnitDriverExtension extends HtmlUnitDriver implements
		IWebDriverExtension {
	private static final String CONTENT_DISPOSITION = "Content-disposition";
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public HtmlUnitDriverExtension() {
		super(true);
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
		logger.debug(page.getWebResponse().getResponseHeaders());
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
