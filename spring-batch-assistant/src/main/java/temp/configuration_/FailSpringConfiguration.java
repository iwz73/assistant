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
public class FailSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job failJob(@Qualifier("failStep_0") Step failStep_0) {
		return jobBuilderFactory.get("failJob").start(failStep_0)
				.on("COMPLETED").fail().build().build();
	}

	@Bean
	public Step failStep_0(@Qualifier("failReader") ItemReader<String> reader,
			@Qualifier("failWriter") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("failStep_0").<String, Integer> chunk(3)
				.reader(reader).writer(writer).build();
	}

}