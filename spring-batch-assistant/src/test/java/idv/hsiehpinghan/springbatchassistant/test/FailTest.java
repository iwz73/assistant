package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

import java.util.Collection;

import org.springframework.batch.core.BatchStatus;
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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class FailTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("failJob")
	private Job failJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(failJob,
				builder.toJobParameters());
		assertFailJobExecution(jobExecution);
		assertFailStepExecutions(jobExecution.getStepExecutions());
	}

	private void assertFailJobExecution(JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"FAILED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.FAILED);
	}

	private void assertFailStepExecutions(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertFailStep_0Execution(stepExecutionArr[0]);
	}

	private void assertFailStep_0Execution(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
	}

}
