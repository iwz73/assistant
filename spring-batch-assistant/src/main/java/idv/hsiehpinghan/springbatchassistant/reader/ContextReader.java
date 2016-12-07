package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ContextReader implements ItemReader<String> {
	private final static String[] ITEMS = new String[] { "0" };
	private static int index = 0;
	private ChunkContext chunkContext;

	@BeforeChunk
	public void beforeChunk(ChunkContext chunkContext) {
		this.chunkContext = chunkContext;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		add();
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index++];
		System.err.println("ContextReader read item(" + item + ")");
		return item;
	}

	private void add() {
		addChunkContext();
		addStepContext();
		addStepExecutionContext();
		addJobExecutionContext();
	}

	private void addChunkContext() {
		chunkContext.setAttribute("chunkContext", "chunkContext");
	}

	private void addStepContext() {
		StepContext stepContext = chunkContext.getStepContext();
		stepContext.setAttribute("stepContext", "stepContext");
	}

	private void addStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		stepExecutionContext.putString("stepExecutionContext", "stepExecutionContext");
	}

	private void addJobExecutionContext() {
		ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext();
		jobExecutionContext.putString("jobExecutionContext", "jobExecutionContext");
	}
}
