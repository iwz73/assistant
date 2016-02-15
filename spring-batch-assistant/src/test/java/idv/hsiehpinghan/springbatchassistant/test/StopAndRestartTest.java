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
public class StopAndRestartTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("stopAndRestartJob")
	private Job stopAndRestartJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = null;
		jobExecution = jobLauncher.run(stopAndRestartJob,
				builder.toJobParameters());
		assertFailJobExecution_0(jobExecution);
		assertFailStepExecutions_0(jobExecution.getStepExecutions());
		jobExecution = jobLauncher.run(stopAndRestartJob,
				builder.toJobParameters());
		assertFailJobExecution_1(jobExecution);
		assertFailStepExecutions_1(jobExecution.getStepExecutions());
	}

	private void assertFailJobExecution_0(JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"STOPPED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.STOPPED);
	}

	private void assertFailStepExecutions_0(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertFailStep_0Execution_0(stepExecutionArr[0]);
	}

	private void assertFailStep_0Execution_0(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
	}

	private void assertFailJobExecution_1(JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
	}

	private void assertFailStepExecutions_1(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertFailStep_1Execution_1(stepExecutionArr[0]);
	}

	private void assertFailStep_1Execution_1(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
	}

}
