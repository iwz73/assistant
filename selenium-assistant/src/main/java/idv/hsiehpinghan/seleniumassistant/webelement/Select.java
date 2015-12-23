package idv.hsiehpinghan.seleniumassistant.webelement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select extends WebElementWithBy {
	public Select(WebDriver webDriver, By by) {
		super(webDriver, by);
	}

	/**
	 * Select by index.
	 * 
	 * @param index
	 */
	public void selectByIndex(int index) {
		getSeleniumSelect().selectByIndex(index);
	}

	/**
	 * Select by value.
	 * 
	 * @param value
	 */
	public void selectByValue(String value) {
		getSeleniumSelect().selectByValue(value);
	}

	/**
	 * Select by text.
	 * 
	 * @param text
	 */
	public void selectByText(String text) {
		getSeleniumSelect().selectByVisibleText(text);
	}

	/**
	 * Get selected text.
	 * 
	 * @return
	 */
	public String getSelectedText() {
		for (Option opt : getOptions()) {
			if (opt.isSelected()) {
				return opt.getText();
			}
		}
		return null;
	}

	/**
	 * Get selected value.
	 * 
	 * @return
	 */
	public String getSelectedValue() {
		for (Option opt : getOptions()) {
			if (opt.isSelected()) {
				return opt.getValue();
			}
		}
		return null;
	}

	/**
	 * Get content options.
	 * 
	 * @return
	 */
	public List<Option> getOptions() {
		int size = getSeleniumSelect().getOptions().size();
		List<Option> result = new ArrayList<Option>(size);
		for (int i = 0; i < size; ++i) {
			Option option = new Option(this, i);
			result.add(option);
		}
		return result;
	}

	private org.openqa.selenium.support.ui.Select getSeleniumSelect() {
		return new org.openqa.selenium.support.ui.Select(
				getSeleniumWebElement());
	}

	public class Option extends WebElementBase {
		private static final String VALUE = "value";
		private Select select;
		private int index;

		public Option(Select select, int index) {
			super(select.getWebDriver());
			this.select = select;
			this.index = index;
		}

		/**
		 * Get value attribute.
		 * 
		 * @return
		 */
		public String getValue() {
			return getAttribute(VALUE);
		}

		/**
		 * Check if selected.
		 * 
		 * @return
		 */
		public boolean isSelected() {
			return getSeleniumWebElement().isSelected();
		}

		@Override
		protected WebElement getSeleniumWebElement() {
			org.openqa.selenium.support.ui.Select sel = select
					.getSeleniumSelect();
			WebElement option = sel.getOptions().get(index);
			return option;
		}

	}
}
