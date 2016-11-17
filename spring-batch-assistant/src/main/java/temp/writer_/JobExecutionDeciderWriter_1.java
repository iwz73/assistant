package temp.writer_;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionDeciderWriter_1 implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		System.err.println("JobExecutionDeciderWriter_1 write : " + datas);
	}

}
