package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.gargoylesoftware.htmlunit.Page;

//@Component
//@Profile("htmlUnit")
public abstract class  HtmlUnitBrowserBase extends BrowserBase {
	private Page page;
//	@Autowired
//	private HtmlUnitDriverExtension webDriver;

	@Override
	public File download(String filePath) {
		InputStream is = getWebDriver().getPageSourceAsInputStream();
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
		return getWebDriver().getFileName();
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
