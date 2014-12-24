package idv.hsiehpinghan.seleniumassistant.webelement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select extends WebElementBase {
    public Select(WebDriver webDriver, By by) {
    	super(webDriver, by);
    }
    
    public List<Option> getOptions() {
        List<WebElement> options = getSeleniumSelect().getOptions();
        int size = options.size();
        List<Option> result = new ArrayList<Option>(size);
        for(int i = 0; i < size; ++i) {
            Option option = new Option(this, i);
            result.add(option);
        }
        return result;
    }
    
    private org.openqa.selenium.support.ui.Select getSeleniumSelect() {
        WebDriver webDriver = getWebDriver();
        By by = getBy();
        WebElement webElement = webDriver.findElement(by);
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(webElement);
        return select;
    }

    public class Option {
    	private Select select;
    	private int index;

    	public Option(Select select, int index) {
    		this.select = select;
    		this.index = index;
    	}
    }
}
