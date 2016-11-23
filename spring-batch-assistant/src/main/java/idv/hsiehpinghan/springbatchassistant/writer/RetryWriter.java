package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import idv.hsiehpinghan.springbatchassistant.vo.RetryVo;

public class RetryWriter implements ItemWriter<RetryVo> {

	@Override
	public void write(List<? extends RetryVo> items) throws Exception {
		System.err.println("RetryWriter write items(" + items + ")");
	}

}
