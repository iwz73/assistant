package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import idv.hsiehpinghan.springbatchassistant.vo.FlatFileVo;

public class MultiResourcePartitionerWriter implements ItemWriter<FlatFileVo> {

	@Override
	public void write(List<? extends FlatFileVo> vos) throws Exception {
		long threadId = Thread.currentThread().getId();
		System.err.println("threadId(" + threadId + ") MultiResourcePartitionerWriter write vos(" + vos + ")");
	}

}
