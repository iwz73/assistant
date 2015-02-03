package idv.hsiehpinghan.seleniumassistant.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextInput extends WebElementWithBy {
	private static final String VALUE = "value";

	public TextInput(WebDriver webDriver, By by) {
		super(webDriver, by);
	}

	public void clear() {
		getSeleniumWebElement().clear();
	}

	public void inputText(CharSequence charSequence) {
		getSeleniumWebElement().sendKeys(charSequence);
	}

	public void inputText(String text) {
		getSeleniumWebElement().sendKeys(text);
	}

	public String getValue() {
		return getAttribute(VALUE);
	}
}
