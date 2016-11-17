package temp.writer_;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ItemWriteListenerWriter implements ItemWriter<String> {
	@Override
	public void write(List<? extends String> datas) throws Exception {
		for (int i = 0, size = datas.size(); i < size; ++i) {
			if (datas.get(i).equals("5")) {
				String msg = "ItemWriteListenerWriter exception test !!!";
				System.err.println(msg);
				throw new Exception(msg);
			}
		}
		System.err.println("ItemWriteListenerWriter write : " + datas);
	}

}
