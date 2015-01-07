package idv.hsiehpinghan.seleniumassistant.webelement;

import idv.hsiehpinghan.seleniumassistant.browser.FirefoxBrowser;
import idv.hsiehpinghan.seleniumassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TableTest {
	private Table table;

	@BeforeClass
	public void beforeClass() throws IOException {
		FirefoxBrowser browser = TestngSuitSetting.getFirefoxBrowser();
		browser.browse(TestngSuitSetting.URL_BASE + "html/selenium_index.html");
		table = browser.getTable(By.cssSelector("#tableId"));
	}

	@Test
	public void getRowAsStringList() {
		List<String> strs = table.getRowAsStringList(0);
		Assert.assertEquals(strs, getRowTexts());
	}

	@Test(dependsOnMethods={"getRowAsStringList"})
	public void clickButtonOfCell() {
		int rowInd_1 = 2;
		int colInd_1 = 2;
		table.clickButtonOfCell(rowInd_1, colInd_1);
		Assert.assertEquals("change_1",
				table.getButtonValueOrText(rowInd_1, colInd_1));

		int rowInd_2 = 2;
		int colInd_2 = 3;
		table.clickButtonOfCell(rowInd_2, colInd_2);
		Assert.assertEquals("change_2",
				table.getButtonValueOrText(rowInd_2, colInd_2));
	}

	private List<String> getRowTexts() {
		List<String> texts = new ArrayList<String>(2);
		texts.add("tr / th");
		texts.add("tr / td");
		return texts;
	}
}
