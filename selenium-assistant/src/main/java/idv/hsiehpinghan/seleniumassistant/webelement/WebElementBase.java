package idv.hsiehpinghan.seleniumassistant.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class WebElementBase {
    private WebDriver webDriver;
    private By by;

    protected WebElementBase(WebDriver webDriver, By by) {
    	super();
        this.webDriver = webDriver;
        this.by = by;
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected By getBy() {
        return by;
    }
}
