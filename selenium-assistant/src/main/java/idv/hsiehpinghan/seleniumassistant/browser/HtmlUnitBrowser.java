package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;

@Component
@Profile("htmlUnit")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HtmlUnitBrowser extends BrowserBase implements InitializingBean {
	private final BrowserVersion VERSION;
	private final boolean ENABLE_JAVASCRIPT;
	private final boolean ENABLE_COOKIES;
	private Page page;
	private HtmlUnitDriverExtension webDriver;

	@Autowired
	private ApplicationContext applicationContext;

	public HtmlUnitBrowser() {
		this(BrowserVersion.FIREFOX_24, true, false);
	}

	public HtmlUnitBrowser(BrowserVersion version, boolean enableJavascript,
			boolean enableCookies) {
		this.VERSION = version;
		this.ENABLE_JAVASCRIPT = enableJavascript;
		this.ENABLE_COOKIES = enableCookies;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		webDriver = applicationContext.getBean(HtmlUnitDriverExtension.class,
				VERSION, ENABLE_JAVASCRIPT, ENABLE_COOKIES);
	}

	@Override
	public void download(File file) {
		InputStream is = webDriver.getPageSourceAsInputStream();
		try {
			FileUtils.copyInputStreamToFile(is, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getContentType() {
		return webDriver.getContentType();
	}

	@Override
	public String getContentDisposition() {
		return webDriver.getContentDisposition();
	}

	@Override
	public boolean hasContentDisposition() {
		String contentDisposition = getContentDisposition();
		if (contentDisposition == null) {
			return false;
		}
		if ("".equals(contentDisposition.trim())) {
			return false;
		}
		return true;
	}

	@Override
	public String getAttachmentFileName() {
		String str = getContentDisposition();
		int idxBegin = str.indexOf("\"") + 1;
		int idxEnd = str.lastIndexOf("\"");
		return str.substring(idxBegin, idxEnd);
	}

	@Override
	public void cacheCurrentPage() {
		this.page = webDriver.getPage();
	}

	@Override
	public void restorePage() {
		if (page != null) {
			webDriver.setPage(page);
			page = null;
		}
	}

	@Override
	public HtmlUnitDriverExtension getWebDriver() {
		return webDriver;
	}

	@Override
	public void clean() {
		browse("about:blank");
		closeAllChildWindow();
		webDriver.manage().deleteAllCookies();
	}

}
