package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ValidatorWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		System.err.println("ValidatorWriter write items(" + items + ")");
	}
}
