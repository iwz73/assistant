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

@Configuration
public class EndSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job endJob(@Qualifier("endStep_0") Step endStep_0) {
		return jobBuilderFactory.get("endJob").start(endStep_0).on("FAILED")
				.end("END TEST").build().build();
	}

	@Bean
	public Step endStep_0(@Qualifier("endReader") ItemReader<String> reader,
			@Qualifier("endWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("endStep_0").<String, Integer> chunk(3)
				.reader(reader).writer(writer).build();
	}

}