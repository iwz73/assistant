package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.property.BrowserProperty;
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

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BrowserBase {

	/**
	 * Get title.
	 * 
	 * @return
	 */
	public String getTitle() {
		return getWebDriver().getTitle();
	}

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
		String parentWindowHandle = webDriver.getWindowHandle();
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
		String parentWindowHandle = webDriver.getWindowHandle();
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
	 * Clean browser.
	 */
	public void clean() {
		browse("about:blank");
		closeAllChildWindow();
		getWebDriver().manage().deleteAllCookies();
	}

	/**
	 * set browser property.
	 * 
	 * @param property
	 */
	public abstract void setBrowserProperty(BrowserProperty property);

	/**
	 * Get webDriver for test.
	 * 
	 * @return
	 */
	public abstract WebDriver getWebDriver();
	
	/**
	 * Generate capabilities.
	 * 
	 * @param properties
	 * @return
	 */
	protected Capabilities generateCapabilities(Properties properties) {
		String supportsJavascript = properties
				.getProperty(CapabilityType.SUPPORTS_JAVASCRIPT);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,
				Boolean.valueOf(supportsJavascript));
		return capabilities;
	}

}
