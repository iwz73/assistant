package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TaskExecutorTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("TaskExecutorTasklet execute begin !!!");
		for (int i = 0; i < 10; ++i) {
			Thread.sleep(1000);
		}
		System.err.println("TaskExecutorTasklet execute finish !!!");
		return RepeatStatus.FINISHED;
	}

}
