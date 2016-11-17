package temp.configuration_;

import org.springframework.batch.core.ItemWriteListener;
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
public class ItemWriteListenerSpringConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job itemWriteListenerJob(
			@Qualifier("itemWriteListenerStep_0") Step itemWriteListenerStep_0) {
		return jobBuilderFactory.get("itemWriteListenerJob")
				.start(itemWriteListenerStep_0).build();
	}

	@Bean
	public Step itemWriteListenerStep_0(
			@Qualifier("itemWriteListenerReader") ItemReader<String> reader,
			@Qualifier("itemWriteListenerWriter") ItemWriter<String> writer,
			@Qualifier("itemWriteListenerListener") ItemWriteListener<String> listener) {
		return stepBuilderFactory.get("itemWriteListenerStep_0")
				.<String, String> chunk(3).reader(reader).writer(writer)
				.listener(listener).build();
	}

}