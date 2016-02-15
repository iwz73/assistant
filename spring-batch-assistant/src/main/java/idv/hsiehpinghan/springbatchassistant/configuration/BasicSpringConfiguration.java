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
public class BasicSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job basicJob(@Qualifier("basicStep_0") Step basicStep_0,
			@Qualifier("basicStep_1") Step basicStep_1) {
		return jobBuilderFactory.get("basicJob").start(basicStep_0)
				.next(basicStep_1).build();
	}

	@Bean
	public Step basicStep_0(
			@Qualifier("basicReader") ItemReader<String> reader,
			@Qualifier("basicProcessor") ItemProcessor<String, Integer> processor,
			@Qualifier("basicWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("basicStep_0").<String, Integer> chunk(3)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	public Step basicStep_1(@Qualifier("basicTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("basicStep_1").tasklet(tasklet).build();
	}

}