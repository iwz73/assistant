package idv.hsiehpinghan.seleniumchromedriverassistant.utility;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import idv.hsiehpinghan.seleniumchromedriverassistant.enumeration.CssPropertyEnumeration;

public class ChromeDriverUtility {
	public static void setAllElementAttributeValue(ChromeDriver chromeDriver, String attributeName) {
		WebElement webElement = chromeDriver.findElement(By.tagName("body"));
		String attributePrefix = "";
		setChildElementAttributeValue(chromeDriver, webElement, attributeName, attributePrefix);
	}

	public static Map<String, String> getElementAllAttribute(WebElement webElement) {
		Map<String, String> map = new LinkedHashMap<>();
		for (CssPropertyEnumeration cssPropertyEnumeration : CssPropertyEnumeration.values()) {
			String attributeName = cssPropertyEnumeration.getName();
			String attributeValue = webElement.getCssValue(attributeName);
			map.put(attributeName, attributeValue);
		}
		return map;
	}

	public static Map<String, Long> getBoundingClientRect(ChromeDriver chromeDriver, WebElement webElement) {
		@SuppressWarnings("unchecked")
		Map<String, Long> boundingClientRect = (Map<String, Long>) chromeDriver
				.executeScript("return arguments[0].getBoundingClientRect();", webElement);
		return boundingClientRect;
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
