package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

public class PromotionWriter implements ItemWriter<Integer> {
	private ChunkContext chunkContext;

	@BeforeChunk
	public void beforeChunk(ChunkContext chunkContext) {
		this.chunkContext = chunkContext;
	}

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		printStepExecutionContext();
		System.err.println("PromotionWriter write items(" + items + ")");
	}

	private void printStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		System.err.println(
				"PromotionWriter stepExecutionContext(" + stepExecutionContext.getString("data_0") + ")");
	}

}
