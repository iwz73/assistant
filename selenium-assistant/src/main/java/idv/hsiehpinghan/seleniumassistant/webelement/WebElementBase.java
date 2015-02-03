package idv.hsiehpinghan.seleniumassistant.webelement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebElementBase {
	private WebDriver webDriver;

	protected WebElementBase(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	public String getText() {
		return getSeleniumWebElement().getText();
	}

	public void click() {
		getSeleniumWebElement().click();
	}

	public String getAttribute(String attributeName) {
		return getSeleniumWebElement().getAttribute(attributeName);
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public boolean isDisplayed() {
		return getSeleniumWebElement().isDisplayed();
	}

	protected abstract WebElement getSeleniumWebElement();
}
