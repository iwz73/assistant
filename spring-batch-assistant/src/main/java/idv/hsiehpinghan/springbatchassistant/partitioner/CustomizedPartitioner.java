package idv.hsiehpinghan.springbatchassistant.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class CustomizedPartitioner implements Partitioner {
	private final static String[] ITEMS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private String keyName;

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		int count = ITEMS.length;
		int size = (int) Math.ceil(count / (double) gridSize);
		int index = 0;
		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		for (int i = 0; i < gridSize; ++i) {
			int start = index;
			int end = ((index + size) > count) ? count : (index + size);
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.putInt("start", start);
			executionContext.putInt("end", end);
			String key = keyName + i;
			result.put(key, executionContext);
			System.err.println("CustomizedPartitioner partition put key(" + key + ") !!!");
			index = end;
		}
		return result;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

}
