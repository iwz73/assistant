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
		print();
		System.err.println("ContextWriter write items(" + items + ")");
	}

	private void print() {
		printChunkContext();
		printStepContext();
		printStepExecutionContext();
		printJobExecutionContext();
	}

	private void printChunkContext() {
		System.err.println("ContextWriter chunkContext(" + chunkContext.getAttribute("chunkContext") + ")");
	}

	private void printStepContext() {
		StepContext stepContext = chunkContext.getStepContext();
		System.err.println("ContextWriter stepContext(" + stepContext.getAttribute("stepContext") + ")");
	}

	private void printStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		System.err.println(
				"ContextWriter stepExecutionContext(" + stepExecutionContext.getString("stepExecutionContext") + ")");
	}

	private void printJobExecutionContext() {
		ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext();
		System.err.println(
				"ContextWriter jobExecutionContext(" + jobExecutionContext.getString("jobExecutionContext") + ")");
	}
}
