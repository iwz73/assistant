package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class LinearFlowWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		System.err.println("LinearFlow write items(" + items + ")");
	}
}
