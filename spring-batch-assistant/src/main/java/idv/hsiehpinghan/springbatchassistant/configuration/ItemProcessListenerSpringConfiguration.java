package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.ItemProcessListener;
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
public class ItemProcessListenerSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job itemProcessListenerJob(
			@Qualifier("itemProcessListenerStep_0") Step itemProcessListenerStep_0) {
		return jobBuilderFactory.get("itemProcessListenerJob")
				.start(itemProcessListenerStep_0).build();
	}

	@Bean
	protected Step itemProcessListenerStep_0(
			@Qualifier("itemProcessListenerReader") ItemReader<String> reader,
			@Qualifier("itemProcessListenerProcessor") ItemProcessor<String, Integer> processor,
			@Qualifier("itemProcessListenerListener") ItemProcessListener<String, Integer> listener,
			@Qualifier("itemProcessListenerWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("itemProcessListenerStep_0")
				.<String, Integer> chunk(3).reader(reader).processor(processor)
				.listener(listener).writer(writer).build();
	}

}