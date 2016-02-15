package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobStepSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job jobStepJob(@Qualifier("jobStepStep_0") Step jobStepStep_0,
			@Qualifier("jobStepStep_1") Step jobStepStep_1) {
		return jobBuilderFactory.get("jobStepJob").start(jobStepStep_0)
				.next(jobStepStep_1).build();
	}

	@Bean
	public Step jobStepStep_0(
			@Qualifier("jobStepReader") ItemReader<String> reader,
			@Qualifier("jobStepWriter") ItemWriter<String> writer) {
		return stepBuilderFactory.get("jobStepStep_0")
				.<String, String> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step jobStepStep_1(@Qualifier("jobStepSubJob") Job jobStepSubJob) {
		return stepBuilderFactory.get("jobStepStep_1").job(jobStepSubJob)
				.build();
	}

	@Bean
	public Job jobStepSubJob(@Qualifier("jobStepSubStep") Step jobStepSubStep) {
		return jobBuilderFactory.get("jobStepSubJob").start(jobStepSubStep)
				.build();
	}

	@Bean
	public Step jobStepSubStep(@Qualifier("jobStepTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("jobStepSubStep").tasklet(tasklet)
				.build();
	}

}