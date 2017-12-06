package idv.hsiehpinghan.seleniumchromedriverassistant.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverUtility {
	public static void setAllElementAttributeValue(ChromeDriver chromeDriver, String attributeName) {
		WebElement webElement = chromeDriver.findElement(By.tagName("body"));
		String attributePrefix = "";
		setChildElementAttributeValue(chromeDriver, webElement, attributeName, attributePrefix);
	}

	private static void setChildElementAttributeValue(ChromeDriver chromeDriver, WebElement webElement,
			String attributeName, String attributePrefix) {
		List<WebElement> webElements = webElement.findElements(By.xpath("*"));
		for (int i = 0, size = webElements.size(); i < size; ++i) {
			WebElement webEle = webElements.get(i);
			String newAttributePrefix = String.format("%s_%d", attributePrefix, i);
			chromeDriver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", webEle, attributeName,
					newAttributePrefix);
			setChildElementAttributeValue(chromeDriver, webEle, attributeName, newAttributePrefix);
		}
	}
}
