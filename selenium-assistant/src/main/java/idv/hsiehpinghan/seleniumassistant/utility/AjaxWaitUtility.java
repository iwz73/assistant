package idv.hsiehpinghan.seleniumassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.VoidUtility;
import idv.hsiehpinghan.seleniumassistant.browser.HtmlUnitBrowserBase;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;
import idv.hsiehpinghan.seleniumassistant.webelement.Table;
import idv.hsiehpinghan.seleniumassistant.webelement.TextInput;
import idv.hsiehpinghan.seleniumassistant.webelement.WebElementBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class AjaxWaitUtility {
	private static final int POLLING_MILLISECONDS = 1000;
	private static final int TIMEOUT_MILLISECONDS = 10000;
	private static Logger logger = Logger.getLogger(AjaxWaitUtility.class
			.getName());

	/**
	 * Wait until select's options differ from comparedOption.
	 * 
	 * @param select
	 * @param comparedOption
	 * @return
	 */
	public static boolean waitUntilOptionsDifferent(final Select select,
			final List<Option> comparedOption) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					List<Option> options = select.getOptions();
					return CompareUtility.isEquals(options, comparedOption) == false;
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait until row text equals comparedList.
	 * 
	 * @param table
	 * @param rowIndex
	 * @param comparedList
	 * @return
	 */
	public static boolean waitUntilRowTextEqual(final Table table,
			final int rowIndex, final List<String> comparedList) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					List<String> txts = table.getRowAsStringList(rowIndex);
					return ListUtils.isEqualList(txts, comparedList);
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait webElement font text equal.
	 * 
	 * @param webElement
	 * @param text
	 * @return
	 */
	public static boolean waitUntilTextEqual(final WebElementBase webElement,
			final String text) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return text.equals(webElement.getText());
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait webElement font text different.
	 * 
	 * @param webElement
	 * @param text
	 * @return
	 */
	public static boolean waitUntilTextDifferent(
			final WebElementBase webElement, final String text) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return text.equals(webElement.getText()) == false;
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait until text input equal.
	 * 
	 * @param TextInput
	 * @param value
	 * @return
	 */
	public static boolean waitUntilValueEqual(final TextInput TextInput,
			final String value) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return value.equals(TextInput.getValue());
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait until text start with text.
	 * 
	 * @param webElement
	 * @param text
	 * @return
	 */
	public static boolean waitUntilTextStartWith(
			final WebElementBase webElement, final String text) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return webElement.getText().startsWith(text);
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	/**
	 * Wait until webElement displayed.
	 * 
	 * @param webElement
	 * @return
	 */
	public static boolean waitUntilDisplayed(final WebElementBase webElement) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return webElement.isDisplayed();
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	public static boolean waitUntilFirstChildWindowAttachmentNotNull(
			final HtmlUnitBrowserBase browser) {
		return wait(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					browser.switchToFirstChildWindow();
					return browser.getAttachment() != null;
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				} finally {
					browser.switchToParentWindow();
				}
			}
		});
	}

	private static boolean wait(final Function<Void, Boolean> function) {
		FluentWait<Void> fluentWait = new FluentWait<Void>(VoidUtility.VOID);
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(function);
	}
}
