package idv.hsiehpinghan.seleniumassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.VoidUtility;
import idv.hsiehpinghan.seleniumassistant.webelement.Div;
import idv.hsiehpinghan.seleniumassistant.webelement.Font;
import idv.hsiehpinghan.seleniumassistant.webelement.Select;
import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;
import idv.hsiehpinghan.seleniumassistant.webelement.Table;

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
		FluentWait<Void> fluentWait = new FluentWait<Void>(VoidUtility.VOID);
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Void, Boolean>() {
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
		FluentWait<Void> fluentWait = new FluentWait<Void>(VoidUtility.VOID);
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Void, Boolean>() {
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
	 * Wait until font text equal.
	 * 
	 * @param font
	 * @param text
	 * @return
	 */
	public static boolean waitUntilFontTextEqual(final Font font,
			final String text) {
		FluentWait<Void> fluentWait = new FluentWait<Void>(VoidUtility.VOID);
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return text.equals(font.getText());
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}

	public static boolean waitUntilDivTextStartWith(final Div div,
			final String text) {
		FluentWait<Void> fluentWait = new FluentWait<Void>(VoidUtility.VOID);
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Void, Boolean>() {
			@Override
			public Boolean apply(Void v) {
				try {
					return div.getText().startsWith(text);
				} catch (Exception e) {
					logger.trace("Exception : ", e);
					return false;
				}
			}
		});
	}
}
