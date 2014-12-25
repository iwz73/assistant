package idv.hsiehpinghan.seleniumassistant.utility;

import idv.hsiehpinghan.seleniumassistant.webelement.Select.Option;

import java.util.List;

public class CompareUtility {
	/**
	 * Compare option by value and text.
	 * @param options1
	 * @param options2
	 * @return
	 */
	public static boolean isEquals(List<Option> options1, List<Option> options2) {
		if(options1.size() != options2.size()) {
			return false;
		}
		for(int i = 0, size = options1.size(); i < size; ++i) {
			Option opt1 = options1.get(i);
			Option opt2 = options2.get(i);
			if(opt1.getValue().equals(opt2.getValue()) == false) {
				return false;
			}
			if(opt1.getText().equals(opt2.getText()) == false) {
				return false;
			}
		}
		return true;
	}
}
