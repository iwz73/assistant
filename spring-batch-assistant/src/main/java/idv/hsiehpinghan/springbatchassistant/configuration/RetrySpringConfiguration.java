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
public class RetrySpringConfiguration {
	private static final Class<? extends Throwable> RETRY_CLASS = IOException.class;
	private static final int RETRY_LIMIT = 3;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job retryJob(@Qualifier("retryStep_0") Step retryStep_0) {
		return jobBuilderFactory.get("retryJob").start(retryStep_0).build();
	}

	@Bean
	protected Step retryStep_0(
			@Qualifier("retryReader") ItemReader<String> reader,
			@Qualifier("retryWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("retryStep_0").<String, Integer> chunk(3)
				.faultTolerant().retry(RETRY_CLASS).retryLimit(RETRY_LIMIT)
				.reader(reader).writer(writer).build();
	}

}