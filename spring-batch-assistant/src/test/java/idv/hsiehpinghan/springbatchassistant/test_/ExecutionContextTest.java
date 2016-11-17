package idv.hsiehpinghan.springbatchassistant.test_;

import java.util.Collection;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
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
public class ExecutionContextTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("executionContextJob")
	private Job executionContextJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution_first = jobLauncher.run(executionContextJob,
				builder.toJobParameters());
		assertExecutionContextJobExecution_first(jobExecution_first);
		assertExecutionContextStepExecutions_first(jobExecution_first
				.getStepExecutions());
		JobExecution jobExecution_second = jobLauncher.run(executionContextJob,
				builder.toJobParameters());
		assertExecutionContextJobExecution_second(jobExecution_second);
		assertExecutionContextStepExecutions_second(jobExecution_second
				.getStepExecutions());
	}

	private void assertExecutionContextJobExecution_first(
			JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"FAILED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.FAILED);
		assertExecutionContextJobExecutionContext_first(jobExecution
				.getExecutionContext());
	}

	private void assertExecutionContextStepExecutions_first(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertExecutionContextStep_0Execution_first(stepExecutionArr[0]);
	}

	private void assertExecutionContextStep_0Execution_first(
			StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getCommitCount(), 1);
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"FAILED");
		Assert.assertEquals(stepExecution.getFilterCount(), 0);
		Assert.assertEquals(stepExecution.getProcessSkipCount(), 0);
		Assert.assertEquals(stepExecution.getReadCount(), 6);
		Assert.assertEquals(stepExecution.getReadSkipCount(), 0);
		Assert.assertEquals(stepExecution.getRollbackCount(), 1);
		Assert.assertEquals(stepExecution.getSkipCount(), 0);
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.FAILED);
		Assert.assertEquals(stepExecution.getStepName(),
				"executionContextStep_0");
		assertExecutionContextStep_0ExecutionContext_first(stepExecution
				.getExecutionContext());
	}

	private void assertExecutionContextJobExecutionContext_first(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.get("jobExecutionContextParam"),
				"jobExecutionContextParam");

	}

	private void assertExecutionContextStep_0ExecutionContext_first(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.get("stepExecutionContextParam"),
				3);
	}

	private void assertExecutionContextJobExecution_second(
			JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
		assertExecutionContextJobExecutionContext_second(jobExecution
				.getExecutionContext());
	}

	private void assertExecutionContextStepExecutions_second(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertExecutionContextStep_0Execution_second(stepExecutionArr[0]);
		assertExecutionContextStep_1Execution_second(stepExecutionArr[1]);
	}

	private void assertExecutionContextStep_0Execution_second(
			StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getCommitCount(), 3);
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getFilterCount(), 0);
		Assert.assertEquals(stepExecution.getProcessSkipCount(), 0);
		Assert.assertEquals(stepExecution.getReadCount(), 7);
		Assert.assertEquals(stepExecution.getReadSkipCount(), 0);
		Assert.assertEquals(stepExecution.getRollbackCount(), 0);
		Assert.assertEquals(stepExecution.getSkipCount(), 0);
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
		Assert.assertEquals(stepExecution.getStepName(),
				"executionContextStep_0");
		assertExecutionContextStep_0ExecutionContext_second(stepExecution
				.getExecutionContext());
	}

	private void assertExecutionContextStep_1Execution_second(
			StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getCommitCount(), 1);
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getFilterCount(), 0);
		Assert.assertEquals(stepExecution.getProcessSkipCount(), 0);
		Assert.assertEquals(stepExecution.getReadCount(), 0);
		Assert.assertEquals(stepExecution.getReadSkipCount(), 0);
		Assert.assertEquals(stepExecution.getRollbackCount(), 0);
		Assert.assertEquals(stepExecution.getSkipCount(), 0);
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
		Assert.assertEquals(stepExecution.getStepName(),
				"executionContextStep_1");
	}

	private void assertExecutionContextJobExecutionContext_second(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.get("jobExecutionContextParam"),
				"jobExecutionContextParam");

	}

	private void assertExecutionContextStep_0ExecutionContext_second(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.get("stepExecutionContextParam"),
				10);
	}
}
