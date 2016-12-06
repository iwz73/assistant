package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ContextTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		printAttribute(chunkContext);
		System.err.println("ContextTasklet !!!");
		return RepeatStatus.FINISHED;
	}

	private void printAttribute(ChunkContext chunkContext) {
		StepContext stepContext = chunkContext.getStepContext();
		ExecutionContext executionContext = stepContext.getStepExecution().getJobExecution().getExecutionContext();
		System.err.println("ContextTasklet chunkContext : " + chunkContext.getAttribute("chunkContext")
				+ ", stepContext : " + stepContext.getAttribute("stepContext") + ", executionContext : "
				+ executionContext.getString("executionContext"));
	}
}
