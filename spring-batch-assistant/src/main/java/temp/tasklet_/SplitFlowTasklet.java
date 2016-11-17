package temp.tasklet_;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SplitFlowTasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		System.err.println("Before SplitFlowTasklet.");
		Thread.sleep(5000);
		System.err.println("After SplitFlowTasklet.");
		return RepeatStatus.FINISHED;
	}

}
