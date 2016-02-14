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
public class AllowStartIfCompleteSpringConfiguration {
	private static final boolean ALLOW_START_IF_COMPLETE = true;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job allowStartIfCompleteJob(
			@Qualifier("allowStartIfCompleteStep_0") Step allowStartIfCompleteStep_0) {
		return jobBuilderFactory.get("allowStartIfCompleteJob")
				.start(allowStartIfCompleteStep_0).build();
	}

	@Bean
	public Step allowStartIfCompleteStep_0(
			@Qualifier("allowStartIfCompleteTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("allowStartIfCompleteStep_0")
				.allowStartIfComplete(ALLOW_START_IF_COMPLETE).tasklet(tasklet)
				.build();
	}

}