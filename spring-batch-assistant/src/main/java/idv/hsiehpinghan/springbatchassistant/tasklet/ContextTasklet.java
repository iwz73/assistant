package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ContextTasklet implements Tasklet {
	private ChunkContext chunkContext;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		this.chunkContext = chunkContext;
		System.err.println("ContextTasklet !!!");
		print();
		return RepeatStatus.FINISHED;
	}

	private void print() {
		printChunkContext();
		printStepContext();
		printStepExecutionContext();
		printJobExecutionContext();
	}

	private void printChunkContext() {
		System.err.println("ContextTasklet chunkContext(" + chunkContext.getAttribute("chunkContext") + ")");
	}

	private void printStepContext() {
		StepContext stepContext = chunkContext.getStepContext();
		System.err.println("ContextTasklet stepContext(" + stepContext.getAttribute("stepContext") + ")");
	}

	private void printStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		System.err.println("ContextTasklet stepExecutionContext("
				+ stepExecutionContext.getString("stepExecutionContext", null) + ")");
	}

	private void printJobExecutionContext() {
		ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext();
		System.err.println("ContextTasklet jobExecutionContext("
				+ jobExecutionContext.getString("jobExecutionContext", null) + ")");
	}
}
