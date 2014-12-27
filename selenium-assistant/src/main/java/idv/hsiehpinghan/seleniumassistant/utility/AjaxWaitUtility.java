package idv.hsiehpinghan.seleniumassistant.utility;

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
	private static Logger logger = Logger.getLogger(AjaxWaitUtility.class.getName());
	/**
	 * Wait until select's options differ from comparedOption.
	 * 
	 * @param select
	 * @param comparedOption
	 * @return
	 */
	public static boolean waitUntilOptionsDifferent(final Select select,
			final List<Option> comparedOption) {
		// Object parameter is not used.
		FluentWait<Object> fluentWait = new FluentWait<Object>(new Object());
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Object, Boolean>() {
			@Override
			public Boolean apply(Object obj) {
				try {
					List<Option> options = select.getOptions();
					return CompareUtility.isEquals(options, comparedOption) == false;
				} catch (Exception e) {
					logger.debug("Exception : ", e);
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
		// Object parameter is not used.
		FluentWait<Object> fluentWait = new FluentWait<Object>(new Object());
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Object, Boolean>() {
			@Override
			public Boolean apply(Object obj) {
				try {
					List<String> txts = table.getRowAsStringList(rowIndex);
					return ListUtils.isEqualList(txts, comparedList);
				} catch (Exception e) {
					logger.debug("Exception : ", e);
					return false;
				}
			}
		});
	}
	
	
	public static boolean waitUntilFontTextEqual(final Font font,
			final String text) {
		// Object parameter is not used.
		FluentWait<Object> fluentWait = new FluentWait<Object>(new Object());
		fluentWait.pollingEvery(POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
		return fluentWait.until(new Function<Object, Boolean>() {
			@Override
			public Boolean apply(Object obj) {
				try {
					return text.equals(font.getText());
				} catch (Exception e) {
					logger.debug("Exception : ", e);
					return false;
				}
			}
		});
	}
}
