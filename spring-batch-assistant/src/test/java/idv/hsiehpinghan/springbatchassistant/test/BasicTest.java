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
		Assert.assertEquals(DATE, jobParameters.getDate("DATE"));
		Assert.assertEquals(DOUBLE, jobParameters.getDouble("DOUBLE"));
		Assert.assertEquals(LONG, jobParameters.getLong("LONG"));
		Assert.assertEquals(STRING, jobParameters.getString("STRING"));
	}

	private void assertBasicJobInstance(JobInstance JobInstance) {
		Assert.assertEquals("basicJob", JobInstance.getJobName());
	}

	private void assertBasicJobExecution(JobExecution jobExecution) {
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus()
				.getExitCode());
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
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
		Assert.assertEquals(4, stepExecution.getCommitCount());
		Assert.assertEquals("COMPLETED", stepExecution.getExitStatus()
				.getExitCode());
		Assert.assertEquals(0, stepExecution.getFilterCount());
		Assert.assertEquals(0, stepExecution.getProcessSkipCount());
		Assert.assertEquals(10, stepExecution.getReadCount());
		Assert.assertEquals(0, stepExecution.getReadSkipCount());
		Assert.assertEquals(0, stepExecution.getRollbackCount());
		Assert.assertEquals(0, stepExecution.getSkipCount());
		Assert.assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
		Assert.assertEquals("basicStep_0", stepExecution.getStepName());
		assertBasicStep_0ExecutionContext(stepExecution.getExecutionContext());
	}

	private void assertBasicStep_1Execution(StepExecution stepExecution) {
		Assert.assertEquals(1, stepExecution.getCommitCount());
		Assert.assertEquals("COMPLETED", stepExecution.getExitStatus()
				.getExitCode());
		Assert.assertEquals(0, stepExecution.getFilterCount());
		Assert.assertEquals(0, stepExecution.getProcessSkipCount());
		Assert.assertEquals(0, stepExecution.getReadCount());
		Assert.assertEquals(0, stepExecution.getReadSkipCount());
		Assert.assertEquals(0, stepExecution.getRollbackCount());
		Assert.assertEquals(0, stepExecution.getSkipCount());
		Assert.assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
		Assert.assertEquals("basicStep_1", stepExecution.getStepName());
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
