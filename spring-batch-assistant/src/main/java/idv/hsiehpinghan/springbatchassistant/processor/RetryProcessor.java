package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.vo.RetryVo;

public class RetryProcessor implements ItemProcessor<RetryVo, RetryVo> {
	private static int retryAmount = 0;

	@Override
	public RetryVo process(RetryVo item) throws Exception {
		String stringValue = item.getStringValue();
		if (stringValue.equals("6") && (retryAmount < 5)) {
			System.err.println("RetryProcessor retryAmount(" + retryAmount + ") !!!");
			++retryAmount;
			throw new RuntimeException();
		}
		item.setStringValue(stringValue + stringValue);
		System.err.println("RetryProcessor write process item(" + item + ")");
		return item;
	}
}