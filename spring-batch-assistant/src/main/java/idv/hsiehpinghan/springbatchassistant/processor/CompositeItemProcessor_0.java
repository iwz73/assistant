package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.vo.CompositeItemProcessorVo;

public class CompositeItemProcessor_0 implements ItemProcessor<CompositeItemProcessorVo, CompositeItemProcessorVo> {

	@Override
	public CompositeItemProcessorVo process(CompositeItemProcessorVo item) throws Exception {
		long longValue = item.getLongValue();
		item.setLongValue(longValue + longValue);
		System.err.println("CompositeItemProcessor_0 process item(" + item + ") !!!");
		return item;
	}

}