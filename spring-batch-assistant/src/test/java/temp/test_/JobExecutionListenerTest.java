package temp.test_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JobExecutionListenerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("jobExecutionListenerJob")
	private Job jobExecutionListenerJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(jobExecutionListenerJob,
				builder.toJobParameters());
		assertJobExecutionListenerJobExecution(jobExecution);
	}

	private void assertJobExecutionListenerJobExecution(
			JobExecution jobExecution) {
		assertJobExecutionListenerJobExecutionContext(jobExecution
				.getExecutionContext());
	}

	private void assertJobExecutionListenerJobExecutionContext(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.getString("beforeJob"),
				"beforeJob");
		Assert.assertEquals(executionContext.getString("afterJob"), "afterJob");
	}

}
