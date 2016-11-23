package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import idv.hsiehpinghan.springbatchassistant.vo.CompositeItemProcessorVo;

public class CompositeItemProcessorWriter implements ItemWriter<CompositeItemProcessorVo> {

	@Override
	public void write(List<? extends CompositeItemProcessorVo> vos) throws Exception {
		System.err.println("CompositeItemProcessorWriter write items(" + vos + ")");
	}
}
