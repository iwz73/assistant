package temp.tasklet_;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class RestartableTasklet implements Tasklet {
	private static boolean isRestart = false;

	@Override
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		if (isRestart == false) {
			isRestart = true;
			String msg = "RestartableTasklet exception test !!!";
			System.err.println(msg);
			throw new Exception(msg);
		}
		System.err.println("RestartableTasklet execute : "
				+ RepeatStatus.FINISHED);
		return RepeatStatus.FINISHED;
	}

}
