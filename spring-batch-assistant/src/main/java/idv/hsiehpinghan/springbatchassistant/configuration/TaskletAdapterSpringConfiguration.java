package idv.hsiehpinghan.springbatchassistant.configuration;

import idv.hsiehpinghan.springbatchassistant.adapter.TaskletAdapterAdapter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletAdapterSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job methodInvokingTaskletAdapterJob(
			@Qualifier("methodInvokingTaskletAdapterStep_0") Step methodInvokingTaskletAdapterStep_0) {
		return jobBuilderFactory.get("methodInvokingTaskletAdapterJob")
				.start(methodInvokingTaskletAdapterStep_0).build();
	}

	@Bean
	public Step methodInvokingTaskletAdapterStep_0(
			@Qualifier("methodInvokingTaskletAdapter") MethodInvokingTaskletAdapter adapter) {
		return stepBuilderFactory.get("methodInvokingTaskletAdapterStep_0")
				.tasklet(adapter).build();
	}

	@Bean
	public MethodInvokingTaskletAdapter methodInvokingTaskletAdapter(
			@Qualifier("taskletAdapterAdapter") TaskletAdapterAdapter taskletAdapterAdapter) {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(taskletAdapterAdapter);
		adapter.setTargetMethod("test");
		return adapter;
	}

}