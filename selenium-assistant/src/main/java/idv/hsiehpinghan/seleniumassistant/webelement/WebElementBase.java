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

	protected abstract WebElement getSeleniumWebElement();

	protected WebDriver getWebDriver() {
		return webDriver;
	}
}
