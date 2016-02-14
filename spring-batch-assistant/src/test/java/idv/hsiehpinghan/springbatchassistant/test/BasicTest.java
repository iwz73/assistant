package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

import java.util.Collection;
import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	private final Date DATE = new Date();
	private final Double DOUBLE = Double.MAX_VALUE;
	private final Long LONG = Long.MAX_VALUE;
	private final String STRING = "string";

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("basicJob")
	private Job basicJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("DATE", DATE);
		builder.addDouble("DOUBLE", DOUBLE);
		builder.addLong("LONG", LONG);
		builder.addString("STRING", STRING);
		JobExecution jobExecution = jobLauncher.run(basicJob,
				builder.toJobParameters());
		assertBasicJobParameters(jobExecution.getJobParameters());
		assertBasicJobInstance(jobExecution.getJobInstance());
		assertBasicJobExecution(jobExecution);
		assertBasicStepExecutions(jobExecution.getStepExecutions());
	}

	private void assertBasicJobParameters(JobParameters jobParameters) {
		Assert.assertEquals(jobParameters.getDate("DATE"), DATE);
		Assert.assertEquals(jobParameters.getDouble("DOUBLE"), DOUBLE);
		Assert.assertEquals(jobParameters.getLong("LONG"), LONG);
		Assert.assertEquals(jobParameters.getString("STRING"), STRING);
	}

	private void assertBasicJobInstance(JobInstance JobInstance) {
		Assert.assertEquals(JobInstance.getJobName(), "basicJob");
	}

	private void assertBasicJobExecution(JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
		assertBasicJobExecutionContext(jobExecution.getExecutionContext());
	}

	private void assertBasicStepExecutions(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertBasicStep_0Execution(stepExecutionArr[0]);
		assertBasicStep_1Execution(stepExecutionArr[1]);
	}

	private void assertBasicStep_0Execution(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getCommitCount(), 4);
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"COMPLETED");
		Assert.assertEquals(stepExecution.getFilterCount(), 0);
		Assert.assertEquals(stepExecution.getProcessSkipCount(), 0);
		Assert.assertEquals(stepExecution.getReadCount(), 10);
		Assert.assertEquals(stepExecution.getReadSkipCount(), 0);
		Assert.assertEquals(stepExecution.getRollbackCount(), 0);
		Assert.assertEquals(stepExecution.getSkipCount(), 0);
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
		Assert.assertEquals(stepExecution.getStepName(), "basicStep_0");
		assertBasicStep_0ExecutionContext(stepExecution.getExecutionContext());
	}

	private void assertBasicStep_1Execution(StepExecution stepExecution) {
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
		Assert.assertEquals(stepExecution.getStepName(), "basicStep_1");
		assertBasicStep_1ExecutionContext(stepExecution.getExecutionContext());
	}

	private void assertBasicJobExecutionContext(
			ExecutionContext executionContext) {
		// do nothing
	}

	private void assertBasicStep_0ExecutionContext(
			ExecutionContext executionContext) {
		// do nothing
	}

	private void assertBasicStep_1ExecutionContext(
			ExecutionContext executionContext) {
		// do nothing
	}
}
