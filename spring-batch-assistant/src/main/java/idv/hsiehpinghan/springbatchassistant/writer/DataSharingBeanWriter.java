package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class DataSharingBeanWriter implements ItemWriter<Integer> {
	private long longValue;

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		System.err.println("DataSharingBeanWriter longValue(" + longValue + ") !!!");
		System.err.println("DataSharingBeanWriter write items(" + items + ")");
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

}
