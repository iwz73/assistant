package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webelement.A;
import idv.hsiehpinghan.seleniumassistant.webelement.Button;
import idv.hsiehpinghan.seleniumassistant.webelement.Div;
import idv.hsiehpinghan.seleniumassistant.webelement.Font;
import idv.hsiehpinghan.seleniumassistant.webelement.H;
import idv.hsiehpinghan.seleniumassistant.webelement.Radio;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;
import idv.hsiehpinghan.seleniumassistant.webelement.Table;
import idv.hsiehpinghan.seleniumassistant.webelement.Td;
import idv.hsiehpinghan.seleniumassistant.webelement.TextInput;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BrowserBase_bk {
	private String parentWindowHandle;

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
	 * Get text input.
	 * 
	 * @param by
	 * @return
	 */
	public TextInput getTextInput(By by) {
		return new TextInput(getWebDriver(), by);
	}

	/**
	 * Get H.(h1, h2, ...)
	 * 
	 * @param by
	 * @return
	 */
	public H getH(By by) {
		return new H(getWebDriver(), by);
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
	 * Get a.
	 * 
	 * @param by
	 * @return
	 */
	public A getA(By by) {
		return new A(getWebDriver(), by);
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

	/**
	 * Get Radio button.
	 * 
	 * @param by
	 * @return
	 */
	public Radio getRadio(By by) {
		return new Radio(getWebDriver(), by);
	}

	/**
	 * Get Td.
	 * 
	 * @param by
	 * @return
	 */
	public Td getTd(By by) {
		return new Td(getWebDriver(), by);
	}

	/**
	 * Back to pre-page.
	 */
	public void back() {
		getWebDriver().navigate().back();
	}

	/**
	 * Close all child window.
	 */
	public void closeAllChildWindow() {
		WebDriver webDriver = getWebDriver();
		parentWindowHandle = webDriver.getWindowHandle();
		Iterator<String> iter = webDriver.getWindowHandles().iterator();
		while (iter.hasNext()) {
			String windowHandle = iter.next();
			if (parentWindowHandle.equals(windowHandle) == false) {
				webDriver.switchTo().window(windowHandle);
				webDriver.close();
			}
		}
		webDriver.switchTo().window(parentWindowHandle);
	}

	/**
	 * Check if has child window.
	 * 
	 * @return
	 */
	public boolean hasChildWindow() {
		Set<String> set = getWebDriver().getWindowHandles();
		return set.size() > 1;
	}

	/**
	 * Switch to first child window.
	 */
	public void switchToFirstChildWindow() {
		WebDriver webDriver = getWebDriver();
		parentWindowHandle = webDriver.getWindowHandle();
		Iterator<String> iter = webDriver.getWindowHandles().iterator();
		while (iter.hasNext()) {
			String windowHandle = iter.next();
			if (parentWindowHandle.equals(windowHandle) == false) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	/**
	 * Switch to parent window.
	 */
	public void switchToParentWindow() {
		getWebDriver().switchTo().window(parentWindowHandle);
	}

	/**
	 * Get download page content to filePath.
	 * 
	 * @param file
	 * @throws UnsupportedEncodingException
	 */
	public abstract void download(File file)
			throws UnsupportedEncodingException;

	/**
	 * Get webDriver for test.
	 * 
	 * @return
	 */
	public abstract WebDriver getWebDriver();

	/**
	 * Get attachment of response's Content-disposition.
	 * 
	 * @return
	 */
	public abstract String getContentDisposition();

	/**
	 * Check if has attachment.
	 * 
	 * @return
	 */
	public abstract boolean hasContentDisposition();

	/**
	 * Get attachment file name.
	 * 
	 * @return
	 */
	public abstract String getAttachmentFileName();

	/**
	 * Cache current page for ajax page backward.
	 */
	public abstract void cacheCurrentPage();

	/**
	 * Restore cacheCurrentPage's page.
	 */
	public abstract void restorePage();

	/**
	 * Get content type.
	 * 
	 * @return
	 */
	public abstract String getContentType();

	/**
	 * Clean browser.
	 */
	public abstract void clean();

}
