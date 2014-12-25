package idv.hsiehpinghan.seleniumassistant.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebElementWithBy extends WebElementBase {
    private By by;

    protected WebElementWithBy(WebDriver webDriver, By by) {
    	super(webDriver);
        this.by = by;
    }

    protected WebElement getSeleniumWebElement() {
    	return getWebDriver().findElement(by);
    }
    
    protected By getBy() {
        return by;
    }
}
