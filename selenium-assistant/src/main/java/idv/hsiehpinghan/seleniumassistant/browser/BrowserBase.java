package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webelement.Button;
import idv.hsiehpinghan.seleniumassistant.webelement.Div;
import idv.hsiehpinghan.seleniumassistant.webelement.Font;
import idv.hsiehpinghan.seleniumassistant.webelement.Radio;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;
import idv.hsiehpinghan.seleniumassistant.webelement.Table;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BrowserBase {

	/**
	 * Go to target url.
	 * 
	 * @param url
	 */
	public void browse(String url) {
		getWebDriver().get(url);
	}

	/**
	 * Get select.
	 * 
	 * @param by
	 * @return
	 */
	public Select getSelect(By by) {
		return new Select(getWebDriver(), by);
	}

	/**
	 * Get table.
	 * 
	 * @param by
	 * @return
	 */
	public Table getTable(By by) {
		return new Table(getWebDriver(), by);
	}

	/**
	 * Get div.
	 * 
	 * @param by
	 * @return
	 */
	public Div getDiv(By by) {
		return new Div(getWebDriver(), by);
	}

	/**
	 * Get font.
	 * 
	 * @param by
	 * @return
	 */
	public Font getFont(By by) {
		return new Font(getWebDriver(), by);
	}

	/**
	 * Get button.
	 * 
	 * @param by
	 * @return
	 */
	public Button getButton(By by) {
		return new Button(getWebDriver(), by);
	}

	public Radio getRadio(By by) {
		return new Radio(getWebDriver(), by);
	}
	
	/**
	 * Back to pre-page.
	 */
	public void back() {
		getWebDriver().navigate().back();
	}

	/**
	 * Get download page content to filePath.
	 * 
	 * @param filePath
	 * @return
	 */
	public abstract File download(String filePath);

	/**
	 * Get webDriver for test.
	 * 
	 * @return
	 */
	public abstract WebDriver getWebDriver();

	/**
	 * Get file name by response's Content-disposition.
	 * 
	 * @return
	 */
	public abstract String getDownloadFileName();

	/**
	 * Cache current page for ajax page backward.
	 */
	public abstract void cacheCurrentPage();

	/**
	 * Restore cacheCurrentPage's page.
	 */
	public abstract void restorePage();
}
