package idv.hsiehpinghan.springbatchassistant.tasklet;

import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet implements Tasklet {
	private final Object OBJECT = new Date();
	private final double DOUBLE = Double.MAX_VALUE;
	private final int INT = Integer.MAX_VALUE;
	private final long LONG = Long.MAX_VALUE;
	private final String STRING = "string";

	@Override
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		putJobExecutionContext(chunkContext.getStepContext().getStepExecution()
				.getJobExecution().getExecutionContext());
		System.err.println("BasicTasklet execute : " + RepeatStatus.FINISHED);
		return RepeatStatus.FINISHED;
	}

	private void putJobExecutionContext(ExecutionContext executionContext) {
		executionContext.put("OBJECT", OBJECT);
		executionContext.putDouble("DOUBLE", DOUBLE);
		executionContext.putInt("INT", INT);
		executionContext.putLong("LONG", LONG);
		executionContext.putString("STRING", STRING);
	}
}
