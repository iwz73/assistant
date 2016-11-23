package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class SkipWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		for (Integer item : items) {
			if (item.intValue() == 8) {
				throw new RuntimeException("SkipWriter RuntimeException : item(" + item + ") !!!");
			}
		}
		System.err.println("SkipWriter write items(" + items + ")");
	}
}
