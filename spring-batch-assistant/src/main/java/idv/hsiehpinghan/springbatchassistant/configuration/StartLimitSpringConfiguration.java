package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartLimitSpringConfiguration {
	private static final int START_LIMIT = 3;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job startLimitJob(
			@Qualifier("startLimitStep_0") Step startLimitStep_0) {
		return jobBuilderFactory.get("startLimitJob").start(startLimitStep_0)
				.build();
	}

	@Bean
	protected Step startLimitStep_0(
			@Qualifier("startLimitTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("startLimitStep_0")
				.startLimit(START_LIMIT).tasklet(tasklet).build();
	}

}