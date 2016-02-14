package idv.hsiehpinghan.springbatchassistant.configuration;

import idv.hsiehpinghan.springbatchassistant.listener.ItemReadListenerListener;

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
public class ItemReadListenerSpringConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job itemReadListenerJob(
			@Qualifier("itemReadListenerStep_0") Step itemReadListenerStep_0) {
		return jobBuilderFactory.get("itemReadListenerJob")
				.start(itemReadListenerStep_0).build();
	}

	@Bean
	protected Step itemReadListenerStep_0(
			@Qualifier("itemReadListenerReader") ItemReader<String> reader,
			@Qualifier("itemReadListenerListener") ItemReadListenerListener listener,
			@Qualifier("itemReadListenerWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("itemReadListenerStep_0")
				.<String, Integer> chunk(3).reader(reader).listener(listener)
				.writer(writer).build();
	}

}