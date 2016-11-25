package temp.test_;

import java.util.Collection;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class RetryTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("retryJob")
	private Job retryJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = null;
		jobExecution = jobLauncher.run(retryJob, builder.toJobParameters());
		assertRetryStepExecutions(jobExecution.getStepExecutions(), "FAILED");
		jobExecution = jobLauncher.run(retryJob, builder.toJobParameters());
		assertRetryStepExecutions(jobExecution.getStepExecutions(), "FAILED");
		jobExecution = jobLauncher.run(retryJob, builder.toJobParameters());
		assertRetryStepExecutions(jobExecution.getStepExecutions(), "FAILED");
		jobExecution = jobLauncher.run(retryJob, builder.toJobParameters());
		assertRetryStepExecutions(jobExecution.getStepExecutions(), "COMPLETED");
	}

	private void assertRetryStepExecutions(
			Collection<StepExecution> stepExecutions, String exitCode) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertRetryStep_0Execution(stepExecutionArr[0], exitCode);
	}

	private void assertRetryStep_0Execution(StepExecution stepExecution,
			String exitCode) {
		Assert.assertEquals(exitCode, stepExecution.getExitStatus()
				.getExitCode());
	}

}
