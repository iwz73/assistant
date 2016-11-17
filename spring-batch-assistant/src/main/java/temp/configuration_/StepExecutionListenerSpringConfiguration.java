package temp.configuration_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepExecutionListenerSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job stepExecutionListenerJob(
			@Qualifier("stepExecutionListenerStep_0") Step stepExecutionListenerStep_0) {
		return jobBuilderFactory.get("stepExecutionListenerJob")
				.start(stepExecutionListenerStep_0).build();
	}

	@Bean
	public Step stepExecutionListenerStep_0(
			@Qualifier("stepExecutionListenerListener") StepExecutionListener listener,
			@Qualifier("doNothingTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("stepExecutionListenerStep_0")
				.listener(listener).tasklet(tasklet).build();
	}

}