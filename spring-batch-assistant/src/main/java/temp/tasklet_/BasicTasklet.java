package temp.tasklet_;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class BasicTasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		System.err.println("BasicTasklet execute : " + RepeatStatus.FINISHED);
		return RepeatStatus.FINISHED;
	}

}
