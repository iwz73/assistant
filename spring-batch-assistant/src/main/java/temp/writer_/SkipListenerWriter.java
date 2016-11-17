package temp.writer_;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class SkipListenerWriter implements ItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		for (Integer integer : datas) {
			if (integer.intValue() == 8) {
				String msg = "SkipListenerWriter exception test !!!";
				System.err.println(msg);
				throw new IOException(msg);
			}
		}
		System.err.println("SkipListenerWriter write : " + datas);
	}

}
