package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomizedPartitionerWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		long threadId = Thread.currentThread().getId();
		System.err.println("threadId(" + threadId + ") CustomizedPartitionerWriter write items(" + items + ")");
	}
}
