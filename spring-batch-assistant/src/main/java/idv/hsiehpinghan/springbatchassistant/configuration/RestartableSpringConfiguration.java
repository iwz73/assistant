package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestartableSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job restartableJob(
			@Qualifier("restartableStep_0") Step restartableStep_0) {
		SimpleJob simpleJob = (SimpleJob) jobBuilderFactory
				.get("restartableJob").start(restartableStep_0).build();
		simpleJob.setRestartable(false);
		return simpleJob;
	}

	@Bean
	protected Step restartableStep_0(
			@Qualifier("restartableTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("restartableStep_0").tasklet(tasklet)
				.build();
	}

}