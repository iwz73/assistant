package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalFlowSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	/*
	 * conditionalFlowStep_0 --<GOTO_1>--> conditionalFlowStep_1
	 *                       |
	 *                       --<*>-------> conditionalFlowStep_2
	 */
	@Bean
	public Job conditionalFlowJob(
			@Qualifier("conditionalFlowStep_0") Step conditionalFlowStep_0,
			@Qualifier("conditionalFlowStep_1") Step conditionalFlowStep_1,
			@Qualifier("conditionalFlowStep_2") Step conditionalFlowStep_2) {
		return jobBuilderFactory.get("conditionalFlowJob")
				.start(conditionalFlowStep_0)
					.on("GOTO_1").to(conditionalFlowStep_1)
				.from(conditionalFlowStep_0)
					.on("*").to(conditionalFlowStep_2)
				.build().build();
	}

	@Bean
	protected Step conditionalFlowStep_0(
			@Qualifier("conditionalFlowReader_0") ItemReader<String> reader,
			@Qualifier("conditionalFlowWriter_0") ItemWriter<Integer> writer,
			@Qualifier("conditionalFlowListener_0") StepExecutionListener listener) {
		return stepBuilderFactory.get("conditionalFlowStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.listener(listener)
				.build();
	}

	@Bean
	protected Step conditionalFlowStep_1(
			@Qualifier("conditionalFlowReader_1") ItemReader<String> reader,
			@Qualifier("conditionalFlowWriter_1") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("conditionalFlowStep_1")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	protected Step conditionalFlowStep_2(
			@Qualifier("conditionalFlowReader_2") ItemReader<String> reader,
			@Qualifier("conditionalFlowWriter_2") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("conditionalFlowStep_2")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}
}