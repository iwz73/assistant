package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class StartLimitTasklet implements Tasklet {

	@Override
	@SuppressWarnings("unused")
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		if (true) {
			String msg = "StartLimitTasklet exception test !!!";
			System.err.println(msg);
			throw new RuntimeException(msg);
		}
		return RepeatStatus.FINISHED;
	}

}
