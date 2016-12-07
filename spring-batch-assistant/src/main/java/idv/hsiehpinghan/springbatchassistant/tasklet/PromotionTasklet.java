package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class PromotionTasklet implements Tasklet {
	private ChunkContext chunkContext;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		this.chunkContext = chunkContext;
		System.err.println("PromotionTasklet !!!");
		print();
		return RepeatStatus.FINISHED;
	}

	private void print() {
		printStepExecutionContext();
		printJobExecutionContext();
	}

	private void printStepExecutionContext() {
		ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		System.err.println(
				"PromotionTasklet stepExecutionContext(" + stepExecutionContext.getString("data_0", null) + ")");
		System.err.println(
				"PromotionTasklet stepExecutionContext(" + stepExecutionContext.getString("data_1", null) + ")");
	}

	private void printJobExecutionContext() {
		ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext();
		System.err.println("PromotionTasklet jobExecutionContext(" + jobExecutionContext.getString("data_0") + ")");
		System.err.println("PromotionTasklet jobExecutionContext(" + jobExecutionContext.getString("data_1") + ")");
	}
}
