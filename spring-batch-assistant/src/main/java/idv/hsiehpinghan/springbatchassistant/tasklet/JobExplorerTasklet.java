package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class JobExplorerTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("JobExplorerTasklet !!!");
		return RepeatStatus.FINISHED;
	}

}
