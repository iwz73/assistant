package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

public class ContextWriter implements ItemWriter<Integer> {
	private ChunkContext chunkContext;

	@BeforeChunk
	public void beforeChunk(ChunkContext chunkContext) {
		this.chunkContext = chunkContext;
	}

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		printAttribute();
		System.err.println("ContextWriter write items(" + items + ")");
	}

	private void printAttribute() {
		StepContext stepContext = chunkContext.getStepContext();
		ExecutionContext jobExecutionContext = stepContext.getStepExecution().getJobExecution().getExecutionContext();
		System.err.println("ContextWriter chunkContext : " + chunkContext.getAttribute("chunkContext")
				+ ", stepContext : " + stepContext.getAttribute("stepContext") + ", jobExecutionContext : "
				+ jobExecutionContext.getString("jobExecutionContext"));
	}
}
