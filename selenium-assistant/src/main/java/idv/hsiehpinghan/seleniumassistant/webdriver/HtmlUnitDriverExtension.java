package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.History;
import com.gargoylesoftware.htmlunit.Page;

@Component
@Profile("htmlUnit")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HtmlUnitDriverExtension extends HtmlUnitDriver {
	private static final String CONTENT_DISPOSITION = "Content-disposition";
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public HtmlUnitDriverExtension() {
		super();
	}

	public HtmlUnitDriverExtension(boolean enableJavascript) {
		super(enableJavascript);
	}

	public HtmlUnitDriverExtension(BrowserVersion version,
			boolean enableJavascript, boolean enableCookies) {
		super(version);
		setJavascriptEnabled(enableJavascript);
		getWebClient().getCookieManager().setCookiesEnabled(enableCookies);
	}

	public String getContentType() {
		return lastPage().getWebResponse().getContentType();
	}

	public void removeAllHistories() {
		History history = getCurrentWindow().getHistory();
		for(int i = 0, size = history.getLength(); i < size; ++i) {
			history.removeCurrent();
		}
	}
	
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
	 * Get content disposition.
	 * 
	 * @return
	 */
	public String getContentDisposition() {
		Page page = lastPage();
		if (page == null) {
			return null;
		}
		logger.debug("Response headers : "
				+ page.getWebResponse().getResponseHeaders());
		// ie. attachment; filename="2013-01-otc-02-C.zip"
		String contDisp = page.getWebResponse().getResponseHeaderValue(
				CONTENT_DISPOSITION);
		return contDisp;
	}

	/**
	 * Set webdrive's page.
	 * 
	 * @param page
	 */
	public void setPage(Page page) {
		getCurrentWindow().setEnclosedPage(page);
	}

	/**
	 * Get webdriver's page.
	 * 
	 * @return
	 */
	public Page getPage() {
		return lastPage();
	}

}
