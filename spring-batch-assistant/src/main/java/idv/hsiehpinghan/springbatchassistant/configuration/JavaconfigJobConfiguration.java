package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JavaconfigJobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job javaconfigJob(@Qualifier("javaconfigTaskletStep") Step javaconfigTaskletStep,
			@Qualifier("javaconfigChunkStep") Step javaconfigChunkStep) {
		return jobBuilderFactory.get("javaconfigJob").start(javaconfigTaskletStep).next(javaconfigChunkStep).build();
	}

	@Bean
	@Scope("job")
	public Step javaconfigTaskletStep(@Qualifier("javaconfigTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("javaconfigTaskletStep").tasklet(tasklet).build();
	}

	@Bean
	@Scope("job")
	public Step javaconfigChunkStep(@Qualifier("javaconfigReader") ItemReader<String> reader,
			@Qualifier("javaconfigProcessor") ItemProcessor<String, Integer> processor,
			@Qualifier("javaconfigWriter") ItemWriter<Integer> writer,
			@Value("#{jobParameters['commitInterval']}") Integer commitInterval) {
		return stepBuilderFactory.get("javaconfigChunkStep").<String, Integer>chunk(commitInterval).reader(reader)
				.processor(processor).writer(writer).build();
	}

}