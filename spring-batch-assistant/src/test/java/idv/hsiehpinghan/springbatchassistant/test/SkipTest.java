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
public class SkipTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("skipJob")
	private Job skipJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(skipJob,
				builder.toJobParameters());
		assertSkipStepExecutions(jobExecution.getStepExecutions());
	}

	private void assertSkipStepExecutions(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertSkipStep_0Execution(stepExecutionArr[0]);
	}

	private void assertSkipStep_0Execution(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getCommitCount(), 1);
		Assert.assertEquals("FAILED", stepExecution.getExitStatus()
				.getExitCode());
		Assert.assertEquals(stepExecution.getFilterCount(), 0);
		Assert.assertEquals(stepExecution.getProcessSkipCount(), 0);
		Assert.assertEquals(stepExecution.getReadCount(), 4);
		Assert.assertEquals(stepExecution.getReadSkipCount(), 3);
		Assert.assertEquals(stepExecution.getRollbackCount(), 1);
		Assert.assertEquals(stepExecution.getSkipCount(), 3);
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.FAILED);
		Assert.assertEquals(stepExecution.getStepName(), "skipStep_0");
	}

}
