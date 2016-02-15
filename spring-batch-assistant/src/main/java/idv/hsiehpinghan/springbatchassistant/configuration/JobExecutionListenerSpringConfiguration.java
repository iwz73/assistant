package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobExecutionListenerSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job jobExecutionListenerJob(
			@Qualifier("jobExecutionListenerListener") JobExecutionListener jobExecutionListener,
			@Qualifier("jobExecutionListenerStep_0") Step jobExecutionListenerStep_0) {
		return jobBuilderFactory.get("jobExecutionListenerJob")
				.listener(jobExecutionListener)
				.start(jobExecutionListenerStep_0).build();
	}

	@Bean
	public Step jobExecutionListenerStep_0(
			@Qualifier("doNothingTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("jobExecutionListenerStep_0")
				.tasklet(tasklet).build();
	}

}