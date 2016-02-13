package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		Object jobExecutionContextParam = chunkContext.getStepContext()
				.getStepExecution().getJobExecution().getExecutionContext()
				.get("jobExecutionContextParam");
		System.err.println("BasicTasklet execute : jobExecutionContextParam("
				+ jobExecutionContextParam + ")");
		return RepeatStatus.FINISHED;
	}

}
