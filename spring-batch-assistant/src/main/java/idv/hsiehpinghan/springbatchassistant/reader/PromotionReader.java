package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class PromotionReader implements ItemReader<String> {
	private final static String[] ITEMS = new String[] { "0" };
	private static int index = 0;
	private ChunkContext chunkContext;

	@BeforeChunk
	public void beforeChunk(ChunkContext chunkContext) {
		this.chunkContext = chunkContext;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		addStepExecutionContext();
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index++];
		System.err.println("PromotionReader read item(" + item + ")");
		return item;
	}

	private void addStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		stepExecutionContext.putString("data_0", "data_0");
		stepExecutionContext.putString("data_1", "data_1");
	}

}
