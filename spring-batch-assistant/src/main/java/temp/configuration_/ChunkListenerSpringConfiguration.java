package temp.configuration_;

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

import temp.listener_.ChunkListenerListener;

@Configuration
public class ChunkListenerSpringConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job chunkListenerJob(
			@Qualifier("chunkListenerStep_0") Step chunkListenerStep_0) {
		return jobBuilderFactory.get("chunkListenerJob")
				.start(chunkListenerStep_0).build();
	}

	@Bean
	public Step chunkListenerStep_0(
			@Qualifier("chunkListenerListener") ChunkListenerListener listener,
			@Qualifier("chunkListenerReader") ItemReader<String> reader,
			@Qualifier("chunkListenerWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("chunkListenerStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.listener(listener).build();
	}

}