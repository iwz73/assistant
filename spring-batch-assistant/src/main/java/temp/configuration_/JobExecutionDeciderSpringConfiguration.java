package temp.configuration_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobExecutionDeciderSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job jobExecutionDeciderJob(
			@Qualifier("jobExecutionDeciderStep_0") Step jobExecutionDeciderStep_0,
			@Qualifier("jobExecutionDeciderDecider") JobExecutionDecider decider,
			@Qualifier("jobExecutionDeciderStep_1") Step jobExecutionDeciderStep_1) {
		return jobBuilderFactory.get("jobExecutionDeciderJob")
				.start(jobExecutionDeciderStep_0).next(decider)
				.on("JobExecutionDecider_test").to(jobExecutionDeciderStep_1)
				.build().build();
	}

	@Bean
	public Step jobExecutionDeciderStep_0(
			@Qualifier("jobExecutionDeciderReader_0") ItemReader<String> reader,
			@Qualifier("jobExecutionDeciderWriter_0") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("jobExecutionDeciderStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step jobExecutionDeciderStep_1(
			@Qualifier("jobExecutionDeciderReader_1") ItemReader<String> reader,
			@Qualifier("jobExecutionDeciderWriter_1") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("jobExecutionDeciderStep_1")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

}