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
		addAttribute();
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index++];
		System.err.println("ContextReader read item(" + item + ")");
		return item;
	}

	private void addAttribute() {
		chunkContext.setAttribute("chunkContext", "chunkContext");
		StepContext stepContext = chunkContext.getStepContext();
		stepContext.setAttribute("stepContext", "stepContext");
		ExecutionContext jobExecutionContext = stepContext.getStepExecution().getJobExecution().getExecutionContext();
		jobExecutionContext.putString("jobExecutionContext", "jobExecutionContext");
	}
}
