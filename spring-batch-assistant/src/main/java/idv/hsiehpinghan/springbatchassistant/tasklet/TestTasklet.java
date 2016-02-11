package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class TestTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution paramStepContribution,
			ChunkContext paramChunkContext) throws Exception {
		System.err.println("TestTasklet execute.");
		return RepeatStatus.FINISHED;
	}

}
