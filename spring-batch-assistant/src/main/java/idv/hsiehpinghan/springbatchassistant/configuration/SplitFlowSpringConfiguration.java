package idv.hsiehpinghan.springbatchassistant.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SplitFlowSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job splitFlowJob(@Qualifier("flows") SimpleFlow flows) {
		return jobBuilderFactory.get("splitFlowJob").start(flows).build()
				.build();
	}

	@Bean
	public SimpleFlow flows(@Qualifier("flow_0") SimpleFlow flow_0,
			@Qualifier("flow_1") SimpleFlow flow_1,
			@Qualifier("flow_2") SimpleFlow flow_2) {
		return new FlowBuilder<SimpleFlow>("flows").start(flow_0)
				.split(new SimpleAsyncTaskExecutor()).add(flow_1, flow_2)
				.build();
	}

	@Bean
	public SimpleFlow flow_0(@Qualifier("splitFlowStep_0") Step splitFlowStep_0) {
		return new FlowBuilder<SimpleFlow>("flow_0").start(splitFlowStep_0)
				.build();
	}

	@Bean
	public SimpleFlow flow_1(@Qualifier("splitFlowStep_1") Step splitFlowStep_1) {
		return new FlowBuilder<SimpleFlow>("flow_1").start(splitFlowStep_1)
				.build();
	}

	@Bean
	public SimpleFlow flow_2(@Qualifier("splitFlowStep_2") Step splitFlowStep_2) {
		return new FlowBuilder<SimpleFlow>("flow_2").start(splitFlowStep_2)
				.build();
	}

	@Bean
	public Step splitFlowStep_0(
			@Qualifier("splitFlowReader_0") ItemReader<String> reader,
			@Qualifier("splitFlowWriter_0") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("splitFlowStep_0")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step splitFlowStep_1(
			@Qualifier("splitFlowReader_1") ItemReader<String> reader,
			@Qualifier("splitFlowWriter_1") ItemWriter<Integer> writer) {
		return stepBuilderFactory.get("splitFlowStep_1")
				.<String, Integer> chunk(3).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public Step splitFlowStep_2(@Qualifier("splitFlowTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("splitFlowStep_2").tasklet(tasklet)
				.build();
	}

}