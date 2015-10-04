package idv.hsiehpinghan.seleniumassistant.browser;

import idv.hsiehpinghan.seleniumassistant.webelement.Select;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;
import idv.hsiehpinghan.seleniumassistant.webelement.Table;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public abstract class BrowserTest {
	private static final String URL_BASE = "http://127.0.0.1:8080/";

	@Test
	public void browse() {
		getBrowser().browse(URL_BASE + "html/selenium_index.html");
		Assert.assertEquals("Index page", getBrowser().getTitle());
	}

	@Test(dependsOnMethods = { "browse" })
	public void getSelect() {
		Select select = getBrowser().getSelect(By.cssSelector("#selectId"));
		Assert.assertNotNull(select);
	}

	@Test(dependsOnMethods = { "getSelect" })
	public void getOptions() {
		Select select = getBrowser().getSelect(By.cssSelector("#selectId"));
		List<Option> options = select.getOptions();
		Assert.assertEquals(3, options.size());
	}

	@Test(dependsOnMethods = { "getOptions" })
	public void getRowAsStringList() {
		Table table = getBrowser().getTable(By.cssSelector("#tableId"));
		List<String> strs = table.getRowAsStringList(0);
		Assert.assertEquals(strs, getRowTexts());
	}

	@Test(dependsOnMethods = { "getRowAsStringList" })
	public void clickButtonOfCell() {
		Table table = getBrowser().getTable(By.cssSelector("#tableId"));
		int rowInd_1 = 2;
		int colInd_1 = 2;
		table.clickButtonOfCell(rowInd_1, colInd_1);
		Assert.assertEquals(table.getButtonValueOrText(rowInd_1, colInd_1),
				"change_1");

		int rowInd_2 = 2;
		int colInd_2 = 3;
		table.clickButtonOfCell(rowInd_2, colInd_2);
		Assert.assertEquals(table.getButtonValueOrText(rowInd_2, colInd_2),
				"change_2");
	}

	public abstract BrowserBase getBrowser();

	private List<String> getRowTexts() {
		List<String> texts = new ArrayList<String>(2);
		texts.add("tr / th");
		texts.add("tr / td");
		return texts;
	}

}
