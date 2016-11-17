package temp.configuration_;

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
public class ExecutionContextSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job executionContextJob(
			@Qualifier("executionContextStep_0") Step executionContextStep_0,
			@Qualifier("executionContextStep_1") Step executionContextStep_1) {
		return jobBuilderFactory.get("executionContextJob")
				.start(executionContextStep_0).next(executionContextStep_1)
				.build();
	}

	@Bean
	public Step executionContextStep_0(
			@Qualifier("executionContextReader") ItemReader<String> reader,
			@Qualifier("executionContextWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("executionContextStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step executionContextStep_1(
			@Qualifier("executionContextTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("executionContextStep_1")
				.tasklet(tasklet).listener(tasklet).build();
	}

}