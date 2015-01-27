package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webdriver.HtmlUnitDriverExtension;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.gargoylesoftware.htmlunit.Page;

//@Component
//@Profile("htmlUnit")
public abstract class HtmlUnitBrowserBase extends BrowserBase {
	private Page page;

	// @Autowired
	// private HtmlUnitDriverExtension webDriver;

	@Override
	public File download(String filePath) {
		InputStream is = getWebDriver().getPageSourceAsInputStream();
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "big5"));
			String str;
	        while ((str = in.readLine()) != null) {
	        	System.err.println(str);
	        }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
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
