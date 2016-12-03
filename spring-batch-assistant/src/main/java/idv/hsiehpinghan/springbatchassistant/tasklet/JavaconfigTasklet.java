package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("step")
public class JavaconfigTasklet implements Tasklet {
	@Value("#{jobParameters['commitInterval']}")
	private Integer limit;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("JavaconfigTasklet execute : limit(" + limit + ")");
		return RepeatStatus.FINISHED;
	}

}
