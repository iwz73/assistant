package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TaskletAdapterTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("methodInvokingTaskletAdapterJob")
	private Job methodInvokingTaskletAdapterJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		jobLauncher.run(methodInvokingTaskletAdapterJob,
				builder.toJobParameters());
	}

}
