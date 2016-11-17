package temp.writer_;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class JobStepWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> datas) throws Exception {
		System.err.println("JobStepWriter write : " + datas);
	}

}
