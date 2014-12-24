package idv.hsiehpinghan.seleniumassistant.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebElementBase {
    private WebDriver webDriver;
    private By by;

    protected WebElementBase(WebDriver webDriver, By by) {
    	super();
        this.webDriver = webDriver;
        this.by = by;
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
    
    protected WebElement getSeleniumWebElement() {
        return webDriver.findElement(by);
    }
    
    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected By getBy() {
        return by;
    }
}
