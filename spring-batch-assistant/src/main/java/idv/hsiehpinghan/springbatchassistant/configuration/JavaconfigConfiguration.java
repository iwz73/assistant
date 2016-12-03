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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaconfigConfiguration {
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
	public Step javaconfigTaskletStep(@Qualifier("javaconfigTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("javaconfigTaskletStep").tasklet(tasklet).build();
	}

	@Bean
	public Step javaconfigChunkStep(@Qualifier("javaconfigReader") ItemReader<String> reader,
			@Qualifier("javaconfigProcessor") ItemProcessor<String, Integer> processor,
			@Qualifier("javaconfigWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("javaconfigChunkStep").<String, Integer>chunk(3).reader(reader)
				.processor(processor).writer(writer).build();
	}

}