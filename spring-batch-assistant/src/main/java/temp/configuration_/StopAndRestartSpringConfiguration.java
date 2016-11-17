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
public class StopAndRestartSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job stopAndRestartJob(
			@Qualifier("stopAndRestartStep_0") Step stopAndRestartStep_0,
			@Qualifier("stopAndRestartStep_1") Step stopAndRestartStep_1) {
		return jobBuilderFactory.get("stopAndRestartJob")
				.start(stopAndRestartStep_0).on("COMPLETED")
				.stopAndRestart(stopAndRestartStep_1).build().build();
	}

	@Bean
	public Step stopAndRestartStep_0(
			@Qualifier("stopAndRestartReader_0") ItemReader<String> reader,
			@Qualifier("stopAndRestartWriter_0") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("stopAndRestartStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step stopAndRestartStep_1(
			@Qualifier("stopAndRestartReader_1") ItemReader<String> reader,
			@Qualifier("stopAndRestartWriter_1") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("stopAndRestartStep_1")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}
}