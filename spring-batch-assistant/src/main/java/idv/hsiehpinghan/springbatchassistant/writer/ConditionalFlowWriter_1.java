package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ConditionalFlowWriter_1 implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		System.err.println("ConditionalFlowWriter_1 write : " + datas);
	}

}
