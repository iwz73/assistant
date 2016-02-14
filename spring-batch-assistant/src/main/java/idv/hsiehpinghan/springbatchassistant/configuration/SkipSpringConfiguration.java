package idv.hsiehpinghan.springbatchassistant.configuration;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipSpringConfiguration {
	private static final Class<? extends Throwable> SKIP_CLASS = IOException.class;
	private static final int SKIP_LIMIT = 3;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job skipJob(@Qualifier("skipStep_0") Step skipStep_0) {
		return jobBuilderFactory.get("skipJob").start(skipStep_0).build();
	}

	@Bean
	public Step skipStep_0(@Qualifier("skipReader") ItemReader<String> reader,
			@Qualifier("skipWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("skipStep_0").<String, Integer> chunk(3)
				.faultTolerant().skip(SKIP_CLASS).skipLimit(SKIP_LIMIT)
				.reader(reader).writer(writer).build();
	}

}