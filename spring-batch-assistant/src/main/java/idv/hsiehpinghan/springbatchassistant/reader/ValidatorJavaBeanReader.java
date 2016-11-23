package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import idv.hsiehpinghan.springbatchassistant.vo.ValidatorJavaBeanVo;

public class ValidatorJavaBeanReader implements ItemReader<ValidatorJavaBeanVo> {
	private final static String[] ITEMS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static int index = 0;

	@Override
	public ValidatorJavaBeanVo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index++];
		ValidatorJavaBeanVo vo = new ValidatorJavaBeanVo();
		vo.setLongValue(Long.valueOf(item));
		vo.setStringValue(item);
		System.err.println("ValidatorJavaBeanReader read vo(" + vo + ")");
		return vo;
	}
}
