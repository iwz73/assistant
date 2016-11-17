package temp.writer_;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class RetryWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		System.err.println("RetryWriter write : " + datas);
	}

}
