package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.vo.CompositeItemProcessorVo;

public class CompositeItemProcessor_1 implements ItemProcessor<CompositeItemProcessorVo, CompositeItemProcessorVo> {

	@Override
	public CompositeItemProcessorVo process(CompositeItemProcessorVo item) throws Exception {
		String stringValue = item.getStringValue();
		item.setStringValue(stringValue + stringValue);
		System.err.println("CompositeItemProcessor_1 process item(" + item + ") !!!");
		return item;
	}

}