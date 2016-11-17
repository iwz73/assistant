package temp.configuration_;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipListenerSpringConfiguration {
	private static final Class<? extends Throwable> SKIP_CLASS = IOException.class;
	private static final int SKIP_LIMIT = 3;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job skipListenerJob(
			@Qualifier("skipListenerStep_0") Step skipListenerStep_0) {
		return jobBuilderFactory.get("skipListenerJob")
				.start(skipListenerStep_0).build();
	}

	@Bean
	public Step skipListenerStep_0(
			@Qualifier("skipListenerReader") ItemReader<String> reader,
			@Qualifier("skipListenerProcessor") ItemProcessor<String, Integer> processor,
			@Qualifier("skipListenerWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("skipListenerStep_0")
				.<String, Integer> chunk(3).faultTolerant().skip(SKIP_CLASS)
				.skipLimit(SKIP_LIMIT).reader(reader).processor(processor)
				.writer(writer).build();
	}

}