package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class StopAndRestartWriter_1 implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		System.err.println("StopAndRestartWriter_1 write : " + datas);
	}

}
